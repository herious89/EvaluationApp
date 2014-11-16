package com.example.ptevalform;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

public class Client_UT {
	private Client mUT;	

	@Test
	public void createClientObjectExpectNotNull() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		assertNotNull(mUT);
	}
	
	@Test
	public void setAndGetClientFirstNameExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setFirstName("Valid First Name");
		assertEquals("Valid First Name", mUT.getFirstName());
	}
	
	@Test
	public void setAndGetClientLastNameExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setLastName("Valid Last Name");
		assertEquals("Valid Last Name", mUT.getLastName());
	}
	
	@Test
	public void setAndGetClientNumberExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setClientNumber(11111);
		assertEquals(11111, mUT.getClientNumber());
	}
	
	@Test
	public void setAndGetPTNameExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setPTName("Valid PT Name");
		assertEquals("Valid PT Name", mUT.getPTName());
	}
	
	@Test
	public void setAndGetClientOccupationExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setOccupation("Valid Job Name");
		assertEquals("Valid Job Name", mUT.getOccupation());
	}
	
	@Test
	public void setAndGetClientBirthDateExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setBirthDate("10/11/2012");
		assertEquals("10/11/2012", mUT.getBirthDate());
	}
	
	@Test
	public void setAndGetClientAgeExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setAge(65);
		assertEquals(65, mUT.getAge());
	}
	
	@Test
	public void setAndGetClientHeightExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setHeight(6.6);
		assertTrue(mUT.getHeight() == 6.6);
	}
	
	@Test
	public void setAndGetClientWeightExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setWeight(102.2);
		assertTrue(mUT.getWeight() == 102.2);
	}
	
	@Test
	public void setAndGetClientPhoneNumberExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setPhoneNumber("Valid Phone Number");
		assertEquals("Valid Phone Number", mUT.getPhoneNumber());
	}
	
	@Test
	public void setAndGetClientEmployerExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setEmployerName("Valid Employer Name");
		assertEquals("Valid Employer Name", mUT.getEmployerName());
	}
	
	@Test
	public void setAndGetClientEmploymentStatusExpectTrue() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setEmploymentStatus(true);
		assertTrue(mUT.getEmploymentStatus());
	}
	
}
