package com.example.repository;

import com.example.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tsachev
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
