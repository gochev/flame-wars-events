package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Organizer;


@Repository(value = OrganizerRepository.NAME)
public interface OrganizerRepository extends PagingAndSortingRepository<Organizer, Long> {
	public static final String NAME = "organizerRepository";
	
	public Organizer findUserByUsername(String username);
	
}
