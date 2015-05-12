package org.saburto.logger.repository;

import org.saburto.logger.entity.Log;
import org.saburto.logger.entity.LogLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, String> {

	Page<Log> findByLogNameLike(String logName, Pageable pageable);
	Page<Log> findByLogNameLikeAndLevel(String logName, LogLevel level, Pageable pageable);
	Page<Log> findByLevel(LogLevel level, Pageable pageable);

}
