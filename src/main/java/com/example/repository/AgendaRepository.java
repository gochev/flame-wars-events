package com.example.repository;

import com.example.model.Agenda;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nikolayrusev on 6/28/16.
 */
public interface AgendaRepository extends PagingAndSortingRepository<Agenda,Long> {
}
