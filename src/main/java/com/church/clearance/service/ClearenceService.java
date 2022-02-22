package com.church.clearance.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.clearance.config.HelperMethods;
import com.church.clearance.dao.ChildDao;
import com.church.clearance.dao.ChurchDao;
import com.church.clearance.dao.ClearanceDao;
import com.church.clearance.dao.PersonalDataDao;
import com.church.clearance.dao.PreviousEngagmentDao;
import com.church.clearance.dao.PreviousMarriageDao;
import com.church.clearance.dao.UsersDao;
import com.church.clearance.entities.Church;
import com.church.clearance.entities.Clearance;
import com.church.clearance.entities.PersonalData;
import com.church.clearance.entities.PreviousEngagment;
import com.church.clearance.entities.PreviousMarriage;
import com.church.clearance.entities.Users;
import com.church.clearance.model.ClrancAddPrintedReq;
import com.church.clearance.model.ClrancCancelReq;
import com.church.clearance.model.ClrancStepOneReqModel;
import com.church.clearance.model.ClrncStepFiveReq;
import com.church.clearance.model.ClrncStepFourReq;
import com.church.clearance.model.ClrncStepThreeReq;
import com.church.clearance.model.ClrncStepTwoReq;
import com.church.clearance.model.ResultReturn;

@Service
public class ClearenceService {

	@Autowired
	PersonalDataDao personalDataDao;

	@Autowired
	ChurchDao churchDao;

	@Autowired
	UsersDao userDao;

	@Autowired
	ClearanceDao clearanceDao;

	@Autowired
	HelperMethods help;

	@Autowired
	PreviousEngagmentDao previousEngagmentDao;

	@Autowired
	ChildDao childDao;

	@Autowired
	PreviousMarriageDao previousMarriageDao;

	public ResultReturn createFirstStepClearance(ClrancStepOneReqModel clrancStepOne) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationFirstStepClearance(clrancStepOne);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {

			Clearance cl = clearanceDao.findByEmirateIdAndStatus(clrancStepOne.getEmirateId());

			if (cl == null) {

				cl = new Clearance();

			}

			cl.setAddress(clrancStepOne.getAddress());
			cl.setChurch(churchDao.findOne(clrancStepOne.getChurchId()));
			cl.setFatherOfConfession(clrancStepOne.getFatherOfConfession());
			cl.setFromChurch(clrancStepOne.getFromChurch());
			cl.setGender(clrancStepOne.getGender());
			cl.setIntakeRate(clrancStepOne.getIntakeRate());
			cl.setJob(clrancStepOne.getJob());
			cl.setJobAddress(clrancStepOne.getJobAddress());
			cl.setMilitaryService(clrancStepOne.getMilitaryService());
			cl.setPersonalData(personalDataDao.findOne(clrancStepOne.getEmirateId()));
			cl.setRecognitionRegularityRate(clrancStepOne.getRecognitionRegularityRate());
			cl.setRefNo(help.getClearanceId());
			cl.setStatus("Draft");
			cl.setUser(userDao.findOne(clrancStepOne.getUserId()));

			cl = clearanceDao.save(cl);

			res.getRes().put("Clearance", cl);

			return res;
		}

	}

	public LinkedHashMap<String, Object> validationFirstStepClearance(ClrancStepOneReqModel clrancStepOne) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////personalData/////////////////////		
		if (clrancStepOne.getEmirateId() == null || clrancStepOne.getEmirateId().equals("")) {

			r.put("1", "EmirateId can not be null");
		} else {
			PersonalData p = personalDataDao.findOne(clrancStepOne.getEmirateId());

			if (p == null) {
				r.put("2", "EmirateId Not Found in PersonalData");
			}
///////////////////anotherClearances//////////////////////

			List<Clearance> oldClearance = (List<Clearance>) clearanceDao.findByEmirateId(clrancStepOne.getEmirateId());
			if (oldClearance.size() > 0) {

				for (Clearance cl : oldClearance) {

//		 if (cl.getStatus().equalsIgnoreCase("Draft")){
//			 
//			 r.put("14", "Clearance Already exist can go to update it");
//			 break;
//		 }
					// else
					if (cl.getStatus().equalsIgnoreCase("Active")) {
						r.put("15", "Clearance Already exist can't create another cancel the active first");
						break;
					}
				}
			}
		}
		/////////// church//////////////////
		if (clrancStepOne.getChurchId() == null || clrancStepOne.getChurchId().equals("")) {
			r.put("4", "church can not be null");
		} else {
			Church ch = churchDao.findOne(clrancStepOne.getChurchId());
			if (ch == null) {
				r.put("5", "church  not Found");
			}
		}
//////////////user////////////////////		
		if (clrancStepOne.getUserId() == null || clrancStepOne.getUserId().equals("")) {
			r.put("6", "user can not be null");
		} else {
			Users user = userDao.findOne(clrancStepOne.getUserId());

			if (user == null) {
				r.put("7", "user Not Found");
			} else if (user.getChurch().getChurchId() != clrancStepOne.getChurchId()) {
				Church ch = churchDao.findOne(clrancStepOne.getChurchId());
				if (ch != null) {
					r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
				} else {
					r.put("8", "user can't make clearance for church ");
				}
			} else if (user.getRoll().getRolId() != 2) {

				r.put("9", "user can't make clearance not have privilage");
			}
		}
		/////////// geder//////////////
		if (clrancStepOne.getGender() == null || clrancStepOne.getGender().equals("")) {
			r.put("10", "Gender can't be null");
		} else if (clrancStepOne.getGender().equalsIgnoreCase("Male")) {

			if (clrancStepOne.getMilitaryService() == null || clrancStepOne.getMilitaryService().equals("")) {

				r.put("11", "MilitaryService can't be null");
			}
		}
//////////////////adress/////////////////	
		if (clrancStepOne.getAddress() == null || clrancStepOne.getAddress().equals("")) {
			r.put("11", "Address can't be null");
		}
//////////////////job/////////////////	
		if (clrancStepOne.getJob() == null || clrancStepOne.getJob().equals("")) {
			r.put("12", "Job can't be null");
		}
//////////////////Jobadress/////////////////	
		if (clrancStepOne.getJobAddress() == null || clrancStepOne.getJobAddress().equals("")) {
			r.put("13", "JobAddress can't be null");
		}
		return r;
	}

	public ResultReturn upgateClearanceEngagment(ClrncStepTwoReq clrncStepTwoReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationSecondStep(clrncStepTwoReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrncStepTwoReq.getRefNo());

			if (cl.getIsPreviousEngagement() == null) {
				cl.setIsPreviousEngagement(clrncStepTwoReq.getIsPreviousEngagement().toUpperCase());
				clearanceDao.save(cl);
				res.getRes().put("updateEngagment", cl);
				return res;
			} else if (clrncStepTwoReq.getIsPreviousEngagement().equalsIgnoreCase("N")) {

				cl.setIsPreviousEngagement(clrncStepTwoReq.getIsPreviousEngagement().toUpperCase());
				if (cl.getPreviousEngagment().size() > 0) {

					previousEngagmentDao.delete(cl.getPreviousEngagment());
				}
				clearanceDao.save(cl);
				res.getRes().put("updateEngagment", cl);
				return res;
			} else if (clrncStepTwoReq.getIsPreviousEngagement().equalsIgnoreCase("Y")) {

				cl.setIsPreviousEngagement(clrncStepTwoReq.getIsPreviousEngagement().toUpperCase());

				clearanceDao.save(cl);
				res.getRes().put("updateEngagment", cl);
				return res;
			}

			return res;
		}
	}

	private LinkedHashMap<String, Object> validationSecondStep(ClrncStepTwoReq clrncStepTwoReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrncStepTwoReq.getRefNo() == null || clrncStepTwoReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrncStepTwoReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel") || cl.getStatus().equals("Active")) {
				r.put("18", "can't update egagment for active or cancel clearance");
			}
//////////////user////////////////////		
			if (clrncStepTwoReq.getUserId() == null || clrncStepTwoReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrncStepTwoReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrncStepTwoReq.getIsPreviousEngagement() == null
					|| clrncStepTwoReq.getIsPreviousEngagement().equals("")) {
				r.put("19", "IsPreviousEngagement can't be null");
			} else if (!clrncStepTwoReq.getIsPreviousEngagement().equalsIgnoreCase("Y")
					&& !clrncStepTwoReq.getIsPreviousEngagement().equalsIgnoreCase("N")) {
				r.put("19", "IsPreviousEngagement Must be Y or F only");
			}

		}
		return r;
	}

	public ResultReturn upgateClearanceMarriage(ClrncStepThreeReq clrncStepThreeReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationTheerdStep(clrncStepThreeReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrncStepThreeReq.getRefNo());

			if (cl.getIsPreviousMarriage() == null) {
				cl.setIsPreviousMarriage(clrncStepThreeReq.getIsPreviousMarrage().toUpperCase());
				clearanceDao.save(cl);
				res.getRes().put("updateMarrage", cl);
				return res;
			} else if (clrncStepThreeReq.getIsPreviousMarrage().equalsIgnoreCase("N")) {

				cl.setIsPreviousEngagement(clrncStepThreeReq.getIsPreviousMarrage().toUpperCase());
				if (cl.getPreviousMarriages().size() > 0) {

					previousMarriageDao.delete(cl.getPreviousMarriages());
				}
				clearanceDao.save(cl);
				res.getRes().put("updateMarrage", cl);
				return res;
			} else if (clrncStepThreeReq.getIsPreviousMarrage().equalsIgnoreCase("Y")) {

				cl.setIsPreviousMarriage(clrncStepThreeReq.getIsPreviousMarrage().toUpperCase());

				clearanceDao.save(cl);
				res.getRes().put("updateMarrage", cl);
				return res;
			}

			return res;
		}
	}

	private LinkedHashMap<String, Object> validationTheerdStep(ClrncStepThreeReq clrncStepThreeReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrncStepThreeReq.getRefNo() == null || clrncStepThreeReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrncStepThreeReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel") || cl.getStatus().equals("Active")) {
				r.put("18", "can't update egagment for active or cancel clearance");
			}
//////////////user////////////////////		
			if (clrncStepThreeReq.getUserId() == null || clrncStepThreeReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrncStepThreeReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrncStepThreeReq.getIsPreviousMarrage() == null
					|| clrncStepThreeReq.getIsPreviousMarrage().equals("")) {
				r.put("30", "IsPreviousMarrage can't be null");
			} else if (!clrncStepThreeReq.getIsPreviousMarrage().equalsIgnoreCase("Y")
					&& !clrncStepThreeReq.getIsPreviousMarrage().equalsIgnoreCase("N")) {
				r.put("31", "IsPreviousMarrage Must be Y or F only");
			}

		}
		return r;
	}

	public ResultReturn upgateClearanceChild(ClrncStepFourReq clrncStepFourReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationForthStep(clrncStepFourReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrncStepFourReq.getRefNo());

			if (cl.getIsHaveChildern() == null) {
				cl.setIsHaveChildern(clrncStepFourReq.getIsPreviousChild().toUpperCase());
				clearanceDao.save(cl);
				res.getRes().put("updateChild", cl);
				return res;
			} else if (clrncStepFourReq.getIsPreviousChild().equalsIgnoreCase("N")) {

				cl.setIsHaveChildern(clrncStepFourReq.getIsPreviousChild().toUpperCase());
				if (cl.getChilds().size() > 0) {

					childDao.delete(cl.getChilds());
				}
				clearanceDao.save(cl);
				res.getRes().put("updateChild", cl);
				return res;
			} else if (clrncStepFourReq.getIsPreviousChild().equalsIgnoreCase("Y")) {

				cl.setIsHaveChildern(clrncStepFourReq.getIsPreviousChild().toUpperCase());

				clearanceDao.save(cl);
				res.getRes().put("updateChild", cl);
				return res;
			}

			return res;
		}
	}

	private LinkedHashMap<String, Object> validationForthStep(ClrncStepFourReq clrncStepFourReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrncStepFourReq.getRefNo() == null || clrncStepFourReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrncStepFourReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel") || cl.getStatus().equals("Active")) {
				r.put("42", "can't update haveChild for active or cancel clearance");
			}
//////////////user////////////////////		
			if (clrncStepFourReq.getUserId() == null || clrncStepFourReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrncStepFourReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrncStepFourReq.getIsPreviousChild() == null || clrncStepFourReq.getIsPreviousChild().equals("")) {
				r.put("40", "IsHaveChild can't be null");
			} else if (!clrncStepFourReq.getIsPreviousChild().equalsIgnoreCase("Y")
					&& !clrncStepFourReq.getIsPreviousChild().equalsIgnoreCase("N")) {
				r.put("41", "IsHaveChild Must be Y or F only");
			}

		}
		return r;
	}

	public ResultReturn upgateClearanceFinal(ClrncStepFiveReq clrncStepFiveReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationFiveStep(clrncStepFiveReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrncStepFiveReq.getRefNo());

			cl.setSocialStatus(clrncStepFiveReq.getSocialStatus());
			cl.setSourceOfMarriagePermit(clrncStepFiveReq.getSourceOfPermitMarriage());
			cl.setDateOfMarriagePermit(clrncStepFiveReq.getDateOfPermitMarriage());
			cl.setNumOfChildern(cl.getChilds().size());
			cl.setStatus("Active");

			clearanceDao.save(cl);
			res.getRes().put("updateClearance", cl);
		}
		return res;
	}

	private LinkedHashMap<String, Object> validationFiveStep(ClrncStepFiveReq clrncStepFiveReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrncStepFiveReq.getRefNo() == null || clrncStepFiveReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrncStepFiveReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Cancel") || cl.getStatus().equals("Active")) {
				r.put("50", "can't update Clearance for active or cancel clearance");
			}
//////////////user////////////////////		
			if (clrncStepFiveReq.getUserId() == null || clrncStepFiveReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrncStepFiveReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrncStepFiveReq.getSocialStatus() == null || clrncStepFiveReq.getSocialStatus().equals("")) {
				r.put("51", "SocialStatus can't be null");
			} else if (clrncStepFiveReq.getSocialStatus().equalsIgnoreCase("divorce")
					&& (clrncStepFiveReq.getSourceOfPermitMarriage() == null
							|| clrncStepFiveReq.getSourceOfPermitMarriage().equals(""))) {
				r.put("52", "SourceOfMarriagePermitcan't be null if social status divorced");
			} else if (clrncStepFiveReq.getSocialStatus().equalsIgnoreCase("divorce")
					&& (clrncStepFiveReq.getDateOfPermitMarriage() == null)) {
				r.put("53", "DateOfMarriagePermitcan't be null if social status divorced");
			}
		}
		return r;
	}

	public ResultReturn upgateClearancePrintedFile(ClrancAddPrintedReq clrancAddPrintedReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationPrintedFileStep(clrancAddPrintedReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrancAddPrintedReq.getRefNo());

			cl.setPrintedFileAttachment(clrancAddPrintedReq.getPrintedClearance());

			clearanceDao.save(cl);
			res.getRes().put("updateClearance", cl);
		}
		return res;
	}

	private LinkedHashMap<String, Object> validationPrintedFileStep(ClrancAddPrintedReq clrancAddPrintedReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrancAddPrintedReq.getRefNo() == null || clrancAddPrintedReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrancAddPrintedReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			}
//	else if(cl.getStatus().equals("Cancel") || cl.getStatus().equals("Active")){
//		r.put("50", "can't update Clearance for active or cancel clearance");
//	}
//////////////user////////////////////		
			if (clrancAddPrintedReq.getUserId() == null || clrancAddPrintedReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrancAddPrintedReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrancAddPrintedReq.getPrintedClearance() == null
					|| clrancAddPrintedReq.getPrintedClearance().equals("")) {
				r.put("54", "printed File can't be null");
			}
		}
		return r;
	}

	public ResultReturn cancelClearance(ClrancCancelReq clrancCancelReq) {

		ResultReturn res = new ResultReturn();
		LinkedHashMap<String, Object> t = validationCancelClearance(clrancCancelReq);

		if (t.size() > 0) {

			res.setRes(t);

			return res;
		} else {
			Clearance cl = clearanceDao.findOne(clrancCancelReq.getRefNo());

			cl.setOriginalFileAttachment(clrancCancelReq.getOriginalClearance());
			cl.setEngagmentFileAttachment(clrancCancelReq.getEngClearance());
			cl.setAnnulmentEngagmentAttachmnet(clrancCancelReq.getAnnulClearance());
			cl.setStatus("Cancel");

			clearanceDao.save(cl);
			res.getRes().put("cancelClearance", cl);
		}
		return res;
	}

	private LinkedHashMap<String, Object> validationCancelClearance(ClrancCancelReq clrancCancelReq) {

		LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();

/////////////////refNo///////////////////

		if (clrancCancelReq.getRefNo() == null || clrancCancelReq.getRefNo().equals("")) {
			r.put("16", "refNo can't be null");
		} else {

			Clearance cl = clearanceDao.findOne(clrancCancelReq.getRefNo());

			if (cl == null) {
				r.put("17", "Clearance not found");
			} else if (cl.getStatus().equals("Draft")) {
				r.put("55", "can't Cancel Clearance for Draft clearance");
			}
//////////////user////////////////////		
			if (clrancCancelReq.getUserId() == null || clrancCancelReq.getUserId().equals("")) {
				r.put("6", "user can not be null");
			} else {
				Users user = userDao.findOne(clrancCancelReq.getUserId());

				if (user == null) {
					r.put("7", "user Not Found");
				}
				if (cl != null) {
					if (user.getChurch().getChurchId() != cl.getChurch().getChurchId()) {
						Church ch = churchDao.findOne(cl.getChurch().getChurchId());
						if (ch != null) {
							r.put("8", "user can't make clearance for church " + ch.getChurchNameEn());
						} else {
							r.put("8", "user can't make clearance for church ");
						}
					}
				} else if (user.getRoll().getRolId() != 2) {

					r.put("9", "user can't make clearance not have privilage");
				}
			}

			if (clrancCancelReq.getOriginalClearance() == null || clrancCancelReq.getOriginalClearance().equals("")) {

				if (clrancCancelReq.getEngClearance() == null || clrancCancelReq.getEngClearance().equals("")) {

					r.put("56", "engagment File can't be null");
				}

				if (clrancCancelReq.getAnnulClearance() == null || clrancCancelReq.getAnnulClearance().equals("")) {

					r.put("57", "annulement Engagment File can't be null");
				}

			} else if (clrancCancelReq.getOriginalClearance() != null
					&& !clrancCancelReq.getOriginalClearance().equals("")) {
				if (clrancCancelReq.getEngClearance() != null && !clrancCancelReq.getEngClearance().equals("")) {

					r.put("58", "engagment File must be null if you have Original");
				}

				if (clrancCancelReq.getAnnulClearance() != null && !clrancCancelReq.getAnnulClearance().equals("")) {

					r.put("59", "annulement Engagment File must be null if you have Original");
				}

			}
		}
		return r;
	}

	public LinkedHashMap<String, Object> getClearanceData(String eid) {
		LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();

		if (eid != null) {
			PersonalData person = personalDataDao.findOne(eid);
			if (person != null) {
				List<Clearance> clearances = person.getClearances();
				if(!clearances.isEmpty()) {
					result.put("clearances", clearances);
					result.put("code", 200);
				}
				else {
					result.put("code", 4000);
					result.put("msg","No clearance found");
					
				}
			}
			else {
				result.put("code", 4000);
				result.put("msg","User Not Found");		
			}
		}else {
			result.put("code", 404);
			result.put("msg","Required Data");		
		}
		return result;
	}
}
