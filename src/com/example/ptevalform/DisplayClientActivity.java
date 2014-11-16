package com.example.ptevalform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class DisplayClientActivity extends ActionBarActivity {

	private ArrayList<Client> mClientArray = new ArrayList<Client>();
	private ClientAdapter mAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_client);
	    
		mClientArray.add(new Client(777, "Will", "Du", "A", "Dota Player", "2/20/2020"));
		mClientArray.add(new Client(777, "Will", "Xanh", "Rauma", "Dota Player", "2/15/1920"));
		mClientArray.add(new Client(777, "Will", "Hummer", "xDriver", "Dota Player", "3/10/1220"));
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
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// implement add new client for menu
	
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
