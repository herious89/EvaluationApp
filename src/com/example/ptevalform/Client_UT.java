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
	
	@Test
	public void setAndGetIncidentExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setIncident("Valid Incident");
		assertEquals("Valid Incident", mUT.getIncident());
	}
	
	@Test
	public void setAndGetClientDateOfInjuryExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setDateOfInjury("10/11/2013");
		assertEquals("10/11/2013", mUT.getDateOfInjury());
	}
	
	@Test
	public void setAndGetClientDescriptionOfInjuryExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setDescriptionOfInjury("Valid Description");
		assertEquals("Valid Description", mUT.getDescriptionOfInjury());
	}
	
	@Test
	public void setAndGetClientRecievedTherapyExpectTrue() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setRecievedTherapy(true);
		assertTrue(mUT.getRecievedTherapy());
	}
	
	@Test
	public void setAndGetClientDateOfTherapyExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setDateOfTherapy("10/11/2012");
		assertEquals("10/11/2012", mUT.getDateOfTherapy());
	}
	
	@Test
	public void setAndGetClientNumberOfVisitsExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setNumberOfVisits(12);
		assertEquals(12, mUT.getNumberOfVisits());
	}
	
	@Test
	public void setAndGetClientConditionAfterTherapyExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setConditionAfterTherapy("Valid Condition");
		assertEquals("Valid Condition", mUT.getConditionAfterTherapy());
	}
	
	@Test
	public void setAndGetClientSymptompsExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setSymptomps("Valid Symptomps");
		assertEquals("Valid Symptomps", mUT.getSymptomps());
	}
	
	@Test
	public void setAndGetClientBestPainDegreeExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setBestPainDegree(1);
		assertEquals(1, mUT.getBestPainDegree());
	}
	
	@Test
	public void setAndGetClientWorstPainDegreeExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		mUT.setWorstPainDegree(10);
		assertEquals(10, mUT.getWorstPainDegree());
	}
	
	@Test
	public void setAndGetClientBetterConditionExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		ArrayList<String> betterConditions = new ArrayList<String>();
		betterConditions.add("Better Condition 1");
		betterConditions.add("Better Condition 2");
		mUT.setBetterCondition(betterConditions);		
		assertEquals(betterConditions, mUT.getBetterCondition());
	}
	
	@Test
	public void setAndGetClientWorseConditionExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		ArrayList<String> worseConditions = new ArrayList<String>();
		worseConditions.add("Worse Condition 1");
		worseConditions.add("Worse Condition 2");
		mUT.setWorseCondition(worseConditions);		
		assertEquals(worseConditions, mUT.getWorseCondition());
	}
	
	@Test
	public void setAndGetClientPreMedInterventionExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		ArrayList<String> preMedIntervention = new ArrayList<String>();
		preMedIntervention.add("PreMed 1");
		preMedIntervention.add("PreMed 2");
		mUT.setPreMedIntervention(preMedIntervention);		
		assertEquals(preMedIntervention, mUT.getPreMedIntervention());
	}
	
	@Test
	public void setAndGetClientMedInformationExpectEqual() {
		mUT = new Client(01, "ptname", "fname", "lname", "job", "12345");
		ArrayList<String> medInformation = new ArrayList<String>();
		medInformation.add("Med 1");
		medInformation.add("Med 2");
		mUT.setMedInformation(medInformation);		
		assertEquals(medInformation, mUT.getMedInformation());
	}
}
