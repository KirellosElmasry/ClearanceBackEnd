package com.church.clearance.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.PreviousEngagment;
import com.church.clearance.entities.PreviousMarriage;


@Transactional
@Repository
public interface PreviousMarriageDao extends CrudRepository<PreviousMarriage, Integer>,
PagingAndSortingRepository<PreviousMarriage, Integer>{
	
	@Query("from PreviousMarriage p where p.marriageDate=:marriageDate and p.clearance.refNo=:refNo ")
	public PreviousMarriage findByMarriageDate_RefNo(@Param("marriageDate")Date marriageDate,@Param("refNo")String refNo);


}
