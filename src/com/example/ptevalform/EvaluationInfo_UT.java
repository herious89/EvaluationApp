package com.example.ptevalform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class EvaluationInfo_UT {
	private EvaluationInfo mUT;
	
	@Test
	public void setAndGetIncidentExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setIncident("Valid Incident");
		assertEquals("Valid Incident", mUT.getIncident());
	}
	
	@Test
	public void setAndGetClientDateOfInjuryExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setDateOfInjury("10/11/2013");
		assertEquals("10/11/2013", mUT.getDateOfInjury());
	}
	
	@Test
	public void setAndGetClientDescriptionOfInjuryExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setDescriptionOfInjury("Valid Description");
		assertEquals("Valid Description", mUT.getDescriptionOfInjury());
	}
	
	@Test
	public void setAndGetClientRecievedTherapyExpectTrue() {
		mUT = new EvaluationInfo();
		mUT.setRecievedTherapy(true);
		assertTrue(mUT.getRecievedTherapy());
	}
	
	@Test
	public void setAndGetClientDateOfTherapyExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setDateOfTherapy("10/11/2012");
		assertEquals("10/11/2012", mUT.getDateOfTherapy());
	}
	
	@Test
	public void setAndGetClientNumberOfVisitsExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setNumberOfVisits(12);
		assertEquals(12, mUT.getNumberOfVisits());
	}
	
	@Test
	public void setAndGetClientConditionAfterTherapyExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setConditionAfterTherapy("Valid Condition");
		assertEquals("Valid Condition", mUT.getConditionAfterTherapy());
	}
	
	@Test
	public void setAndGetClientSymptompsExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setSymptomps("Valid Symptomps");
		assertEquals("Valid Symptomps", mUT.getSymptomps());
	}
	
	@Test
	public void setAndGetClientBestPainDegreeExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setBestPainDegree(1);
		assertEquals(1, mUT.getBestPainDegree());
	}
	
	@Test
	public void setAndGetClientWorstPainDegreeExpectEqual() {
		mUT = new EvaluationInfo();
		mUT.setWorstPainDegree(10);
		assertEquals(10, mUT.getWorstPainDegree());
	}
	
	@Test
	public void setAndGetClientBetterConditionExpectEqual() {
		mUT = new EvaluationInfo();
		ArrayList<String> betterConditions = new ArrayList<String>();
		betterConditions.add("Better Condition 1");
		betterConditions.add("Better Condition 2");
		mUT.setBetterCondition(betterConditions);		
		assertEquals(betterConditions, mUT.getBetterCondition());
	}
	
	@Test
	public void setAndGetClientWorseConditionExpectEqual() {
		mUT = new EvaluationInfo();
		ArrayList<String> worseConditions = new ArrayList<String>();
		worseConditions.add("Worse Condition 1");
		worseConditions.add("Worse Condition 2");
		mUT.setWorseCondition(worseConditions);		
		assertEquals(worseConditions, mUT.getWorseCondition());
	}
	
	@Test
	public void setAndGetClientPreMedInterventionExpectEqual() {
		mUT = new EvaluationInfo();
		ArrayList<String> preMedIntervention = new ArrayList<String>();
		preMedIntervention.add("PreMed 1");
		preMedIntervention.add("PreMed 2");
		mUT.setPreMedIntervention(preMedIntervention);		
		assertEquals(preMedIntervention, mUT.getPreMedIntervention());
	}
	
	@Test
	public void setAndGetClientMedInformationExpectEqual() {
		mUT = new EvaluationInfo();
		ArrayList<String> medInformation = new ArrayList<String>();
		medInformation.add("Med 1");
		medInformation.add("Med 2");
		mUT.setMedInformation(medInformation);		
		assertEquals(medInformation, mUT.getMedInformation());
	}
}
