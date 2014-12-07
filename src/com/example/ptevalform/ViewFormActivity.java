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
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFormActivity extends Activity {
	final static String FILENAME = "database.json";
	private JSONObject mDatabase;
	private int mPosition, mPatientPosition;
	private String filedir;
	private JSONArray mQuestion;
	private static boolean isManager, sFormType;
	
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
		setContentView(R.layout.activity_view_form);
		
		// get database
		Intent in = getIntent();
		filedir = in.getExtras().getString("filedir");
		mPosition = in.getExtras().getInt("position");
		mPatientPosition = in.getExtras().getInt("patientPosition");
		isManager = in.getExtras().getBoolean("isManager");
		if (in.getExtras().getBoolean("per-visit")) {
			sFormType = true;
			setTitle("Per-visit Form");
		}
		else {
			sFormType = false;
			setTitle("Quarterly Assessment Form");
		}
		
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
			if (sFormType)
				mQuestion = mDatabase.getJSONArray("PT").getJSONObject(mPosition).getJSONArray("Patient").
					getJSONObject(mPatientPosition).getJSONObject("Per-visit").getJSONArray("QuestionArray");
			else
				mQuestion = mDatabase.getJSONArray("PT").getJSONObject(mPosition).getJSONArray("Patient").
				getJSONObject(mPatientPosition).getJSONObject("QuarterlyAssessment").getJSONArray("QuestionArray");
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

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_form, menu);
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
			return true;
		}
		if (id == R.id.action_back) {
			super.onBackPressed();
		}
		return super.onOptionsItemSelected(item);
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
			return PlaceholderFragment.newInstance(position, getCount(), mQuestion.toString(), filedir,
					mPosition, mPatientPosition, mDatabase.toString());
			
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			int count = 0;
			for (int i = 0; i <= mQuestion.length(); i = i +7) {
				count += 1;
			}
			return count;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static int sPosition, sPatientPosition, sSize;
		private static String sFiledir, sData, sQuestion;
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber, int count, String question,
				String xFiledir, int xPosition, int xPatientPosition, String data) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//			args.putInt("size", count);
//			args.putString("question", question);
//			args.putString("filedir", xFiledir);
//			args.putInt("mPosition", xPosition);
//			args.putInt("mPatientPosition", xPatientPosition);
			fragment.setArguments(args);
			if (sectionNumber == 0) {
				sPosition = xPosition;
				sPatientPosition = xPatientPosition;
				sSize = count;
				sQuestion = question;
				sFiledir = xFiledir;
				sData = data;
			}
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_view_form,
					container, false);
			int fragNumber = getArguments().getInt(ARG_SECTION_NUMBER);
//			int size = getArguments().getInt("size");
//			String data = getArguments().getString("question");
			
			LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
		    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		    params.leftMargin = 5;
		    
		    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		    params1.leftMargin = 10;
		    
		    if (fragNumber != sSize - 1) {
	    		try {
    				JSONArray mQuestion = new JSONArray(sQuestion);
	    			for (int i = fragNumber * 7; i < fragNumber * 7 + 7 ; i++) {
	    				TextView text1 = new TextView(getActivity());
	    				text1.setText((i+1) + ". " + mQuestion.getJSONObject(i).getString("Title"));
	    				//text1.setBackgroundColor(getResources().getColor(R.color.lightblue2));
	    				text1.setTextColor(getResources().getColor(R.color.lightblue2));

	    				TextView text2 = new TextView(getActivity());
	    				text2.setText(mQuestion.getJSONObject(i).getString("Answer"));

	    				mLinearLayout.addView(text1, params);
	    				mLinearLayout.addView(text2, params1);
	    			}
	    		} catch (JSONException e) { Log.d("error", " JSON"); };
		    }
		    else {
		    	try {
    				JSONArray mQuestion = new JSONArray(sQuestion);
		    		for (int i = fragNumber * 7; i < mQuestion.length() ; i++) {
	    				TextView text1 = new TextView(getActivity());
	    				text1.setText((i+1) + ". " + mQuestion.getJSONObject(i).getString("Title"));
	    				//text1.setBackgroundColor(getResources().getColor(R.color.lightblue2));
	    				text1.setTextColor(getResources().getColor(R.color.lightblue2));

	    				TextView text2 = new TextView(getActivity());
	    				text2.setText(mQuestion.getJSONObject(i).getString("Answer"));

	    				mLinearLayout.addView(text1, params);
	    				mLinearLayout.addView(text2, params1);
	    			}
		    		
		    		if (isManager) {
		    			// feedback section
		    			final JSONObject mDatabase = new JSONObject(sData);
		    			final EditText editText = new EditText(getActivity());
		    			try {
		    				if (sFormType) 
		    					editText.setText(mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
		    						getJSONObject(sPatientPosition).getJSONObject("Per-visit").getString("FeedBack"));
		    				else
		    					editText.setText(mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
			    						getJSONObject(sPatientPosition).getJSONObject("QuarterlyAssessment").getString("FeedBack"));
		    			} catch (JSONException e) {
		    				editText.setHint("Feedbacks & Comments");
		    			}
		    			mLinearLayout.addView(editText, params);

		    			Button btnFeedback = new Button(getActivity());
		    			btnFeedback.setText("Submit Feedback");
		    			
		    			mLinearLayout.addView(btnFeedback, params);
		    			btnFeedback.setOnClickListener(new OnClickListener(){
		    				@Override
		    				public void onClick(View v) {
		    					String text = editText.getText().toString();
		    					try {
		    						if (sFormType)
		    							mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
		    								getJSONObject(sPatientPosition).getJSONObject("Per-visit").put("FeedBack", text);
		    						else
		    							mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
	    									getJSONObject(sPatientPosition).getJSONObject("QuarterlyAssessment").put("FeedBack", text);
		    							
		    						// save to file
		    						OutputStream output  = new BufferedOutputStream(new FileOutputStream(sFiledir + "/" + FILENAME));
		    						OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
		    						BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		    						bufferedWriter.write(mDatabase.toString());
		    						bufferedWriter.close();
		    						output.close();

		    						// return to displayclientactivity
		    						Toast.makeText(getActivity(), "Successfully submitted!", Toast.LENGTH_SHORT).show();
		    						Intent in = new Intent(getActivity(), DisplayClientActivity.class);
		    						in.putExtra("position", sPosition);
		    						in.putExtra("isManager", isManager);
		    						startActivity(in);
		    					} catch (JSONException e) {Log.d("this:", " error"); }
		    					catch (IOException e) {Log.d("ioexception", "eesdf"); };
		    				}
		    			});		
		    		}
		    		else {
		    			final JSONObject mDatabase = new JSONObject(sData);
		    			TextView mText = new TextView(getActivity());
		    			mText.setText("Manager's Feedback:");
		    			mText.setTextSize(18);
		    			mText.setTextColor(getResources().getColor(R.color.lightblue2));
		    			mLinearLayout.addView(mText, params);
		    			
		    			final TextView text = new TextView(getActivity());
		    			try {
		    				if (sFormType)
		    					text.setText(mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
		    						getJSONObject(sPatientPosition).getJSONObject("Per-visit").getString("FeedBack"));
		    				else
		    					text.setText(mDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
			    						getJSONObject(sPatientPosition).getJSONObject("QuarterlyAssessment").getString("FeedBack"));
		    			} catch (JSONException e) {
		    				text.setText("There are no feedbacks from the managers!");
		    			}
		    			mLinearLayout.addView(text, params);

		    			Button btnBack = new Button(getActivity());
		    			btnBack.setText("Go Back");
		    			params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		    			mLinearLayout.addView(btnBack, params);
		    			btnBack.setOnClickListener(new OnClickListener(){
		    				@Override
		    				public void onClick(View v) {
	    						Intent in = new Intent(getActivity(), DisplayClientActivity.class);
	    						in.putExtra("position", sPosition);
	    						in.putExtra("isManager", isManager);
	    						startActivity(in);
		    				}
		    			});
		    		}
		    	} catch (JSONException e) { Log.d("error 1", " JSON"); };		
		    }
			return rootView;
		}
	}

}
