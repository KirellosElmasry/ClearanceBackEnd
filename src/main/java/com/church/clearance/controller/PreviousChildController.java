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
import com.church.clearance.model.PreviousChildren;
import com.church.clearance.model.PreviousMarrageRequest;
import com.church.clearance.model.ResultReturn;
import com.church.clearance.service.PreviousChildService;

@RestController
public class PreviousChildController {
	
	@Autowired
	PreviousChildService previousChildService;

	@Autowired
	HelperMethods helper;
	
	@Autowired
	ResponseMap responseMap;
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/addPreviousChild",method=RequestMethod.POST
	
			,consumes = {MediaType.APPLICATION_JSON_VALUE}
			,produces = MediaType.APPLICATION_JSON_VALUE )
 @ResponseBody
	public Map<?,?> addPreviousChild(@RequestBody PreviousChildren previousChildren){
		
		ResultReturn result=new ResultReturn();
		
		result = previousChildService.addPreviousChild(previousChildren);
		
		if (result.getRes().get("PreviousChild")!=null )
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
