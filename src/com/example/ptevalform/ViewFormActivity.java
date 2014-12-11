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

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

public class ViewFormActivity extends FragmentActivity {
	final static String FILENAME = "database.json";
	private static JSONObject sDatabase;
	private static int sPosition, sPatientPosition, sFormPosition;
	private static String filedir;
	private static JSONArray sQuestion, sFormArray;
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
		sPosition = in.getExtras().getInt("position");
		sPatientPosition = in.getExtras().getInt("patientPosition");
		sFormPosition = in.getExtras().getInt("formPosition");
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
			sDatabase = new JSONObject(database);
			if (sFormType) {
				sFormArray = sDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
						getJSONObject(sPatientPosition).getJSONArray("Per-visit");
			}
			else
				sFormArray = sDatabase.getJSONArray("PT").getJSONObject(sPosition).getJSONArray("Patient").
				getJSONObject(sPatientPosition).getJSONArray("QuarterlyAssessment");
			
			sQuestion = sFormArray.getJSONObject(sFormPosition).getJSONArray("QuestionArray");
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
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			return true;
		}
		if (id == R.id.action_back) {
			super.onBackPressed();
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
			return PlaceholderFragment.newInstance(position, getCount());
			
		}

		@Override
		public int getCount() {
			// Show total pages.
			int count = 0;
			for (int i = 0; i <= sQuestion.length(); i = i +7) {
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
		private static int sSize;
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber, int count) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			if (sectionNumber == 0) {
				sSize = count;
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
			
			LinearLayout mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
		    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		    params.leftMargin = 5;
		    
		    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		    params1.leftMargin = 10;
		    
		    if (fragNumber != sSize - 1) {
	    		try {
	    			for (int i = fragNumber * 7; i < fragNumber * 7 + 7 ; i++) {
	    				TextView text1 = new TextView(getActivity());
	    				text1.setText((i+1) + ". " + sQuestion.getJSONObject(i).getString("Title"));
	    				text1.setTextColor(getResources().getColor(R.color.orange));
		    			text1.setTypeface(text1.getTypeface(), Typeface.BOLD);

	    				TextView text2 = new TextView(getActivity());
	    				text2.setText(sQuestion.getJSONObject(i).getString("Answer"));

	    				mLinearLayout.addView(text1, params);
	    				mLinearLayout.addView(text2, params1);
	    			}
	    		} catch (JSONException e) { Log.d("error", " JSON"); };
		    }
		    else {
		    	try {
		    		for (int i = fragNumber * 7; i < sQuestion.length() ; i++) {
	    				TextView text1 = new TextView(getActivity());
	    				text1.setText((i+1) + ". " + sQuestion.getJSONObject(i).getString("Title"));
	    				text1.setTextColor(getResources().getColor(R.color.orange));

	    				TextView text2 = new TextView(getActivity());
	    				text2.setText(sQuestion.getJSONObject(i).getString("Answer"));

	    				mLinearLayout.addView(text1, params);
	    				mLinearLayout.addView(text2, params1);
	    			}
		    		
		    		if (isManager) {
		    			// feedback section
		    			final EditText editText = new EditText(getActivity());
		    			try {
		    				editText.setText(sFormArray.getJSONObject(sFormPosition).getString("FeedBack"));
		    			} catch (JSONException e) {
		    				editText.setHint("Feedbacks & Comments");
		    			}
		    			editText.setBackground(getResources().getDrawable(R.drawable.boxes));
		    			mLinearLayout.addView(editText, params);

		    			Button btnFeedback = new Button(getActivity());
		    			btnFeedback.setText("Submit Feedback");
		    			btnFeedback.setTextColor(getResources().getColor(R.color.purewhite));
		    		    params.topMargin = 20;
		    			btnFeedback.setBackground(getResources().getDrawable(R.drawable.greenbox));
		    			mLinearLayout.addView(btnFeedback, params);
		    			btnFeedback.setOnClickListener(new OnClickListener(){
		    				@Override
		    				public void onClick(View v) {
		    					String text = editText.getText().toString();
		    					try {
		    						sFormArray.getJSONObject(sFormPosition).put("FeedBack", text);

		    						// save to file
		    						OutputStream output  = new BufferedOutputStream(new FileOutputStream(filedir + "/" + FILENAME));
		    						OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
		    						BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		    						bufferedWriter.write(sDatabase.toString());
		    						bufferedWriter.close();
		    						output.close();

		    						// return to displayclient activity
		    						Toast.makeText(getActivity(), "Successfully submitted!", Toast.LENGTH_SHORT).show();
		    						getActivity().onBackPressed();
		    					} catch (JSONException e) {Log.d("this:", " error"); }
		    					catch (IOException e) {Log.d("ioexception", "eesdf"); };
		    				}
		    			});		
		    		}
		    		else {
		    			TextView mText = new TextView(getActivity());
		    			mText.setText("Manager's Feedback:");
		    			mText.setTextSize(18);
		    			mText.setTextColor(getResources().getColor(R.color.orange));
		    			mText.setTypeface(mText.getTypeface(), Typeface.BOLD);
		    			mLinearLayout.addView(mText, params);
		    			
		    			final TextView text = new TextView(getActivity());
		    			try {
		    				text.setText(sFormArray.getJSONObject(sFormPosition).getString("FeedBack"));
		    			} catch (JSONException e) {
		    				text.setText("There are no feedbacks from the managers!");
		    			}
		    			mLinearLayout.addView(text, params);

		    			Button btnBack = new Button(getActivity());
		    			btnBack.setText("Go Back");
		    			btnBack.setTextColor(getResources().getColor(R.color.purewhite));
		    		    params.topMargin = 20;
		    			params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		    			btnBack.setBackground(getResources().getDrawable(R.drawable.greenbox));
		    			mLinearLayout.addView(btnBack, params);
		    			btnBack.setOnClickListener(new OnClickListener(){
		    				@Override
		    				public void onClick(View v) {
	    						getActivity().onBackPressed();
		    				}
		    			});
		    		}
		    	} catch (JSONException e) { Log.d("error 1", " JSON"); };		
		    }
			return rootView;
		}
	}

}
