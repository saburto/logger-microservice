package org.saburto.logger.service;

import org.saburto.logger.entity.CustomerActivity;
import org.saburto.logger.repository.CustomerActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/activity")
public class CustomerActivityService {
	
	private final CustomerActivityRepository repository;

	@Autowired
	public CustomerActivityService(CustomerActivityRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public CustomerActivity log(@RequestBody CustomerActivity customerActivity){
		return repository.save(customerActivity);
	}
}
