package com.example.ptevalform;

import org.junit.Test;

import static org.junit.Assert.*;

public class Client_UT {
	private Client mUT;	

	@Test
	public void createClientObjectExpectNotNull() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		assertNotNull(mUT);
	}
	
	@Test
	public void setAndGetClientFirstNameExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setFirstName("Valid First Name");
		assertEquals("Valid First Name", mUT.getFirstName());
	}
	
	@Test
	public void setAndGetClientLastNameExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setLastName("Valid Last Name");
		assertEquals("Valid Last Name", mUT.getLastName());
	}
	
	@Test
	public void setAndGetClientIDExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setClientID(11111);
		assertEquals(11111, mUT.getClientID());
	}
	
	@Test
	public void setAndGetPTNameExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setPTName("Valid PT Name");
		assertEquals("Valid PT Name", mUT.getPTName());
	}
	
	@Test
	public void setAndGetClientOccupationExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setOccupation("Valid Job Name");
		assertEquals("Valid Job Name", mUT.getOccupation());
	}
	
	@Test
	public void setAndGetClientBirthDateExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setBirthDate("10/11/2012");
		assertEquals("10/11/2012", mUT.getBirthDate());
	}
	
	@Test
	public void setAndGetClientAgeExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setAge(65);
		assertEquals(65, mUT.getAge());
	}
	
	@Test
	public void setAndGetClientHeightExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setHeight("6.6");
		assertTrue(mUT.getHeight().equals("6.6"));
	}
	
	@Test
	public void setAndGetClientWeightExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setWeight("102.2");
		assertTrue(mUT.getWeight().equals("102.2"));
	}
	
	@Test
	public void setAndGetClientPhoneNumberExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setPhoneNumber("Valid Phone Number");
		assertEquals("Valid Phone Number", mUT.getPhoneNumber());
	}
	
	@Test
	public void setAndGetClientEmployerExpectEqual() {
		mUT = new Client(01, "fname", "lname", "job", "1/1/1111", 20, "5'4\"", 
				"205", "222-333-4444", "employer", "ptname");
		mUT.setEmployerName("Valid Employer Name");
		assertEquals("Valid Employer Name", mUT.getEmployerName());
	}
	
}
