package com.example.ptevalform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayClientActivity extends ActionBarActivity {

	private ArrayList<Client> mClientArray = new ArrayList<Client>();
	private ClientAdapter mAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_client);
	    
		mClientArray.add(new Client(777, "Du", "A", "Dota Player", "2/20/2020", "Will"));
		mClientArray.add(new Client(777, "Xanh", "Rauma", "Dota Player", "2/15/1920", "Will"));
		mClientArray.add(new Client(777, "Hummer", "xDriver", "Dota Player", "3/10/1220", "Will"));
		mAdapter = new ClientAdapter(this, R.layout.list_client, mClientArray);
		ListView mList = (ListView) findViewById(R.id.listView);
		
		mList.setItemsCanFocus(false);
		mList.setAdapter(mAdapter);	

		// Listview on item click listener
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// need to pass client info
				
				Intent in = new Intent(getApplicationContext(), FormActivity.class);
				startActivity(in);
			}
		});
		
		// need to implement call back
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// implement add new client for menu
	private void addNewClient() {
        AlertDialog.Builder alert = new AlertDialog.Builder(DisplayClientActivity.this);
        alert.setTitle("New Client Profile");
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.fragment_profile_add, null);
        alert.setView(alertLayout);
                
        final EditText firstName = (EditText) alertLayout.findViewById(R.id.getFirstName);
        final EditText lastName = (EditText) alertLayout.findViewById(R.id.getLastName);
        final EditText occupation = (EditText) alertLayout.findViewById(R.id.getOccupation);
        final EditText birthDate = (EditText) alertLayout.findViewById(R.id.getBirthDate);
        final EditText age = (EditText) alertLayout.findViewById(R.id.getAge);
        final EditText height = (EditText) alertLayout.findViewById(R.id.getHeight);
        final EditText weight = (EditText) alertLayout.findViewById(R.id.getWeight);
        final EditText phone = (EditText) alertLayout.findViewById(R.id.getPhoneNumber);
        final EditText employer = (EditText) alertLayout.findViewById(R.id.getEmployer);
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
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String occ = occupation.getText().toString();
                String DOB = birthDate.getText().toString();
                
            	if (fName.equals(""))
            		error.setText("First name cannot be blank!");
            	else if (lName.equals(""))
            		error.setText("Last name cannot be blank!");
            	else if (occ.equals(""))
            		error.setText("Occupation cannot be blank!");
            	else if (DOB.equals(""))
            		error.setText("Date of birth cannot be blank!");
            	else {
            		// get current PTName
            		String ptName = "Will";
            		mClientArray.add(new Client(777, fName, lName, occ, DOB, ptName));
            		mAdapter.notifyDataSetChanged();
            		closeDialog = true;
            	}
                if(closeDialog)
                    dialog.dismiss();
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
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
}
