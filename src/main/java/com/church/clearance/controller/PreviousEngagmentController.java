package com.church.clearance.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.config.ResponseMap;
import com.church.clearance.model.PersonalDataRequest;
import com.church.clearance.model.PreviousEngagmentRequst;
import com.church.clearance.model.ResultReturn;
import com.church.clearance.service.PreviousEngagmentService;

@RestController
public class PreviousEngagmentController {
	
	@Autowired
	PreviousEngagmentService  previousEngagmentService;
	
	@Autowired
	HelperMethods helper;
	
	@Autowired
	ResponseMap responseMap;
	
	@CrossOrigin
	@RequestMapping(value="/api/v1/addPreviousEngagment",method=RequestMethod.POST
	
			,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}
			,produces = MediaType.APPLICATION_JSON_VALUE )
 @ResponseBody
	public Map<?,?> uploadFile(	@RequestParam("engAttach") MultipartFile  engAttach ,
			                    @RequestParam("anulAttach") MultipartFile  anulAttach,
			                    @RequestParam (value = "refNo") String refNo,
			                    @RequestParam (value = "userId") Integer userId,
			                    @RequestParam (value = "engagmentDate") String engagmentDate,
			                    @RequestParam (value = "engagmentPlace") String engagmentPlace,
			                    @RequestParam (value = "priestFather") String priestFather,
			                    @RequestParam (value = "status") String status) {
		ResultReturn result=new ResultReturn();

		String root = System.getProperty("user.dir");
		 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
		 String UPLOADED_FOLDER = root+filepath+"PreviousEng";
		 
		 try {
				helper.createFolderIfNotExists(UPLOADED_FOLDER);
			} catch (SecurityException se) {
				
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not create destination folder on server");
				return result.getRes();
			}
		// String ref = refNo.substring(0,refNo.indexOf("/"))+"-"+refNo.substring(refNo.indexOf("/")+1);
		 String ref = refNo;
		 if(engAttach != null){
		
		 String EnguploadedFileLocation = UPLOADED_FOLDER + File.separator + ref+"#eng"+engagmentDate+engAttach.getOriginalFilename().substring(engAttach.getOriginalFilename().indexOf("."));
		 try {
				helper.saveToFile(engAttach.getInputStream(), EnguploadedFileLocation);
			} catch (IOException e) {
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not save file");
				return result.getRes();
		
			}
		 }
		 if( anulAttach != null){
			
		 String AnuluploadedFileLocation = UPLOADED_FOLDER + File.separator + ref+"#anul"+engagmentDate+anulAttach.getOriginalFilename().substring(anulAttach.getOriginalFilename().indexOf("."));
		 try {
				helper.saveToFile(anulAttach.getInputStream(), AnuluploadedFileLocation);
			} catch (IOException e) {
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Can not save file");
				return result.getRes();
		
			}
		 }
			
			PreviousEngagmentRequst previousEngagmentRequst = new PreviousEngagmentRequst();
			previousEngagmentRequst.setEngagmentDate(helper.getDate(engagmentDate));
			previousEngagmentRequst.setEngagmentPlace(engagmentPlace);
			previousEngagmentRequst.setPriestFather(priestFather);
			previousEngagmentRequst.setRefNo(refNo);
			previousEngagmentRequst.setUserId(userId);
			previousEngagmentRequst.setStatus(status);
			previousEngagmentRequst.setEngagmentFileAttachment(UPLOADED_FOLDER + File.separator + ref+"#eng"+engagmentDate+engAttach.getOriginalFilename().substring(engAttach.getOriginalFilename().indexOf(".")));
			previousEngagmentRequst.setAnnulmentEngagmentAttachmnet(UPLOADED_FOLDER + File.separator + ref+"#anul"+engagmentDate+anulAttach.getOriginalFilename().substring(anulAttach.getOriginalFilename().indexOf(".")));
			
			result= previousEngagmentService.addPreviousEngagment(previousEngagmentRequst);
			
			if (result.getRes().get("PreviousEngagment")!=null )
			  {              		 
					 return responseMap.success(result,"200");
				// return new ResponseEntity<User>(result, HttpStatus.OK);
			  }
			  else
			  {
				  if(result.getRes().get("25")== null ){
				  System.out.println(UPLOADED_FOLDER + File.separator + ref+"#eng" +engagmentDate + engAttach.getOriginalFilename().substring(engAttach.getOriginalFilename().indexOf(".")));
				  File eng= new File(UPLOADED_FOLDER + File.separator + ref+"#eng" +engagmentDate + engAttach.getOriginalFilename().substring(engAttach.getOriginalFilename().indexOf(".")));
				  File anul= new File(UPLOADED_FOLDER + File.separator + ref+"#anul"+engagmentDate + anulAttach.getOriginalFilename().substring(anulAttach.getOriginalFilename().indexOf(".")));
				  eng.delete();
				  anul.delete();
				  }
					  return responseMap.fail(result,"4000");

		}
		
	}

}
