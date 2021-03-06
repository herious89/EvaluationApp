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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// database file
	final String FILENAME = "database.json";
	private JSONObject mDatabase;
	JSONArray mPT, mManager;
	boolean isFound, isManager;
	int mPosition;
	private String mManagerName;
	
	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

	    Animation a = AnimationUtils.loadAnimation(this, R.anim.push_down);
	    a.setInterpolator(new Interpolator() {
	        private final int frameCount = 8;

	        @Override
	        public float getInterpolation(float input) {
	            return (float)Math.floor(input*frameCount)/frameCount;
	        }
	    });
	    a.setDuration(100);
		
		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);
		
		// get database from internal storage
		// deleteFile(FILENAME);
		String[] fileList = fileList();
		boolean isFileFound = false;
		for (String s : fileList) {
			Log.d("READDDDD", s);
			if (s.equalsIgnoreCase(FILENAME))
				isFileFound = true;
		}
			
		if (isFileFound) {
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
		}
		else {
			AssetManager am = getApplicationContext().getAssets();
			String database = "";
			try {
				// read file from AM
				InputStream is = am.open(FILENAME);
				InputStreamReader inputStreamReader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ( (receiveString = bufferedReader.readLine()) != null ) {
					stringBuilder.append(receiveString);
				}

				is.close();
				database = stringBuilder.toString();	
				mDatabase = new JSONObject(database);
		
				// save to file outside of AM
				OutputStream output  = new BufferedOutputStream(new FileOutputStream((getFilesDir() + "/" + FILENAME)));
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				bufferedWriter.write(database);
				bufferedWriter.close();
				output.close();
				
				Log.d("READDDDD", " from AM");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.d("123", "error1");
			} catch (JSONException e) {
				Log.d("123", "JSON error");
			}
		}
		
		
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}
		
//		try {
//			mPT = mDatabase.getJSONArray("PT");
//			isFound = false;
//			mPosition = 0;
//		for (int i = 0; i < mPT.length(); i++) {
//			if (mEmail.equalsIgnoreCase(mPT.getJSONObject(i).getString("username")) &&
//					mPassword.equals(mPT.getJSONObject(i).getString("password"))) {
//				isFound = true;
//				mPosition = i;
//			}
//		}
//		} catch (JSONException e) {
//			Log.d("this is", "JSON error");
//		}
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		}		
		else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				
				try {
					mPT = mDatabase.getJSONArray("PT");
					mManager = mDatabase.getJSONArray("Manager");
					isFound = false;
					mPosition = 0;
				for (int i = 0; i < mPT.length(); i++) {
					if (mEmail.equalsIgnoreCase(mPT.getJSONObject(i).getString("username")) &&
							mPassword.equals(mPT.getJSONObject(i).getString("password"))) {
						isFound = true;
						mPosition = i;
					}
				}
				if (!isFound) {
					for (int i = 0; i < mManager.length(); i++) {
						if (mEmail.equalsIgnoreCase(mManager.getJSONObject(i).getString("username")) &&
								mPassword.equals(mManager.getJSONObject(i).getString("password"))) {
							isManager = true;
							isFound = true;
							mManagerName = mManager.getJSONObject(i).getString("managerName");
						}
					}
				}
				} catch (JSONException e) {
					Log.d("this is", "JSON error");
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}
			
			if (!isFound)
				return false;
			return true;
//			for (String credential : DUMMY_CREDENTIALS) {
//				String[] pieces = credential.split(":");
//				if (pieces[0].equals(mEmail)) {
//					// Account exists, return true if the password matches.
//					return pieces[1].equals(mPassword);
//				}
//			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				if (isManager) {
					//Intent in = new Intent(getApplicationContext(), FormActivity.class);
					Intent in = new Intent(getApplicationContext(), ManagerActivity.class);
					in.putExtra("name", mManagerName);
					startActivity(in);
					overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
				}
				else {
					//Intent in = new Intent(getApplicationContext(), FormActivity.class);
					Intent in = new Intent(getApplicationContext(), DisplayClientActivity.class);
					in.putExtra("position", mPosition);
					in.putExtra("isManager", false);
					startActivity(in);
					overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
				}
			} else {
				mEmailView
						.setError(getString(R.string.error_incorrect_username_password));
				mEmailView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
