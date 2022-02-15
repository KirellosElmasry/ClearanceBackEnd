package com.church.clearance.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.dao.ChurchDao;
import com.church.clearance.dao.ClearanceDao;
import com.church.clearance.dao.PreviousEngagmentDao;
import com.church.clearance.dao.UsersDao;
import com.church.clearance.entities.Church;
import com.church.clearance.entities.Clearance;
import com.church.clearance.entities.PreviousEngagment;
import com.church.clearance.entities.Users;
import com.church.clearance.model.ClrancStepOneReqModel;
import com.church.clearance.model.PreviousEngagmentRequst;
import com.church.clearance.model.ResultReturn;

@Service
public class PreviousEngagmentService {
	
	@Autowired
	PreviousEngagmentDao previousEngagmentDao;
	
	@Autowired
	ChurchDao churchDao;
	
	@Autowired
	UsersDao userDao;
	
	@Autowired
	ClearanceDao clearanceDao;
	
public ResultReturn addPreviousEngagment (PreviousEngagmentRequst previousEngagmentRequst){
		
		ResultReturn res=new ResultReturn();
		LinkedHashMap<String,Object> t=validationPreviousEngagment(previousEngagmentRequst);
		
		if (t.size()>0){
			
			res.setRes(t);

			return res;
		}
		else{
			
			PreviousEngagment pg = new PreviousEngagment();
			
			pg.setEngagmentDate(previousEngagmentRequst.getEngagmentDate());
			pg.setClearance(clearanceDao.findOne(previousEngagmentRequst.getRefNo()));
			pg.setEngagmentPlace(previousEngagmentRequst.getEngagmentPlace());
			pg.setPriestFather(previousEngagmentRequst.getPriestFather());
			pg.setStatus(previousEngagmentRequst.getStatus());
			pg.setEngagmentFileAttachment(previousEngagmentRequst.getEngagmentFileAttachment());
			pg.setAnnulmentEngagmentAttachmnet(previousEngagmentRequst.getAnnulmentEngagmentAttachmnet());
			pg = previousEngagmentDao.save(pg);
			
			res.getRes().put("PreviousEngagment", pg);
			
			return res;
			
		}
}

	public LinkedHashMap<String, Object> validationPreviousEngagment(
			PreviousEngagmentRequst previousEngagmentRequst) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

		if (previousEngagmentRequst.getEngagmentDate() == null) {

			r.put("20", "engagment date can't be null");
		}
		
		if (previousEngagmentRequst.getEngagmentPlace() == null
				|| previousEngagmentRequst.getEngagmentPlace().equals("")) {

			r.put("21", "engagment Place can't be null");
		}
		if (previousEngagmentRequst.getPriestFather() == null
				|| previousEngagmentRequst.getPriestFather().equals("")) {

			r.put("22", "engagment PriestFather can't be null");
		}
		if (previousEngagmentRequst.getStatus() == null
				|| previousEngagmentRequst.getStatus().equals("")) {

			r.put("22", "engagment Status can't be null");
		}
		// ///////////////refNo///////////////////

		if (previousEngagmentRequst.getRefNo() == null
				|| previousEngagmentRequst.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(previousEngagmentRequst
					.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel")
					|| cl.getStatus().equals("Active")) {
				r.put("18",
						"can't update egagment for active or cancel clearance");
			}else {
				PreviousEngagment pg = previousEngagmentDao.findByEngagmentDate_RefNo(previousEngagmentRequst.getEngagmentDate(),previousEngagmentRequst.getRefNo());
				if(pg != null){
					r.put("25", "engagment found by the same date and same refNo");
				}
			}
			// ////////////user////////////////////
			if (previousEngagmentRequst.getUserId() == null
					|| previousEngagmentRequst.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(previousEngagmentRequst
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
							r.put("22",
									"user can't add engagment for clearance for church "
											+ ch.getChurchNameEn());
						} else {
							r.put("23",
									"user  can't add engagment for clearance for church ");
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
