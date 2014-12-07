package com.example.ptevalform;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
	Button mSubmit;
	private int mPosition, mPatientPosition;
	private JSONObject mDatabase;
	final static String FILENAME = "database.json";
	private String filedir;
	private boolean isManager;
	
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_profile, container, false);       
       
       final EditText tClientID = (EditText) rootView.findViewById(R.id.clientID);
       final EditText tFirstName = (EditText) rootView.findViewById(R.id.first_name);
       final EditText tLastName = (EditText) rootView.findViewById(R.id.last_name);
       final EditText tOccupation = (EditText) rootView.findViewById(R.id.occupation);
       final EditText tBirthDate = (EditText) rootView.findViewById(R.id.birth_date);
       final EditText tAge = (EditText) rootView.findViewById(R.id.age);
       final EditText tHeight = (EditText) rootView.findViewById(R.id.height);
       final EditText tWeight = (EditText) rootView.findViewById(R.id.weight);
       final EditText tPhone = (EditText) rootView.findViewById(R.id.phone);
       final EditText tEmployer = (EditText) rootView.findViewById(R.id.employer);
       final TextView tError = (TextView) rootView.findViewById(R.id.error);
       
       Intent in = getActivity().getIntent();
       isManager = in.getExtras().getBoolean("isManager");
       mPosition = in.getExtras().getInt("position");
       mPatientPosition = in.getExtras().getInt("patientPosition");
       int clientID = in.getExtras().getInt("clientID");
       String lastName = in.getExtras().getString("lastName");
       String firstName = in.getExtras().getString("firstName");
       String occupation = in.getExtras().getString("occupation");
       String birthDate = in.getExtras().getString("birthDate");
       int age = in.getExtras().getInt("age");
       String height = in.getExtras().getString("height");
       String weight = in.getExtras().getString("weight");
       String phone = in.getExtras().getString("phone");
       String employer = in.getExtras().getString("employer");
       filedir = in.getExtras().getString("filedir");
       
       try {
    	   String database = "";
    	   InputStream is  = new BufferedInputStream(new FileInputStream(filedir + "/" + FILENAME));

    	   InputStreamReader inputStreamReader = new InputStreamReader(is);
    	   BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    	   String receiveString = "";
    	   StringBuilder stringBuilder = new StringBuilder();

    	   while ( (receiveString = bufferedReader.readLine()) != null ) {
    		   stringBuilder.append(receiveString);
    	   }
    	   database = stringBuilder.toString();
    	   mDatabase = new JSONObject(database);
    	   is.close();
    	   Log.d("READDDDD", " not from AM");
       } catch (IOException e1) {
    	   // TODO Auto-generated catch block
    	   Log.d("123", "error1");
       } catch (JSONException e) {
    	   Log.d("123", "JSON error");
       } 
       
       tClientID.setText(clientID + "");
       tLastName.setText(lastName);
       tFirstName.setText(firstName);
       tOccupation.setText(occupation);
       tBirthDate.setText(birthDate);
       tAge.setText(age + "");
       tHeight.setText(height);
       tWeight.setText(weight);
       tPhone.setText(phone);
       tEmployer.setText(employer);
       
       mSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
       mSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String cID = tClientID.getText().toString();
				String cLastName = tLastName.getText().toString();
				String cFirstName = tFirstName.getText().toString();
				String cOccupation = tOccupation.getText().toString();
				int cAge = Integer.parseInt(tAge.getText().toString());
				String cBirthDate = tBirthDate.getText().toString();
				String cPhone = tPhone.getText().toString();
       
            	if (cFirstName.equals(""))
            		tError.setText("First name cannot be blank!");
            	else if (cLastName.equals(""))
            		tError.setText("Last name cannot be blank!");
            	else if (cID.equals("")) 
            		tError.setText("Client ID cannot be blank!");
            	else if (cOccupation.equals(""))
            		tError.setText("Occupation cannot be blank!");
            	else if (cBirthDate.equals(""))
            		tError.setText("Date of birth cannot be blank!");
            	else if (cAge > 150 || cAge < 0)
            		tError.setText("Invalid age!");
            	else if (cPhone.equals(""))
            		tError.setText("Phone number cannot be empty!");
            	else {				
        			try {
        				JSONObject newClient = new JSONObject();
        				JSONObject clientInfo = new JSONObject();
        				clientInfo.put("FName", cLastName);
        				clientInfo.put("LName", cFirstName);
        				clientInfo.put("Occupation", cOccupation);
        				clientInfo.put("PatientID", cID);
        				clientInfo.put("Birthday", cBirthDate);
        				clientInfo.put("Age", cAge);
        				clientInfo.put("Height", tHeight.getText().toString());
        				clientInfo.put("Weight", tWeight.getText().toString());
        				clientInfo.put("Phone", cPhone);
        				clientInfo.put("Employer", tEmployer.getText().toString());

        				newClient.put("PatientInfo", clientInfo);
        				newClient.put("EvaluationForm", "");
        				
        				mDatabase.getJSONArray("PT").getJSONObject(mPosition).
        							getJSONArray("Patient").put(mPatientPosition, newClient);
        				
        				// save to file outside of AM
        				OutputStream output  = new BufferedOutputStream(new FileOutputStream(filedir + "/" + FILENAME));
        				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
        				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        				
        				bufferedWriter.write(mDatabase.toString());
        				bufferedWriter.close();
        				output.close();
        				
    					Intent in = new Intent(getActivity(), DisplayClientActivity.class);
    					in.putExtra("position", mPosition);
    					in.putExtra("isManager", isManager);
    					startActivity(in);
        			} catch (JSONException e) {
        				Log.d("Error: ", "JSON");
        			} catch (IOException e) {
        				Log.d("Error: ", "IO");
        			}
        			
            	}
			}
       });
       
       return rootView;
    }
    
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((FormActivity) activity).onSectionAttached(1);
	}

}
