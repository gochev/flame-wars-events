package com.example.repository;

import com.example.model.Submission;
import com.example.model.Talk;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "submissions") 
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {

}
