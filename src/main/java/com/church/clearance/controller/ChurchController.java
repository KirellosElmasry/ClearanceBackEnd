package com.church.clearance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.church.clearance.service.ChurchService;

@RestController
public class ChurchController {
	
	@Autowired
	ChurchService churchService;
	
	 @CrossOrigin
		@RequestMapping(value="/api/v1/getAllChurch",method=RequestMethod.POST
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> getAllChurch (){
		 
		 return churchService.getAllChurch().getRes();
	 }


}
