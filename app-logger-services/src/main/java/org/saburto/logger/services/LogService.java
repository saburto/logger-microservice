package org.saburto.logger.services;

import org.saburto.logger.entity.Log;
import org.saburto.logger.entity.LogLevel;
import org.saburto.logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogService {
	
	private final LogRepository repository;

	@Autowired
	public LogService(LogRepository repository) {
		this.repository = repository;
	}
	@RequestMapping( method = RequestMethod.POST)
	public Log log(@RequestBody Log log){
		return repository.save(log);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public Iterable<Log> find(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int count,
            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam(value = "sort", defaultValue = "date", required = false) String sortProperty,
            @RequestParam(value = "level", required = false) LogLevel level,
            @RequestParam(value = "logName", required = false) String logname){
		System.out.println(page);
		PageRequest pageable = new PageRequest(page, count, new Sort(direction, sortProperty));
		
		Page<Log> result;
		
		if(level != null && logname != null){
			result = repository.findByLogNameLikeAndLevel(logname, level, pageable);	
		}else if (level != null){
			result = repository.findByLevel(level, pageable);
		}else if(logname != null){
			result = repository.findByLogNameLike(logname, pageable);
		}else{
			result = repository.findAll(pageable);	
		}
		return result.getContent();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Log> find(@PathVariable String id){
		Log findOne = repository.findOne(id);
		return findOne == null ? new ResponseEntity<Log>(HttpStatus.NOT_FOUND) : new ResponseEntity<Log>(findOne, HttpStatus.OK) ;
	}

}
