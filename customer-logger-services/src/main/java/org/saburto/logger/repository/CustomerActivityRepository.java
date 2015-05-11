package org.saburto.logger.repository;

import org.saburto.logger.entity.CustomerActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerActivityRepository extends CrudRepository<CustomerActivity, String> {

}
