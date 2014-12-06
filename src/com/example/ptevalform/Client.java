package com.example.ptevalform;

public class Client {
	private String mPTName;
	String mFirstName;
	String mLastName;
	private String mOccupation;
	private int mClientID;
	private String mDOB;
	private int mAge; 	
	private String mHeight;
	private String mWeight;
	private String mPhoneNumber;
	private String mEmployerName;
	private boolean mIsEmployed;
	private EvaluationInfo mEvaluationForm;
	
	public Client(int xClientID,
				String xFirstName, 
				String xLastName, 
				String xOccupation,
				String xDOB,
				int xAge,
				String xHeight,
				String xWeight,
				String xPhoneNumber,
				String xEmployerName,
				String xPTName) {
		mClientID = xClientID;
		mPTName = xPTName;
		mFirstName = xFirstName;
		mLastName = xLastName;
		mOccupation = xOccupation;
		mDOB = xDOB;
		mAge = xAge;
		mHeight = xHeight;
		mWeight = xWeight;
		mPhoneNumber = xPhoneNumber;
		mEmployerName = xEmployerName;
	}

	public int getClientID() {
		return mClientID;
	}
	
	public void setClientID(int xClientID) {
		mClientID = xClientID;
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
	
	public String getHeight() {
		return mHeight;
	}
		
	public void setHeight(String xHeight) {
		mHeight = xHeight;
	}
	
	public String getWeight() {
		return mWeight;
	}
		
	public void setWeight(String xWeight) {
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
