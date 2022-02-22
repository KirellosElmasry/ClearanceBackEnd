package com.church.clearance.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.dao.PersonalDataDao;
import com.church.clearance.entities.PersonalData;
import com.church.clearance.model.PersonalDataRequest;
import com.church.clearance.model.ResultReturn;

@Service
public class PersonalDataService {

	@Autowired
	PersonalDataDao personalDataDao;

	@Autowired
	HelperMethods helper;

	public ResultReturn getPeronalData(String eid) {

		ResultReturn result = new ResultReturn();

		PersonalData personal = personalDataDao.findOne(eid);

		if (helper.validateEid(eid) != null) {

			return helper.validateEid(eid);
		}

		if (personal == null ) {

			result.getRes().put("code", 200);
			result.getRes().put("msg", "welcome you can insert your data and create new clearance");

			return result;
		} else if (personal.getClearances() != null && personal.getClearances().size() > 0 && personal.getClearances()
				.get(personal.getClearances().size() - 1).getStatus().equalsIgnoreCase("Active")) {

			result.getRes().put("code", 201);
			result.getRes().put("msg", "welcome your clearance Already Exist And Active");

			return result;
		} else if (personal.getClearances() != null && personal.getClearances().size() > 0 && personal.getClearances()
				.get(personal.getClearances().size() - 1).getStatus().equalsIgnoreCase("Cancel")) {

			result.getRes().put("code", 202);
			result.getRes().put("msg", "welcome your prev clearance Already canceled, can create another");

			return result;
		} else if (personal != null && personal.getClearances().isEmpty()
				|| (personal.getClearances().size() > 0 && personal.getClearances()
						.get(personal.getClearances().size() - 1).getStatus().equalsIgnoreCase("Draft"))) {
			result.getRes().put("personalData", personal);
			result.getRes().put("code", 203);
			result.getRes().put("msg", "welcome you can create new clearance");
		} else {
			result.getRes().put("code", 4000);
			result.getRes().put("msg", "someThing wrong");
		}
		return result;
	}

	public ResultReturn addPeronalData(PersonalDataRequest personalDataRequest) {

		ResultReturn result = new ResultReturn();

		PersonalData person = personalDataDao.findOne(personalDataRequest.getEmirateId());

		if (person == null) {
			person = new PersonalData();
			person.setBaptism(personalDataRequest.getBaptism());
			person.setBaptismPlace(personalDataRequest.getBaptismPlace());
			person.setBirthDate(personalDataRequest.getBirthDate());
			person.setBirthLocation(personalDataRequest.getBirthLocation());
			person.setEducation(personalDataRequest.getEducation());
			person.setEducationDate(personalDataRequest.getEducationDate());
			person.setEmirateId(personalDataRequest.getEmirateId());
			person.setName(personalDataRequest.getName());
			person.setPic(personalDataRequest.getPic());
			person.setStatus("Draft");
		} else if (person.getStatus().equals("Draft")) {
			person.setBaptism(personalDataRequest.getBaptism());
			person.setBaptismPlace(personalDataRequest.getBaptismPlace());
			person.setBirthDate(personalDataRequest.getBirthDate());
			person.setBirthLocation(personalDataRequest.getBirthLocation());
			person.setEducation(personalDataRequest.getEducation());
			person.setEducationDate(personalDataRequest.getEducationDate());
			person.setEmirateId(personalDataRequest.getEmirateId());
			person.setName(personalDataRequest.getName());
			person.setPic(personalDataRequest.getPic());
			person.setStatus("Draft");
		} else {
			result.getRes().put("code", 4000);

			result.getRes().put("PersonalData", "Already Exist");

			return result;
		}

		person = personalDataDao.save(person);

		try {
			Resource f = helper.getFileAsResource("PersonalPhoto" + File.separator + personalDataRequest.getPic());

			byte[] fileContent = FileUtils.readFileToByteArray(f.getFile());
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			person.setPic(encodedString);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result.getRes().put("code", 200);

		result.getRes().put("PersonalData", person);
		return result;

	}

}
