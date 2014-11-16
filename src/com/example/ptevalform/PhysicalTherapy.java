package com.example.ptevalform;

import java.util.ArrayList;

public class PhysicalTherapy extends User{
	private ArrayList<Client> mClientList;
	
	public PhysicalTherapy(String xFirstName, String xLastName, String xUserName, String xPassword, int xEmployeeID) {
		super(xFirstName, xLastName, xUserName, xPassword, xEmployeeID);
		mClientList = new ArrayList<Client>();
	}
	
	public void addClient(Client xClient) {
		mClientList.add(xClient);
	}
	
	public Client retrieveClient(int xClientNumber) {
		for (int ii = 0; ii < mClientList.size(); ++ii) {
			if (mClientList.get(ii).getClientNumber() == xClientNumber)
				return mClientList.get(ii);
		}
		return null;
	}
}
