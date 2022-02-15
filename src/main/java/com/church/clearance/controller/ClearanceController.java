package com.church.clearance.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;






















import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.config.ResponseMap;
import com.church.clearance.model.ClrancAddPrintedReq;
import com.church.clearance.model.ClrancCancelReq;
import com.church.clearance.model.ClrancStepOneReqModel;
import com.church.clearance.model.ClrncStepFiveReq;
import com.church.clearance.model.ClrncStepFourReq;
import com.church.clearance.model.ClrncStepThreeReq;
import com.church.clearance.model.ClrncStepTwoReq;
import com.church.clearance.model.LoginRequest;
import com.church.clearance.model.PersonalDataRequest;
import com.church.clearance.model.ResultReturn;
import com.church.clearance.service.ClearenceService;

@RestController
public class ClearanceController {
	
	@Autowired
	HelperMethods helper;
	
	@Autowired
	ClearenceService clearanceService;
	
	@Autowired
	ResponseMap responseMap;
	
	private static final Logger log = LoggerFactory.getLogger(ClearanceController.class);
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/testSaveFile",method=RequestMethod.POST
		
				,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}
				,produces = MediaType.APPLICATION_JSON_VALUE )
	 @ResponseBody
		public Map<?,?> uploadFile(
				
				@RequestParam("file") MultipartFile  fileDetail) {
		 
		 ResultReturn result=new ResultReturn();
		 
		 String root = System.getProperty("user.dir");
		 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
		 String UPLOADED_FOLDER = root+filepath+"test";
		 
			try {
				helper.createFolderIfNotExists(UPLOADED_FOLDER);
			} catch (SecurityException se) {
				
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not create destination folder on server");
				return result.getRes();
			}
			String uploadedFileLocation = UPLOADED_FOLDER + File.separator + fileDetail.getOriginalFilename();
			try {
				helper.saveToFile(fileDetail.getInputStream(), uploadedFileLocation);
			} catch (IOException e) {
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not save file");
				return result.getRes();
		
			}
			result.getRes().put("code", 200);
			result.getRes().put("msg","File saved to " + uploadedFileLocation);
			return result.getRes();
			
		}

	 @CrossOrigin
		@RequestMapping(value="/api/v1/getfileByName",method=RequestMethod.GET
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> getUserSpec (@Param(value = "name")  String name){
			
		 ResultReturn result=new ResultReturn();
		try {
			Resource f= helper.getFileAsResource(name);
			
			byte[] fileContent = FileUtils.readFileToByteArray(f.getFile());
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			result.getRes().put("code", 200);
			result.getRes().put("img",encodedString);
			return result.getRes();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.getRes().put("code", 4000);
		result.getRes().put("msg","error to return file");
		return result.getRes();	
		}
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/addNewClearance",method=RequestMethod.POST
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> addNewClearance (@RequestBody   ClrancStepOneReqModel clrancStepOne){
			
		 ResultReturn result = new ResultReturn();
			
			result = clearanceService.createFirstStepClearance(clrancStepOne);
			
			 if (result.getRes().get("Clearance")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  
					  return responseMap.fail(result,"4000");

		}
	 
	 }
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/updateEngagmentClearance",method=RequestMethod.POST
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> updateEngagmentClearance (@RequestBody ClrncStepTwoReq clrncStepTwoReq){
			
		 ResultReturn result = new ResultReturn();
			
			result = clearanceService.upgateClearanceEngagment(clrncStepTwoReq);
			
			 if (result.getRes().get("updateEngagment")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  
					  return responseMap.fail(result,"4000");

		}
	 
	 }	
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/updateMarrageClearance",method=RequestMethod.POST
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> updateMarrageClearance (@RequestBody ClrncStepThreeReq clrncStepThreeReq){
			
		 ResultReturn result = new ResultReturn();
			
			result = clearanceService.upgateClearanceMarriage(clrncStepThreeReq);
			
			 if (result.getRes().get("updateMarrage")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  
					  return responseMap.fail(result,"4000");

		}
	 
	 }	
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/updateChildClearance",method=RequestMethod.POST
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> updateChildClearance (@RequestBody ClrncStepFourReq clrncStepFourReq){
			
		 ResultReturn result = new ResultReturn();
			
			result = clearanceService.upgateClearanceChild(clrncStepFourReq);
			
			 if (result.getRes().get("updateChild")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  
					  return responseMap.fail(result,"4000");

		}
	 
	 }	
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/updateClearanceFinal",method=RequestMethod.POST
				,consumes = MediaType.APPLICATION_JSON_VALUE
				,produces = MediaType.APPLICATION_JSON_VALUE )
		@ResponseBody
		public Map<?,?> updateClearanceFinal (@RequestBody ClrncStepFiveReq clrncStepFiveReq){
			
		 ResultReturn result = new ResultReturn();
			
			result = clearanceService.upgateClearanceFinal(clrncStepFiveReq);
			
			 if (result.getRes().get("updateClearance")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  
					  return responseMap.fail(result,"4000");

		}
	 
	 }
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/addPrintedFile",method=RequestMethod.POST
		
				,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}
				,produces = MediaType.APPLICATION_JSON_VALUE )
	 @ResponseBody
		public Map<?,?> uploadPrintedFile(	@RequestParam("file") MultipartFile  pic ,
				                    @RequestParam (value = "refNo") String refNo,
				                    @RequestParam (value = "userId") Integer userId) {
			ResultReturn result=new ResultReturn();
			
			String root = System.getProperty("user.dir");
			 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
			 String UPLOADED_FOLDER = root+filepath+"ClearancePhoto";
			 
			 try {
					helper.createFolderIfNotExists(UPLOADED_FOLDER);
				} catch (SecurityException se) {
					
					result.getRes().put("code", 4000);
					result.getRes().put("msg","Can not create destination folder on server");
					return result.getRes();
				}
			 String ref = refNo.substring(0,refNo.indexOf("/"))+"-"+refNo.substring(refNo.indexOf("/")+1);
			 if(pic != null){
				
			 String uploadedFileLocation = UPLOADED_FOLDER + File.separator + ref+pic.getOriginalFilename().substring(pic.getOriginalFilename().indexOf("."));
				try {
					helper.saveToFile(pic.getInputStream(), uploadedFileLocation);
				} catch (IOException e) {
					result.getRes().put("code", 4000);
					result.getRes().put("msg","Can not save file");
					return result.getRes();
			
				}
			 }
			    ClrancAddPrintedReq clrancAddPrintedReq = new ClrancAddPrintedReq();
			    clrancAddPrintedReq.setRefNo(refNo);
			    clrancAddPrintedReq.setUserId(userId);
			    clrancAddPrintedReq.setPrintedClearance(UPLOADED_FOLDER + File.separator +ref+pic.getOriginalFilename().substring(pic.getOriginalFilename().indexOf(".")));
			   
			    result = clearanceService.upgateClearancePrintedFile(clrancAddPrintedReq);
				
			    if (result.getRes().get("updateClearance")!=null )
				  {              		 
						 return responseMap.success(result,"200");
					// return new ResponseEntity<User>(result, HttpStatus.OK);
				  }
				  else
				  {
					  
					  File pi= new File(UPLOADED_FOLDER + File.separator +ref+pic.getOriginalFilename().substring(pic.getOriginalFilename().indexOf(".")));
					  pi.delete();
					
					  }
						  return responseMap.fail(result,"4000");
			
		}
	 
	 @CrossOrigin
		@RequestMapping(value="/api/v1/cancelClearance",method=RequestMethod.POST
		
				,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}
				,produces = MediaType.APPLICATION_JSON_VALUE )
	 @ResponseBody
		public Map<?,?> uploadCancelClearance(	@RequestParam(value ="original", required=false)  MultipartFile  original ,
				                                @RequestParam(value = "eng" , required=false) MultipartFile  eng ,
				                                @RequestParam(value = "annul" , required=false) MultipartFile  annul ,
				                                @RequestParam (value = "refNo") String refNo,
				                                @RequestParam (value = "userId") Integer userId) {
			ResultReturn result=new ResultReturn();
			
			String root = System.getProperty("user.dir");
			 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
			 String UPLOADED_FOLDER = root+filepath+"CancelClearance";
			 
			 try {
					helper.createFolderIfNotExists(UPLOADED_FOLDER);
				} catch (SecurityException se) {
					
					result.getRes().put("code", 4000);
					result.getRes().put("msg","Can not create destination folder on server");
					return result.getRes();
				}
			 String ref = refNo.substring(0,refNo.indexOf("/"))+"-"+refNo.substring(refNo.indexOf("/")+1);
			 if(original != null){
				
			 String uploadedFileLocation = UPLOADED_FOLDER + File.separator + ref+"#org"+original.getOriginalFilename().substring(original.getOriginalFilename().indexOf("."));
				try {
					helper.saveToFile(original.getInputStream(), uploadedFileLocation);
				} catch (IOException e) {
					result.getRes().put("code", 4000);
					result.getRes().put("msg","Can not save file");
					return result.getRes();
			
				}
			 }
			 else if( eng !=null && annul != null){
				 
				 String uploadedFileLocationEng = UPLOADED_FOLDER + File.separator + ref+"#eng"+eng.getOriginalFilename().substring(eng.getOriginalFilename().indexOf("."));
				 String uploadedFileLocationAnnul = UPLOADED_FOLDER + File.separator + ref+"#anul"+annul.getOriginalFilename().substring(annul.getOriginalFilename().indexOf("."));
					
					try {
						helper.saveToFile(eng.getInputStream(), uploadedFileLocationEng);
						helper.saveToFile(annul.getInputStream(), uploadedFileLocationAnnul);
					} catch (IOException e) {
						result.getRes().put("code", 4000);
						result.getRes().put("msg","Can not save file");
						return result.getRes();
				
					} 
				 
			 }
			     ClrancCancelReq clrancCancelReq = new ClrancCancelReq();
			     clrancCancelReq.setRefNo(refNo);
			     clrancCancelReq.setUserId(userId);
			     if(original != null){
			     clrancCancelReq.setOriginalClearance(UPLOADED_FOLDER + File.separator +ref+"#org"+original.getOriginalFilename().substring(original.getOriginalFilename().indexOf(".")));
			     }
			     else if(eng !=null && annul != null){
			    	 clrancCancelReq.setEngClearance(UPLOADED_FOLDER + File.separator +ref+"#eng"+eng.getOriginalFilename().substring(eng.getOriginalFilename().indexOf(".")));
			    	 clrancCancelReq.setAnnulClearance(UPLOADED_FOLDER + File.separator +ref+"#anul"+annul.getOriginalFilename().substring(annul.getOriginalFilename().indexOf(".")));
			     }
			    result = clearanceService.cancelClearance(clrancCancelReq);
				
			    if (result.getRes().get("cancelClearance")!=null )
				  {              		 
						 return responseMap.success(result,"200");
					// return new ResponseEntity<User>(result, HttpStatus.OK);
				  }
				  else
				  {
					  if(original != null){
					  File pi= new File(UPLOADED_FOLDER + File.separator +ref+"#org"+original.getOriginalFilename().substring(original.getOriginalFilename().indexOf(".")));
					  pi.delete();
					  }else if (eng !=null && annul != null){
						  
						  File en= new File(UPLOADED_FOLDER + File.separator +ref+"#eng"+eng.getOriginalFilename().substring(eng.getOriginalFilename().indexOf(".")));
						  File an= new File(UPLOADED_FOLDER + File.separator +ref+"#anul"+annul.getOriginalFilename().substring(annul.getOriginalFilename().indexOf(".")));
						  en.delete(); 
						  an.delete();
						  
					  }
					
					  }
						  return responseMap.fail(result,"4000");
			
		}
	 
}
