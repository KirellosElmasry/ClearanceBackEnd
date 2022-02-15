package com.church.clearance.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.Child;


@Transactional
@Repository
public interface ChildDao extends CrudRepository<Child, Integer>,
PagingAndSortingRepository<Child, Integer>{
	
	@Query("from Child c where c.childName=:childName and c.clearance.refNo=:refNo ")
	public Child findByChildName_RefNo(@Param("childName")String childName,@Param("refNo")String refNo);

}
