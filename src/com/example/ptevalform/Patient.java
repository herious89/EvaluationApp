package com.example.ptevalform;

import java.util.ArrayList;

public class Patient {
	private int mPatientNumber;
	private String mPTName;
	private String mfirstName;
	private String mlastName;
	private String mOccupation;
	private String mDOB;
	private int mAge; 
	private double mHeight;
	private double mWeight;
	private String mPhoneNumber;
	private String mEmployerName;
	private boolean mIsEmployed;
	
	private String mIncident;
	private String mDateOfInjury;
	private String mDateOfSurgery;
	private String mDescriptionOfInjury;
	private boolean mIsRecievedTherapy;
	private String mDateOfTherapy;
	private int mNumberOfVisits;
	private String mConditionAfterTherapy;
	private String mSymptomps;
	
	private int mBestPainDegree;
	private int mWorstPainDegree;
	
	private ArrayList<String> mBetterCondition;
	private ArrayList<String> mWorseCondition;
	
	private ArrayList<String> mPreMedIntervention;
	private ArrayList<String> mMedInformation;
	
	public Patient(int xPatientNumber,
				String xPTName,
				String xFirstName, 
				String xLastName, 
				String xOccupation,
				String xDOB) {
		mPatientNumber = xPatientNumber;
		mPTName = xPTName;
		mfirstName = xFirstName;
		mlastName = xLastName;
		mOccupation = xOccupation;
		mDOB = xDOB;
	}
	
	public int getPatientNumber() {
		return mPatientNumber;
	}
	
	public void setPatientNumber(int xPatientNumber) {
		mPatientNumber = xPatientNumber;
	}
	
	public String getPTName() {
		return mPTName;
	}
	
	public void setPTName(String xPTName) {
		mPTName = xPTName;
	}
	
	public String getFirstName() {
		return mfirstName;
	}
	
	public void setFirstName(String xFirstName) {
		mfirstName = xFirstName;
	}
	
	public String getLastName() {
		return mlastName;
	}

	public void setLastName(String xLastName) {
		mlastName = xLastName;
	}
	
	public String getOccupation() {
		return mOccupation;
	}
		
	public void setOccupation(String xOccupation) {
		mOccupation = xOccupation;
	}
	
	public String getBirthDate() {
		return mDOB;
	}
		
	public void setBirthDate(String xDOB) {
		mDOB = xDOB;
	}
	
	public int getAge() {
		return mAge;
	}
		
	public void setAge(int xAge) {
		mAge = xAge;
	}
	
	public double getHeight() {
		return mHeight;
	}
		
	public void setHight(double xHeight) {
		mHeight = xHeight;
	}
	
	public double getWeight() {
		return mWeight;
	}
		
	public void setWeight(double xWeight) {
		mWeight = xWeight;
	}
	
	public String getPhoneNumber() {
		return mPhoneNumber;
	}
		
	public void setPhoneNumber(String xPhoneNumber) {
		mPhoneNumber = xPhoneNumber;
	}
	
	public String getEmployerName() {
		return mEmployerName;
	}
		
	public void setEmployerName(String xEmployerName) {
		mEmployerName = xEmployerName;
	}
	
	public boolean getEmploymentStatus() {
		return mIsEmployed;
	}
		
	public void setEmploymentStatus(boolean xEmploymentStatus) {
		mIsEmployed = xEmploymentStatus;
	}
	
	public String getIncident() {
		return mIncident;
	}
		
	public void setIncident(String xIncident) {
		mIncident = xIncident;
	}
	
	public String getDateOfInjury() {
		return mDateOfInjury;
	}
		
	public void setDateOfInjury(String xDayOfInjury) {
		mDateOfInjury = xDayOfInjury;
	}
	
	public String getDateOfSurgery() {
		return mDateOfSurgery;
	}
		
	public void setDateOfSurgery(String xDateOfSurgery) {
		mDateOfSurgery = xDateOfSurgery;
	}
	
	public String getDescriptionOfInjury() {
		return mDescriptionOfInjury;
	}
		
	public void setDescriptionOfInjury(String xDescriptionOfInjury) {
		mDOB = xDescriptionOfInjury;
	}
	
	public boolean getRecievedTherapy() {
		return mIsRecievedTherapy;
	}
		
	public void setRecievedTherapy(boolean xIsRecievedTherapy) {
		mIsRecievedTherapy = xIsRecievedTherapy;
	}
	
	public String getDateOfTherapy() {
		return mDateOfTherapy;
	}
		
	public void setDateOfTherapy(String xDateOfTherapy) {
		mDateOfTherapy = xDateOfTherapy;
	}
	
	public int getNumberOfVisits() {
		return mNumberOfVisits;
	}
		
	public void setNumberOfVisits(int xNumberOfVisits) {
		mNumberOfVisits = xNumberOfVisits;
	}
	
	public String getConditionAfterTherapy() {
		return mConditionAfterTherapy;
	}
		
	public void setConditionAfterTherapy(String xConditionAfterTherapy) {
		mConditionAfterTherapy = xConditionAfterTherapy;
	}
	
	public String getSymptomps() {
		return mSymptomps;
	}
		
	public void setSymptomps(String xSymptomps) {
		mSymptomps = xSymptomps;
	}
	
	public int getBestPainDegree() {
		return mBestPainDegree;
	}
		
	public void setBestPainDegree(int xBestPainDegree) {
		mBestPainDegree = xBestPainDegree;
	}
	
	public int getWorstPainDegree() {
		return mWorstPainDegree;
	}
		
	public void setWorstPainDegree(int xWorstPainDegree) {
		mWorstPainDegree = xWorstPainDegree;
	}
	
	public ArrayList<String> getBetterCondition() {
		return mBetterCondition;
	}
		
	public void setBetterCondition(ArrayList<String> xBetterCondition) {
		mBetterCondition = xBetterCondition;
	}
	
	public ArrayList<String> getWorseCondition() {
		return mWorseCondition;
	}
		
	public void setWorseCondition(ArrayList<String> xWorseCondition) {
		mWorseCondition = xWorseCondition;
	}
	
	public ArrayList<String> getPreMedIntervention() {
		return mPreMedIntervention;
	}
		
	public void setPreMedIntervention(ArrayList<String> xPreMedIntervention) {
		mPreMedIntervention = xPreMedIntervention;
	}
	
	public ArrayList<String> getMedInformation() {
		return mMedInformation;
	}
		
	public void setMedInformation(ArrayList<String> xMedInformation) {
		mMedInformation = xMedInformation;
	}
}
