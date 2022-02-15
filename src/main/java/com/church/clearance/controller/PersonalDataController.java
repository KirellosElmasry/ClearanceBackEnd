package com.church.clearance.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.swing.Box.Filler;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.model.PersonalDataRequest;
import com.church.clearance.model.ResultReturn;
import com.church.clearance.service.PersonalDataService;

@RestController
public class PersonalDataController {
	
	@Autowired
	PersonalDataService  personalDataService;
	
	@Autowired
	HelperMethods helper;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/v1/getSearchByEid",method=RequestMethod.GET
			,consumes = MediaType.APPLICATION_JSON_VALUE
			,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Map<?,?> getSearchByEid (@Param(value = "eid")  String eid){
		
		return personalDataService.getPeronalData(eid).getRes();
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/addPersonalData",method=RequestMethod.POST
	
			,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}
			,produces = MediaType.APPLICATION_JSON_VALUE )
 @ResponseBody
	public Map<?,?> uploadFile(	@RequestParam("file") MultipartFile  pic ,
			                    @RequestParam (value = "emirateId") String emirateId,
			                    @RequestParam (value = "name") String name,
			                    @RequestParam (value = "birthDate") String birthDate,
			                    @RequestParam (value = "birthLocation") String birthLocation,
			                    @RequestParam (value = "baptism") String baptism,
			                    @RequestParam (value = "baptismPlace") String baptismPlace,
			                    @RequestParam (value = "education") String education,
			                    @RequestParam (value = "educationDate") String educationDate) {
		ResultReturn result=new ResultReturn();
		
		if(helper.validateEidWithBirthdate(emirateId, birthDate)== null){
		String root = System.getProperty("user.dir");
		 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
		 String UPLOADED_FOLDER = root+filepath+"PersonalPhoto";
		 
		 try {
				helper.createFolderIfNotExists(UPLOADED_FOLDER);
			} catch (SecurityException se) {
				
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not create destination folder on server");
				return result.getRes();
			}
		 
		 String uploadedFileLocation = UPLOADED_FOLDER + File.separator + emirateId+pic.getOriginalFilename().substring(pic.getOriginalFilename().indexOf("."));
			try {
				helper.saveToFile(pic.getInputStream(), uploadedFileLocation);
			} catch (IOException e) {
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not save file");
				return result.getRes();
		
			}
			PersonalDataRequest personalDataRequest = new PersonalDataRequest();
			personalDataRequest.setEmirateId(emirateId);
			personalDataRequest.setBaptism(baptism);
			personalDataRequest.setBirthDate(helper.getDate(birthDate));
			personalDataRequest.setBaptismPlace(baptismPlace);
			personalDataRequest.setBirthLocation(birthLocation);
			personalDataRequest.setEducation(education);
			personalDataRequest.setEducationDate(helper.getDate(educationDate));
			personalDataRequest.setPic(emirateId+pic.getOriginalFilename().substring(pic.getOriginalFilename().indexOf(".")));
			personalDataRequest.setName(name);
			
			
			
			return personalDataService.addPeronalData(personalDataRequest).getRes();
		}else{
			
			return helper.validateEidWithBirthdate(emirateId, birthDate).getRes();
		}
			
		
	}

}
