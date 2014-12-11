package com.example.ptevalform;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFormActivity extends Activity implements ActionBar.TabListener {
	final static String FILENAME = "database.json";
	private static JSONObject mDatabase;
	private static int sPosition, sPatientPosition;
	private static String filedir;
	private static JSONArray sPervisit, sQuarterly;
	private static boolean isManager;
	private static ArrayList<ArrayAdapter<String>> adapterList = new ArrayList<ArrayAdapter<String>>();
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_form);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);

		Intent in = getIntent();
		filedir = in.getExtras().getString("filedir");
		sPosition = in.getExtras().getInt("position");
		sPatientPosition = in.getExtras().getInt("patientPosition");
		isManager = in.getExtras().getBoolean("isManager");
		
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

			sPervisit = mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
					getJSONObject(sPatientPosition).getJSONArray("Per-visit");
			sQuarterly = mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
					getJSONObject(sPatientPosition).getJSONArray("QuarterlyAssessment");
			is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			Log.d("123", "error1");
		} catch (JSONException e) {
			Log.d("123", "JSON error");
		}

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_form, menu);
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
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			return true;
		}
		if (id == R.id.action_back) {
			super.onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_tab1).toUpperCase(l);
			case 1:
				return getString(R.string.title_tab2).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
	
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}	
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_list_form,
					container, false);
			final int fragNumber = getArguments().getInt(ARG_SECTION_NUMBER);
			ListView mList = (ListView) rootView.findViewById(R.id.listView);
			ArrayList<String> list = new ArrayList<String>();
    		try {
				if (fragNumber == 0) {
					if (sPervisit == null)
						return rootView;
					for (int i = 0; i < sPervisit.length(); i++) {
						if (sPervisit.getJSONObject(i).has("FeedBack"))
							list.add("** " + sPervisit.getJSONObject(i).getString("Date"));
						else 
							list.add("    " + sPervisit.getJSONObject(i).getString("Date"));
					}
				}
				else {
					if (sQuarterly == null)
						return rootView;
					for (int i = 0; i < sQuarterly.length(); i++) {
						if (sQuarterly.getJSONObject(i).has("FeedBack"))
							list.add("** " + sQuarterly.getJSONObject(i).getString("Date"));
						else
							list.add("    " + sQuarterly.getJSONObject(i).getString("Date"));
					}
				}
    		} catch (JSONException e) {Log.d("this is ", "error");};
    		
			ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), R.layout.list_form,
					R.id.name, list);
			adapterList.add(adapter);
			mList.setAdapter(adapter);
			final Activity cont = getActivity();
			mList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(cont, ViewFormActivity.class);
					intent.putExtra("filedir", filedir);
					intent.putExtra("position", sPosition);
					intent.putExtra("patientPosition", sPatientPosition);
					intent.putExtra("formPosition", position);
					intent.putExtra("isManager", isManager);
					if (fragNumber == 0)
						intent.putExtra("per-visit", true);
					else
						intent.putExtra("per-visit", false);
					startActivity(intent);
					cont.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				}
			});
			return rootView;
		}
	}

}
