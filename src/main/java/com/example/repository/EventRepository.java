package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.model.Event;

@Repository(value = EventRepository.NAME)
@RepositoryRestResource(path = "events") 
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
	public static final String NAME = "eventRepository";
}
