package com.church.clearance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.dao.ChurchDao;
import com.church.clearance.entities.Church;
import com.church.clearance.model.ResultReturn;

@Service
public class ChurchService {
	
	@Autowired
	ChurchDao churchDao;
	
	public ResultReturn getAllChurch(){
		
		ResultReturn result = new ResultReturn();
		
	List<Church> allChurch=	(List<Church>)churchDao.findAll();
	
	if(allChurch.size()>0){
		
		result.getRes().put("code", 200);
		result.getRes().put("allChurch", allChurch);
		
	}
	else{
		result.getRes().put("code", 4000);
		result.getRes().put("allChurch", "Not Found");
	}
	return result;
		
	}

}
