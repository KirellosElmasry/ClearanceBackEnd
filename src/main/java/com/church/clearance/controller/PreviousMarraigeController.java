package com.church.clearance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.config.ResponseMap;
import com.church.clearance.model.PreviousMarrageRequest;
import com.church.clearance.model.ResultReturn;
import com.church.clearance.service.PreviousMarrageService;

@RestController
public class PreviousMarraigeController {
	
	@Autowired
	PreviousMarrageService previousMarrageService;
	
	@Autowired
	HelperMethods helper;
	
	@Autowired
	ResponseMap responseMap;
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/addPreviousMarrage",method=RequestMethod.POST
	
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = MediaType.APPLICATION_JSON_VALUE )
 @ResponseBody
	public Map<?,?> addPreviousMarrage(@RequestBody PreviousMarrageRequest previousMarrageRequest){
		
		ResultReturn result=new ResultReturn();
		
		result = previousMarrageService.addPreviousMarrage(previousMarrageRequest);
		
		if (result.getRes().get("PreviousMarrage")!=null )
		  {              		 
				 return responseMap.success(result,"200");
			// return new ResponseEntity<User>(result, HttpStatus.OK);
		  }
		  else
		  {
			  return responseMap.fail(result,"4000");
		  }
	}

}
