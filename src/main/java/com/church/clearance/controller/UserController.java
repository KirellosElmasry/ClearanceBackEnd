package com.church.clearance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.church.clearance.model.LoginRequest;
import com.church.clearance.service.LoginService;

@RestController
public class UserController {
	
	@Autowired
	LoginService ls;
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/getUserById",method=RequestMethod.GET
			,consumes = MediaType.APPLICATION_JSON_VALUE
			,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Map<?,?> getUserSpec (@Param(value = "id")  int id){
		
		return ls.getUserbyId(id).getRes();
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/getUserByUserName",method=RequestMethod.POST
			,consumes = MediaType.APPLICATION_JSON_VALUE
			,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Map<?,?> getUserByUserName (@RequestBody   LoginRequest loginRquest){
		
		return ls.getUserbyUserNamePassword(loginRquest.getUserName(),loginRquest.getPassword()).getRes();
		
	}

}
