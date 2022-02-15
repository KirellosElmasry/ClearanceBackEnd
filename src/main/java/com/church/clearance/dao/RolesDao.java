package com.church.clearance.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.Roles;


@Transactional
@Repository
public interface RolesDao extends CrudRepository<Roles, Integer>,
PagingAndSortingRepository<Roles, Integer>{

}
