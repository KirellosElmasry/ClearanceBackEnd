package com.church.clearance.dao;


import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.PreviousEngagment;


@Transactional
@Repository
public interface PreviousEngagmentDao extends CrudRepository<PreviousEngagment, Integer>,
PagingAndSortingRepository<PreviousEngagment, Integer>{
	
	
	@Query("from PreviousEngagment p where p.engagmentDate=:engagmentDate and p.clearance.refNo=:refNo ")
	public PreviousEngagment findByEngagmentDate_RefNo(@Param("engagmentDate")Date engagmentDate,@Param("refNo")String refNo);

}
