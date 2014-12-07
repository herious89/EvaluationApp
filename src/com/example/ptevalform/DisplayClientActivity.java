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
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class DisplayClientActivity extends ActionBarActivity {

	private ArrayList<Client> mClientArray;
	private ClientAdapter mAdapter = null;
	private JSONObject mDatabase, mClient ;
	private JSONArray patientArray;
	private String ptName = "";
	final static String FILENAME = "database.json";
	int mPosition;
	private boolean isManager;
	private Activity mActivity = this;
	final String[] PTCHOICE = {"Edit Client's Info.", "Per-visit Evaluation", "Quarterly Assessment",
			"View Per-visit Form & Feedback", "View Quarterly Assessment & Feedback"};
	final String[] MANAGERCHOICE = {"Edit Client's Info.", "Per-visit Evaluation", "Quarterly Assessment",
					"View Per-visit Form & Provide Feedback", "View Quarterly Assessment & Provide Feedback"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_client);
		// disable home button
		getSupportActionBar ().setHomeButtonEnabled(false);
		getSupportActionBar ().setDisplayHomeAsUpEnabled(false);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mClientArray = new ArrayList<Client>();
		Intent in = getIntent();
		mPosition = in.getExtras().getInt("position");
		isManager = in.getExtras().getBoolean("isManager");
		
		try {
			String database = "";
			InputStream is  = new BufferedInputStream(new FileInputStream((getFilesDir() + "/" + FILENAME)));

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
		
		try {
			mClient = mDatabase.getJSONArray("PT").getJSONObject(mPosition);
			patientArray = mClient.getJSONArray("Patient");
			for (int i = 0; i < patientArray.length(); i++) {
				JSONObject patient = patientArray.getJSONObject(i).getJSONObject("PatientInfo");
				String lastName = patient.getString("LName"),
				firstName = patient.getString("FName"),
				occupation = patient.getString("Occupation"),
				birthDate = patient.getString("Birthday"),
				height = patient.getString("Height"),
				weight = patient.getString("Weight"),
				phone = patient.getString("Phone"),
				employer = patient.getString("Employer");
				ptName = mClient.getString("ptname");
				
				int age = patient.getInt("Age"),
				id = patient.getInt("PatientID");
				
				mClientArray.add(new Client(id, firstName, lastName, occupation, birthDate,
						age, height, weight, phone, employer, ptName));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("this is", " JSON error");			
		}
		
		setTitle("PT - " + ptName);
		
		mAdapter = new ClientAdapter(this, R.layout.list_client, mClientArray);
		ListView mList = (ListView) findViewById(R.id.listView);
		
		mList.setItemsCanFocus(false);
		mList.setAdapter(mAdapter);	

		// Listview on item click listener
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				final Intent in = new Intent(getApplicationContext(), FormActivity.class);
				Client client = mClientArray.get(position);
				in.putExtra("ptPosition", mPosition);
				in.putExtra("patientPosition", position);
				in.putExtra("clientID", client.getClientID());
				in.putExtra("lastName", client.getLastName());
				in.putExtra("firstName", client.getFirstName());
				in.putExtra("occupation", client.getOccupation());
				in.putExtra("birthDate", client.getBirthDate());
				in.putExtra("age", client.getAge());
				in.putExtra("height", client.getHeight());
				in.putExtra("weight", client.getWeight());
				in.putExtra("phone", client.getPhoneNumber());
				in.putExtra("employer", client.getEmployerName());
				in.putExtra("filedir", getFilesDir() + "");
				
            	final Intent intent = new Intent(getApplicationContext(), ViewFormActivity.class);
				intent.putExtra("filedir", getFilesDir() + "");
				intent.putExtra("ptPosition", mPosition);
				intent.putExtra("patientPosition", position);
				
				if (isManager == false) {
					in.putExtra("isManager", false);
					intent.putExtra("isManager", false);
					AlertDialog.Builder alert = new AlertDialog.Builder(DisplayClientActivity.this);
					alert.setTitle("What would you like to do?");
			        LayoutInflater inflater = getLayoutInflater();
			        View alertLayout = inflater.inflate(R.layout.fragment_pt_choice, null);
			     	    	    	
			        // create radiobutton for choices
			        LinearLayout mLinearLayout = (LinearLayout) alertLayout.findViewById(R.id.linearLayout);
	    	    	final ArrayList<RadioButton> radioList = new ArrayList<RadioButton>();
	    	    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	    	    	params.leftMargin = 5;
	    	    	RadioGroup group = new RadioGroup(mActivity);
	    	    	for (int i = 0; i < PTCHOICE.length; i++) {
		    	    	RadioButton radio = new RadioButton(mActivity);
		    	    	radio.setText(PTCHOICE[i]);
		    	    	group.addView(radio, i, params);
		    	    	radioList.add(radio);
	    	    	}
	    	    	mLinearLayout.addView(group, params);
	    	    	
			        alert.setView(alertLayout);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			            	int choice = -1;
			            	for (int i = 0; i < radioList.size(); i++)
			            		if (radioList.get(i).isChecked())
			            			choice = i;
			            	
			            	switch (choice) {
			            		case -1 : dialog.cancel(); break;
			            		case 0 : 
			            			in.putExtra("section", 0);
			            			startActivity(in);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            		case 1 : 
			            			in.putExtra("section", 1);
			            			startActivity(in);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            		case 2 : 
		            				in.putExtra("section", 2);
		            				startActivity(in);
		            				overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
		            				break;
			            		case 3 : 
			            			intent.putExtra("per-visit", true);
	            					startActivity(intent);
	            					overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
		            				break;
			            		case 4 : 
			            			intent.putExtra("per-visit", false);
			            			startActivity(intent);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
		            				break;
			            	}
			            }
			        });
			        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
			            }
			        });
			        alert.show();
				}
				// manager view
				else {
					in.putExtra("isManager", true);
					intent.putExtra("isManager", true);
					AlertDialog.Builder alert = new AlertDialog.Builder(DisplayClientActivity.this);
					alert.setTitle("What would you like to do?");
			        
			        LayoutInflater inflater = getLayoutInflater();
			        View alertLayout = inflater.inflate(R.layout.fragment_pt_choice, null);
			        
			        // create radiobutton for choices
			        LinearLayout mLinearLayout = (LinearLayout) alertLayout.findViewById(R.id.linearLayout);
	    	    	final ArrayList<RadioButton> radioList = new ArrayList<RadioButton>();
	    	    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	    	    	params.leftMargin = 5;
	    	    	RadioGroup group = new RadioGroup(mActivity);
	    	    	for (int i = 0; i < MANAGERCHOICE.length; i++) {
		    	    	RadioButton radio = new RadioButton(mActivity);
		    	    	radio.setText(MANAGERCHOICE[i]);
		    	    	group.addView(radio, i, params);
		    	    	radioList.add(radio);
	    	    	}
	    	    	mLinearLayout.addView(group, params);
	    	    	
			        alert.setView(alertLayout);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			            	int choice = -1;
			            	for (int i = 0; i < radioList.size(); i++)
			            		if (radioList.get(i).isChecked())
			            			choice = i;
			            	
			            	switch (choice) {
			            		case -1 : dialog.cancel(); break;
			            		case 0 : 
			            			in.putExtra("section", 0);
			            			startActivity(in);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            		case 1 : 
			            			in.putExtra("section", 1);
			            			startActivity(in);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            		case 2 : 
		            				in.putExtra("section", 2);
		            				startActivity(in);
		            				overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
		            				break;
			            		case 3 :
			            			intent.putExtra("per-visit", true); 
			            			startActivity(intent);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            		case 4 : 
			            			intent.putExtra("per-visit", false);
			            			startActivity(intent);
			            			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			            			break;
			            	}
			            }
			        });
					alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
			            }
			        });
			        alert.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    if (isManager)
	    	 inflater.inflate(R.menu.display_client_manager, menu);
	    else
	    	inflater.inflate(R.menu.display_client, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		
	    return true;
	} 
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_sort:
	    		return true;
	        case R.id.action_last_name_az:
	        	sortClients(0);
	        	return true;
	        case R.id.action_first_name_az:
	        	sortClients(1);
	            return true;
	        case R.id.action_last_name_za:
	        	sortClients(2);
	            return true;
	        case R.id.action_first_name_za:
	        	sortClients(3);
	            return true;
	        case R.id.action_search:
	            return true;
	        case R.id.action_new:
	        	addNewClient();
	            return true;
	        case R.id.action_log_out:
	        	logout();
	        	return true;
	        case R.id.action_back:
	        	super.onBackPressed();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// implement add new client for menu
	@SuppressLint("InflateParams")
	private void addNewClient() {
        AlertDialog.Builder alert = new AlertDialog.Builder(DisplayClientActivity.this);
        alert.setTitle("New Client Profile");
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.fragment_profile_add, null);
        alert.setView(alertLayout);
                
        final EditText clientId = (EditText) alertLayout.findViewById(R.id.clientID);
        final EditText firstName = (EditText) alertLayout.findViewById(R.id.getFirstName);
        final EditText lastName = (EditText) alertLayout.findViewById(R.id.getLastName);
        final EditText occupation = (EditText) alertLayout.findViewById(R.id.getOccupation);
        final EditText birthDate = (EditText) alertLayout.findViewById(R.id.getBirthDate);
        final EditText getAge = (EditText) alertLayout.findViewById(R.id.getAge);
        final EditText getHeight = (EditText) alertLayout.findViewById(R.id.getHeight);
        final EditText getWeight = (EditText) alertLayout.findViewById(R.id.getWeight);
        final EditText getPhone = (EditText) alertLayout.findViewById(R.id.getPhoneNumber);
        final EditText getEmployer = (EditText) alertLayout.findViewById(R.id.getEmployer);
        final TextView error = (TextView) alertLayout.findViewById(R.id.error);
        
        alert.setPositiveButton(R.string.add_client, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        
        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
            }
        });
        
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {            
            @Override
            public void onClick(View v)
            {
                Boolean closeDialog = false;
                // checking input error
                int id = 0;
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String occ = occupation.getText().toString();
                String DOB = birthDate.getText().toString();
                int age = 0;
                String height = getHeight.getText().toString();
                String weight = getWeight.getText().toString();
                String phone = getPhone.getText().toString();
                String employer = getEmployer.getText().toString();
                
            	if (fName.equals(""))
            		error.setText("First name cannot be blank!");
            	else if (lName.equals(""))
            		error.setText("Last name cannot be blank!");
            	else if (occ.equals(""))
            		error.setText("Occupation cannot be blank!");
            	else if (DOB.equals(""))
            		error.setText("Date of birth cannot be blank!");
            	else if (getAge.getText().toString().equals(""))
            		error.setText("Age cannot be blank!");
            	else if (clientId.getText().toString().equals(""))
            		error.setText("Client ID cannot be blank!");
            	else if (phone.equals(""))
            		error.setText("Phone number cannot be blank!");
            	else {
            		// get current PTName
                	if (!getAge.getText().toString().equals("")) {
                		age = Integer.parseInt(getAge.getText().toString());
                		if (age > 150)
                			error.setText("Invalid age!");
                		else {
                			id = Integer.parseInt(clientId.getText().toString());
                			mClientArray.add(new Client(id, fName, lName, occ, DOB,
                					age, height, weight, phone, employer, ptName));
                			mAdapter.notifyDataSetChanged();
                			
                			try {
                				JSONObject newClient = new JSONObject();
                				JSONObject clientInfo = new JSONObject();
                				clientInfo.put("FName", fName);
                				clientInfo.put("LName", lName);
                				clientInfo.put("Occupation", occ);
                				clientInfo.put("PatientID", id);
                				clientInfo.put("Birthday", DOB);
                				clientInfo.put("Age", age);
                				clientInfo.put("Height", height);
                				clientInfo.put("Weight", weight);
                				clientInfo.put("Phone", phone);
                				clientInfo.put("Employer", employer);

                				newClient.put("PatientInfo", clientInfo);
                				newClient.put("Per-visit", "");
                				newClient.put("QuarterlyAssessment", "");
                				
                				mDatabase.getJSONArray("PT").getJSONObject(mPosition).
                										getJSONArray("Patient").put(newClient);
                				
                				deleteFile(FILENAME);
                				// save to file
                				OutputStream output  = new BufferedOutputStream(new FileOutputStream((getFilesDir() + "/" + FILENAME)));
                				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
                				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                				
                				bufferedWriter.write(mDatabase.toString());
                				bufferedWriter.close();
                				output.close();
                			} catch (JSONException e) {
                				Log.d("error 1", "JSON");
                			} catch (IOException e) {
                				Log.d("error 2", "IO");
                			}
                			
                			closeDialog = true;
                		}
                	}
            	}
                if(closeDialog)
                    dialog.dismiss();
            }
        });
	}
	
	private void sortClients(int selection) {
		switch (selection) {
			case 0:	Collections.sort(mClientArray, new Comparator<Client>() {
						@Override
						public int compare(Client  xClient1, Client  xClient2) {
							int result = xClient1.mLastName.compareTo(xClient2.mLastName);
							if (result == 0)
								return xClient1.mFirstName.compareTo(xClient2.mFirstName);
							return  result;
						}
    				});
			break;
			case 1: Collections.sort(mClientArray, new Comparator<Client>() {
						@Override
						public int compare(Client xClient1, Client  xClient2) {
							int result = xClient1.mFirstName.compareTo(xClient2.mFirstName);
							if (result == 0)
								return xClient1.mLastName.compareTo(xClient2.mLastName);
							return  result;
						}
					});
			break;
			case 2:	Collections.sort(mClientArray, new Comparator<Client>() {
						@Override
						public int compare(Client  xClient1, Client  xClient2) {
							int result = xClient2.mLastName.compareTo(xClient1.mLastName);
							if (result == 0)
								return xClient2.mFirstName.compareTo(xClient1.mFirstName);
							return  result;
						}
					});
			break;
			case 3: Collections.sort(mClientArray, new Comparator<Client>() {
						@Override
						public int compare(Client xClient1, Client  xClient2) {
							int result = xClient2.mFirstName.compareTo(xClient1.mFirstName);
							if (result == 0)
								return xClient2.mLastName.compareTo(xClient1.mLastName);
							return  result;
						}
					});
			break;
			default: break;
		}
		mAdapter.notifyDataSetChanged();
	}
	
	private void logout() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
	}
}
