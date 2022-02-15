package com.church.clearance.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.dao.ChurchDao;
import com.church.clearance.dao.ClearanceDao;
import com.church.clearance.dao.PreviousMarriageDao;
import com.church.clearance.dao.UsersDao;
import com.church.clearance.entities.Church;
import com.church.clearance.entities.Clearance;
import com.church.clearance.entities.PreviousEngagment;
import com.church.clearance.entities.PreviousMarriage;
import com.church.clearance.entities.Users;
import com.church.clearance.model.PreviousMarrageRequest;
import com.church.clearance.model.ResultReturn;

@Service
public class PreviousMarrageService {
	
	@Autowired
	ChurchDao churchDao;
	
	@Autowired
	UsersDao userDao;
	
	@Autowired
	ClearanceDao clearanceDao;
	
	@Autowired
	PreviousMarriageDao previousMarriageDao;
	
	@Autowired
	HelperMethods helper;
	
public ResultReturn addPreviousMarrage (PreviousMarrageRequest previousMarrageRequest){
		
		ResultReturn res=new ResultReturn();
		LinkedHashMap<String,Object> t=validationPreviousMarrage(previousMarrageRequest);
		
		if (t.size()>0){
			
			res.setRes(t);

			return res;
		}
		else{
			
			PreviousMarriage pm = new PreviousMarriage();
			pm.setKindOfMarriage(previousMarrageRequest.getKindOfMarriage());
			pm.setMarriageDate(previousMarrageRequest.getMarriageDate());
			pm.setClearance(clearanceDao.findOne(previousMarrageRequest.getRefNo()));
			pm.setMarriagePlace(previousMarrageRequest.getMarriagePlace());
			pm.setPriestFather(previousMarrageRequest.getPriestFather());
			pm.setStatus(previousMarrageRequest.getStatus());
			pm.setCaseNo(previousMarrageRequest.getCaseNo());
			pm.setCourt(previousMarrageRequest.getCourt());
			pm.setDateOfCase(previousMarrageRequest.getDateOfCase());
			
			pm = previousMarriageDao.save(pm);
			
			res.getRes().put("PreviousMarrage", pm);
			
			return res;
		}
		
		}
		
		public LinkedHashMap<String, Object> validationPreviousMarrage(PreviousMarrageRequest previousMarrageRequest) {

			LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

			if (previousMarrageRequest.getMarriageDate() == null) {

				r.put("32", "Marrage date can't be null");
			}
			
			if (previousMarrageRequest.getMarriagePlace() == null
					|| previousMarrageRequest.getMarriagePlace().equals("")) {

				r.put("33", "marrage Place can't be null");
			}
			if (previousMarrageRequest.getKindOfMarriage() == null
					|| previousMarrageRequest.getKindOfMarriage().equals("")) {

				r.put("39", "KindOfMarriage can't be null");
			}
			if (previousMarrageRequest.getPriestFather() == null
					|| previousMarrageRequest.getPriestFather().equals("")) {

				r.put("34", "marrage PriestFather can't be null");
			}
			if (previousMarrageRequest.getStatus() == null
					|| previousMarrageRequest.getStatus().equals("")) {

				r.put("35", "marrage Status can't be null");
			}
			// ///////////////refNo///////////////////

			if (previousMarrageRequest.getRefNo() == null
					|| previousMarrageRequest.getRefNo().equals("")) {
				r.put("16", "refNo can't be null");
			} else {

				Clearance cl = clearanceDao.findOne(previousMarrageRequest
						.getRefNo());

				if (cl == null) {
					r.put("17", "Clearance not found");
				} else if (cl.getStatus().equals("Cancel")
						|| cl.getStatus().equals("Active")) {
					r.put("36",
							"can't update marrage for active or cancel clearance");
				}else {
					PreviousMarriage pg = previousMarriageDao.findByMarriageDate_RefNo(helper.getDateFormate(previousMarrageRequest.getMarriageDate()),previousMarrageRequest.getRefNo());
					if(pg != null){
						r.put("37", "marrage found by the same date and same refNo");
					}
				}
				// ////////////user////////////////////
				if (previousMarrageRequest.getUserId() == null
						|| previousMarrageRequest.getUserId().equals("")) {
					r.put("6", "user can not be null");
				} else {
					Users user = userDao.findOne(previousMarrageRequest
							.getUserId());

					if (user == null) {
						r.put("7", "user Not Found");
					}
					if (cl != null) {
						if (user.getChurch().getChurchId() != cl.getChurch()
								.getChurchId()) {
							Church ch = churchDao.findOne(cl.getChurch()
									.getChurchId());
							if (ch != null) {
								r.put("37",
										"user can't add marrage for clearance for church "
												+ ch.getChurchNameEn());
							} else {
								r.put("38",
										"user  can't add marrage for clearance for church ");
							}
						}
					} else if (user.getRoll().getRolId() != 2) {

						r.put("24",
								"user can't add engagment for clearance not have privilage");
					}
				
		}
			}
				return r;
	    

		}
}
