package com.example.repository;

import com.example.model.Talk;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by nikolayrusev on 6/28/16.
 */
@RepositoryRestResource(path = "talk") 
public interface TalkRepository extends PagingAndSortingRepository<Talk, Long> {

}
