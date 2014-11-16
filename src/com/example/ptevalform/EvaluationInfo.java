package com.example.ptevalform;

import java.util.ArrayList;

public class EvaluationInfo {
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
	
	public EvaluationInfo() {
		mIncident = null;
		mDateOfInjury = null;
		mDateOfSurgery = null;
		mDescriptionOfInjury = null;
		mIsRecievedTherapy = false;
		mDateOfTherapy = null;
		mNumberOfVisits = 0;
		mConditionAfterTherapy = null;
		mSymptomps = null;
		mBestPainDegree = 0;
		mWorstPainDegree = 0;
		mBetterCondition = null;
		mWorseCondition = null;
		mPreMedIntervention = null;
		mMedInformation = null;
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
		mDescriptionOfInjury = xDescriptionOfInjury;
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
