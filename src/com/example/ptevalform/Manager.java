package com.example.ptevalform;

import java.util.ArrayList;

public class Manager extends User{
	private ArrayList<PhysicalTherapy> mPTList;
	
	public Manager(String xFirstName, String xLastName, String xUserName, String xPassword, int xEmployeeID) {
		super(xFirstName, xLastName, xUserName, xPassword, xEmployeeID);
		mPTList = new ArrayList<PhysicalTherapy>();
	}
	
	public PhysicalTherapy retrievePhysicalTherapy(int xEmployeeID) {
		for (int ii = 0; ii < mPTList.size(); ++ii) {
			if (mPTList.get(ii).getEmployeeID() == xEmployeeID)
				return mPTList.get(ii);
		}
		return null;
	}
}
