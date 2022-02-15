package com.church.clearance.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.church.clearance.entities.Users;

@Transactional
@Repository
public interface UsersDao extends CrudRepository<Users, Integer>,
PagingAndSortingRepository<Users, Integer>{
	
	@Query("from Users  u where u.userName=:userName and u.password=:password ")
	public  Users findUserByUserNameAndPass (@Param("userName")String userName,@Param("password")String password);

//Iterable<EmailSession>
}
