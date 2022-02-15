package com.church.clearance.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Configuration
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseMap {
	
	public Map<?, ?> success(Object result,String code)
	{

		Map<String, Object> success = new LinkedHashMap<String, Object>();
		success.put("status", "success");
		success.put("code", code);
		success.put("result", result);
		
		return success;
		
	}
	
	public Map<?, ?> fail(Object result,String code)
	{

		Map<String, Object> success = new LinkedHashMap<String, Object>();
		success.put("status", "fail");
		success.put("code", code);
		success.put("result", result);
		
		
		return success;
		
	}
	
	public Map<?, ?> fail2(Object result,String code)
	{

		Map<String, Object> success = new LinkedHashMap<String, Object>();
		success.put("status", "fail");
	//	success.put("code", code);
		success.put("result", result);
		
		
		return success;
		
	}
	
	@Bean
	public ResponseMap responsemap()
	{
		return new ResponseMap();
	} 
	

}
