package com.church.clearance.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class ResultReturn implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private LinkedHashMap<String,Object> res=new LinkedHashMap<String,Object> ();

	public LinkedHashMap<String, Object> getRes() {
		return res;
	}

	public void setRes(LinkedHashMap<String, Object> res) {
		this.res = res;
	}
	
//	private Hashtable<String,Object> res=new Hashtable<String,Object> ();
//
//	public Hashtable<String, Object> getRes() {
//		return res;
//	}
//
//	public void setRes(Hashtable<String, Object> res) {
//		this.res = res;
//	}
	
	
	

}
