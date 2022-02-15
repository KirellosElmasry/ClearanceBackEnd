package com.church.clearance.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.PersonalData;


@Transactional
@Repository
public interface PersonalDataDao extends CrudRepository<PersonalData, String>,
PagingAndSortingRepository<PersonalData, String>{

}
