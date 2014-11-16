package com.example.ptevalform;

import java.util.ArrayList;

public class Client {
	private int mClientNumber;
	private String mPTName;
	String mFirstName;
	String mLastName;
	private String mOccupation;
	private String mDOB;
	private int mAge; 	
	private double mHeight;
	private double mWeight;
	private String mPhoneNumber;
	private String mEmployerName;
	private boolean mIsEmployed;
	private EvaluationInfo mEvaluationForm;
	
	public Client(int xClientNumber,
				String xPTName,
				String xFirstName, 
				String xLastName, 
				String xOccupation,
				String xDOB) {
		mClientNumber = xClientNumber;
		mPTName = xPTName;
		mFirstName = xFirstName;
		mLastName = xLastName;
		mOccupation = xOccupation;
		mDOB = xDOB;
	}
	
	public int getClientNumber() {
		return mClientNumber;
	}
	
	public void setClientNumber(int xClientNumber) {
		mClientNumber = xClientNumber;
	}
	
	public String getPTName() {
		return mPTName;
	}
	
	public void setPTName(String xPTName) {
		mPTName = xPTName;
	}
	
	public String getFirstName() {
		return mFirstName;
	}
	
	public void setFirstName(String xFirstName) {
		mFirstName = xFirstName;
	}
	
	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String xLastName) {
		mLastName = xLastName;
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
		
	public void setHeight(double xHeight) {
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
	
	public void setEvaluationForm(EvaluationInfo xEvaluationForm) {
		mEvaluationForm = xEvaluationForm;
	}
	
	public EvaluationInfo getEvaluationForm() {
		return mEvaluationForm;
	}
}
