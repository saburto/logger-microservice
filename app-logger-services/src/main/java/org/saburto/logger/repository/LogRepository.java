package org.saburto.logger.repository;

import org.saburto.logger.entity.Log;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, String> {

}
