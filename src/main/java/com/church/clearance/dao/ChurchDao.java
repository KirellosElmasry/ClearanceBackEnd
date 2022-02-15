package com.church.clearance.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.Church;

@Transactional
@Repository
public interface ChurchDao  extends CrudRepository<Church, Integer>,
PagingAndSortingRepository<Church, Integer>{

}
