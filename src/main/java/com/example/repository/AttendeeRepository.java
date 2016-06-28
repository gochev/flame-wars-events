package com.example.repository;

import com.example.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tsachev
 */
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
