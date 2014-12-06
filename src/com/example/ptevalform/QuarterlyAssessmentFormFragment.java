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
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSparseArrays")
public class QuarterlyAssessmentFormFragment extends Fragment {
	private JSONObject mDatabase;
	final static String FILENAME = "database.json";
	private JSONArray mQuestion;
	private int mPosition, mPatientPosition;
	private LinearLayout mLinearLayout;
	private String filedir;
	private ArrayList<EditText> mEditText = new ArrayList<EditText>();
	@SuppressLint("UseSparseArrays")
	private HashMap<Integer, ArrayList<EditText>> mMapEditText = new HashMap<Integer, ArrayList<EditText>>();
	private HashMap<Integer, ArrayList<CheckBox>> mMapCheckBox = new HashMap<Integer, ArrayList<CheckBox>>();
	private HashMap<Integer, ArrayList<RadioButton>> mMapRadioButton = new HashMap<Integer, ArrayList<RadioButton>>();
	
	@Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_quarterly_assessment, container, false);
       Intent in = getActivity().getIntent();
       filedir = in.getExtras().getString("filedir");
       mPosition = in.getExtras().getInt("position");
       mPatientPosition = in.getExtras().getInt("patientPosition");
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
    	   mQuestion = mDatabase.getJSONObject("DefaultForm").getJSONArray("QuestionArray");
    	   is.close();
       } catch (IOException e1) {
    	   // TODO Auto-generated catch block
    	   Log.d("123", "error1");
       } catch (JSONException e) {
    	   Log.d("123", "JSON error");
       }
      
       mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       params.leftMargin = 5;
       
       for (int i = 0; i < mQuestion.length(); i++) {
    	   mEditText = new ArrayList<EditText> ();
    	   try {
    		   mQuestion.getJSONObject(i);
    		   TextView text = new TextView(getActivity());
    		   text.setText((i+1) + ". " + mQuestion.getJSONObject(i).getString("Title"));
    	       mLinearLayout.addView(text, params);
    	       
    	       try {
    	    	   LinearLayout.LayoutParams radioParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    	    	   params.leftMargin = 5;
    	    	   JSONArray radioArray =  mQuestion.getJSONObject(i).getJSONArray("RadioButton");
    	    	   RadioGroup group = new RadioGroup(getActivity());
    	    	   group.setOrientation(0);
    	    	   
    	    	   ArrayList<RadioButton> radioList = new ArrayList<RadioButton>();
    	    	   for (int j = 0; j < radioArray.length(); j++) {
        	    	   RadioButton radio = new RadioButton(getActivity());
        	    	   radio.setText(radioArray.getString(j));
        	    	   group.addView(radio, j, radioParams);
        	    	   radioList.add(radio);
    	    	   }
    	    	   mMapRadioButton.put(i, radioList);
    	    	   mLinearLayout.addView(group, params);
    	    	   
    	       } catch (JSONException e) { };
    	       
    	       try {
    	    	   JSONArray checkArray =  mQuestion.getJSONObject(i).getJSONArray("CheckBox");
    	    	   ArrayList<CheckBox> mCheckList = new ArrayList<CheckBox>();
    	    	   for (int j = 0; j < checkArray.length(); j++) {
        	    	   CheckBox check = new CheckBox(getActivity());
        	    	   check.setText(checkArray.getString(j));
        	    	   mCheckList.add(check);
        	    	   mLinearLayout.addView(check, params);
    	    	   }
    	    	   mMapCheckBox.put(i, mCheckList);
    	    	   
    	       } catch (JSONException e) { };
    	       
    	       try {
    	    	   EditText editText = new EditText(getActivity());
    	    	   editText.setHint(mQuestion.getJSONObject(i).getString("EditText"));
    	    	   mEditText.add(editText);
    	    	   mLinearLayout.addView(editText, params);
    	    	   mMapEditText.put(i, mEditText);
    	       } catch (JSONException e) {};
    
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("This is", " JSONArray");
			}
		}
    	   
       
       Button mSubmit = new Button(getActivity());
       mSubmit.setText("Submit Form");
       mLinearLayout.addView(mSubmit, params);
       
       mSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String answer = "";
				JSONArray questionArray = new JSONArray();
				
				for (int i = 0; i < mQuestion.length(); i++) {			
					ArrayList<RadioButton> radioList = mMapRadioButton.get(i);
					if (radioList != null) {
						for (int j = 0; j < radioList.size(); j++)
						{
							if (radioList.get(j).isChecked())
								answer = radioList.get(j).getText().toString();
						}
					}
					
					
					ArrayList<CheckBox> checkList = mMapCheckBox.get(i);
					if (checkList != null) {
						for (int j = 0; j < checkList.size(); j++)
						{
							if (checkList.get(j).isChecked()) 
								answer += ", " + checkList.get(j).getText().toString();
						}
					}
					
					ArrayList<EditText> editTextList = mMapEditText.get(i);
					if (editTextList != null)
					{
						for (int j = 0; j < editTextList.size(); j++)
						{
							if (checkList != null || radioList != null)
								answer += ", " +  editTextList.get(j).getText().toString();
							else
								answer = editTextList.get(j).getText().toString();
						}
					}
					try {
						JSONObject question = new JSONObject();
						question.put("Title", mQuestion.getJSONObject(i).getString("Title"));
						question.put("Text", answer);
						questionArray.put(i, question);
					} catch (JSONException e) { } ;
				}

				try {
					mDatabase.getJSONArray("PT").getJSONObject(mPosition).getJSONArray("Patient").
						getJSONObject(mPatientPosition).getJSONObject("EvaluationForm").remove("QuestionArray");
					mDatabase.getJSONArray("PT").getJSONObject(mPosition).getJSONArray("Patient").
					getJSONObject(mPatientPosition).getJSONObject("EvaluationForm").put("QuestionArray", questionArray);
					
					// save to file
					OutputStream output  = new BufferedOutputStream(new FileOutputStream(filedir + "/" + FILENAME));
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
					BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
					
					bufferedWriter.write(mDatabase.toString());
					bufferedWriter.close();
					output.close();
					
					// return to displayclientactivity
					Toast.makeText(getActivity(), "Successfully submitted!", Toast.LENGTH_SHORT).show();
					Intent in = new Intent(getActivity(), DisplayClientActivity.class);
					in.putExtra("position", mPosition);
					startActivity(in);
				} catch (JSONException e) {Log.d("this:", " error"); }
				catch (IOException e) {Log.d("ioexception", "eesdf"); };
			}
       });
       
       return rootView;
    }
    
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((FormActivity) activity).onSectionAttached(3);
	}
}
