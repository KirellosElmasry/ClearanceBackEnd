package com.church.clearance.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.Clearance;
import com.church.clearance.entities.Users;



@Transactional
@Repository
public interface ClearanceDao extends CrudRepository<Clearance, String>,
PagingAndSortingRepository<Clearance, String>{
	
	@Query(value="select IFNULL(max(substr(ref_No,position('/'IN ref_NO)+1)),0) as maxcl from Clearance where substr(ref_No,0,position('/'IN ref_NO)) = :year",nativeQuery = true)
	public  Integer findMaxClearanceId (@Param("year")String year);
	
	@Query("select c from Clearance c where c.personalData.emirateId=:emirateId ")
	public Iterable<Clearance> findByEmirateId(@Param("emirateId")String emirateId );
	
	@Query("select c from Clearance c where c.personalData.emirateId=:emirateId  and upper(c.Status) = 'DRAFT'")
	public Clearance findByEmirateIdAndStatus(@Param("emirateId")String emirateId );


}
