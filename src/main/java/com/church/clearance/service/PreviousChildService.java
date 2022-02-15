package com.church.clearance.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.dao.ChildDao;
import com.church.clearance.dao.ChurchDao;
import com.church.clearance.dao.ClearanceDao;
import com.church.clearance.dao.UsersDao;
import com.church.clearance.entities.Child;
import com.church.clearance.entities.Church;
import com.church.clearance.entities.Clearance;
import com.church.clearance.entities.Users;
import com.church.clearance.model.PreviousChildren;
import com.church.clearance.model.ResultReturn;

@Service
public class PreviousChildService {
	
	@Autowired
	ChildDao childDao;
	
	@Autowired
	ChurchDao churchDao;
	
	@Autowired
	UsersDao userDao;
	
	@Autowired
	ClearanceDao clearanceDao;
	
public ResultReturn addPreviousChild (PreviousChildren previousChildren){
		
		ResultReturn res=new ResultReturn();
		LinkedHashMap<String,Object> t=validationPreviousChild(previousChildren);
		
		if (t.size()>0){
			
			res.setRes(t);

			return res;
		}
		else{
			
			Child ch = new Child();
			Clearance cl = clearanceDao.findOne(previousChildren.getRefNo());
			ch.setChildName(previousChildren.getChildName());
			ch.setChildAge(previousChildren.getChildAge());
			ch.setBaptism(previousChildren.getBaptism());
			ch.setClearance(cl);
			childDao.save(ch);			
			cl.setNumOfChildern(cl.getChilds().size());
			clearanceDao.save(cl);
			
			res.getRes().put("PreviousChild", ch);
			
			return res;
			
		}
}

	public LinkedHashMap<String, Object> validationPreviousChild(
			PreviousChildren previousChildren) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

		if (previousChildren.getChildName() == null
				|| previousChildren.getChildName().equals("")) {

			r.put("43", "childName can't be null");
		}
		
		if (previousChildren.getBaptism() == null
				|| previousChildren.getBaptism().equals("")) {

			r.put("44", "childName can't be null");
		}
		if (previousChildren.getChildAge()== null 
				||previousChildren.getChildAge() <= 0) {

			r.put("45", "childAge can't be null or be less than 0");
		}
		
		// ///////////////refNo///////////////////

		if (previousChildren.getRefNo() == null
				|| previousChildren.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(previousChildren
					.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel")
					|| cl.getStatus().equals("Active")) {
				r.put("18",
						"can't update child for active or cancel clearance");
			}else {
				Child chd = childDao.findByChildName_RefNo(previousChildren.getChildName(),previousChildren.getRefNo());
				if(chd != null){
					r.put("46", "child found by the same Name and same refNo");
				}
			}
			// ////////////user////////////////////
			if (previousChildren.getUserId() == null
					|| previousChildren.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(previousChildren
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
							r.put("47",
									"user can't add child for clearance for church "
											+ ch.getChurchNameEn());
						} else {
							r.put("48",
									"user  can't add child for clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("49",
							"user can't add child for clearance not have privilage");
				}
			}
		}
		return r;
	}

}
