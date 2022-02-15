package com.church.clearance.model;

import java.io.Serializable;
import java.util.Date;

public class PreviousMarrageRequest implements Serializable {


	    private static final long serialVersionUID = 1L;
	    
	    private String kindOfMarriage;
	
	    private Date marriageDate;
		
		private String marriagePlace;
		
		private String priestFather;
		
		private String status;
		
		private String caseNo;
		
		private Date dateOfCase;
		
		private String court;
		
		private String refNo;
		
		private Integer userId;

		public String getKindOfMarriage() {
			return kindOfMarriage;
		}

		public void setKindOfMarriage(String kindOfMarriage) {
			this.kindOfMarriage = kindOfMarriage;
		}

		public Date getMarriageDate() {
			return marriageDate;
		}

		public void setMarriageDate(Date marriageDate) {
			this.marriageDate = marriageDate;
		}

		public String getMarriagePlace() {
			return marriagePlace;
		}

		public void setMarriagePlace(String marriagePlace) {
			this.marriagePlace = marriagePlace;
		}

		public String getPriestFather() {
			return priestFather;
		}

		public void setPriestFather(String priestFather) {
			this.priestFather = priestFather;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCaseNo() {
			return caseNo;
		}

		public void setCaseNo(String caseNo) {
			this.caseNo = caseNo;
		}

		public Date getDateOfCase() {
			return dateOfCase;
		}

		public void setDateOfCase(Date dateOfCase) {
			this.dateOfCase = dateOfCase;
		}

		public String getCourt() {
			return court;
		}

		public void setCourt(String court) {
			this.court = court;
		}

		public String getRefNo() {
			return refNo;
		}

		public void setRefNo(String refNo) {
			this.refNo = refNo;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		


}
