package com.church.clearance.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.church.clearance.dao.ClearanceDao;
import com.church.clearance.model.ResultReturn;

@Configuration
public class HelperMethods {
	private static final Logger log = LoggerFactory.getLogger(HelperMethods.class);
	
	@Autowired
	ClearanceDao clearanceDao;
	
public Date getDate(String done){
	 
		
		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		Date d1=null;
		try {
		    d1=df.parse(done);
			System.out.println(d1);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d1;
	}

public Date getDateFormate(Date done){
	 
	
	String pattern = "dd-MM-yyyy";
	DateFormat df = new SimpleDateFormat(pattern);
	Date d1=null;
	try {
	    d1=df.parse(df.format(done));
		System.out.println(d1);
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return d1;
}

/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	public void saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws SecurityException
	 *             - in case you don't have permission to create the folder
	 */
	public void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
		
		
	}
	
	 public Resource getFileAsResource(String filename) throws FileNotFoundException {
		 String root = System.getProperty("user.dir");
		 String filepath = File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator ;
		 String UPLOADED_FOLDER = root+filepath;
				 
	        String f = UPLOADED_FOLDER +  File.separator + filename;
	        Resource file = loadAsResource(f);
	        return file;
	    }
	 
	 public Resource loadAsResource(String filename) throws FileNotFoundException {
	        try {
	            Path file = Paths.get(filename);
	            org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
	            if (resource.exists() || resource.isReadable()) {
	                return resource;
	            } else {
	                log.error("Could not read file: " + filename);
	                throw new FileNotFoundException();
	            }
	        } catch (MalformedURLException e) {
	            log.error("Could not read file: " + filename, e);
	            throw new FileNotFoundException();
	        }
	    }

	 public ResultReturn validateEid(String eid){
			
			if(!eid.startsWith("784")|| eid.length()!=15){
				
				ResultReturn result = new ResultReturn();
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Invalid EmirateId");
				
				return result;
				
			}
			else{
				return null;
			}
		}
	 
	 public ResultReturn validateEidWithBirthdate(String eid,String Birthdate){
			
			if(!eid.startsWith("784")|| eid.length()!=15){
				
				ResultReturn result = new ResultReturn();
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Invalid EmirateId");
				
				return result;
				
			}
			else if(!eid.substring(3,7).equals(Birthdate.substring(0,Birthdate.indexOf("-")))){
				
				ResultReturn result = new ResultReturn();
				result.getRes().put("code", 4000);
				result.getRes().put("msg","Invalid EmirateId with birthdate");
				
				return result;
			}
			else{
				return null;
			}
		}
	 private int getYearFromDate(){
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 
		return year; 
	 }
	 
	 public String getClearanceId(){
		 
			/*
			 * int year= getYearFromDate();
			 * 
			 * Integer clid= clearanceDao.findMaxClearanceId(String.valueOf(year)); clid =
			 * clid+1;
			 * 
			 * 
			 * return year+"/"+clid;
			 */
		 
		 return String.valueOf( new Date().getTime());
		 
	 }
	 
}
