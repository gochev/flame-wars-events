package com.example.repository;

import com.example.model.Talk;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nikolayrusev on 6/28/16.
 */
public interface TalkRepository extends PagingAndSortingRepository<Talk, Long> {

}
