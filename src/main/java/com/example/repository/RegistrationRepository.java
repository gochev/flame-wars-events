package com.example.repository;

import com.example.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author tsachev
 */
@RepositoryRestResource
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
