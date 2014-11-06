package com.example.ptevalform;

public class Client {
	private String firstName;
	private String lastName;
	private String DOB;
	private String SSN;
	
	public Client(String xFirstName, String xLastName, String xDOB, String xSSN)	{
		firstName = xFirstName;
		lastName = xLastName;
		DOB = xDOB;
		SSN = xSSN;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getBirth() {
		return DOB;
	}
	
	public String getSSN() {
		return SSN;
	}
	
	public void setFirstName(String xFirstName) {
		firstName = xFirstName;
	}
	
	public void setLastName(String xLastName) {
		lastName = xLastName;
	}
	
	public void setBirth(String xDOB) {
		DOB = xDOB;
	}
	
	public void setSSN(String xDOB) {
		SSN = xDOB;
	}
}
