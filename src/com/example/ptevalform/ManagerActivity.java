package com.example.ptevalform;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ManagerActivity extends ActionBarActivity {
	private ArrayList<String> mPTArray;
	private PTAdapter mAdapter = null;
	private JSONObject mDatabase;
	final static String FILENAME = "database.json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);
		// disable home button
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		mPTArray = new ArrayList<String>();
		
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
		
		JSONArray PTArray;
		try {
			PTArray= mDatabase.getJSONArray("PT");
			for (int i = 0; i < PTArray.length(); i++)
				mPTArray.add(PTArray.getJSONObject(i).getString("ptname"));
		} catch (JSONException e) {
			
		}
		Intent in = getIntent();
		setTitle("Manager - " + in.getExtras().getString("name"));
		
		mAdapter = new PTAdapter(this, R.layout.list_pt, mPTArray);
		ListView mList = (ListView) findViewById(R.id.listView);
		
		mList.setItemsCanFocus(false);
		mList.setAdapter(mAdapter);	
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent(getApplicationContext(), DisplayClientActivity.class);
				in.putExtra("position", position);
				in.putExtra("isManager", true);
				startActivity(in);
				overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_log_out) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
