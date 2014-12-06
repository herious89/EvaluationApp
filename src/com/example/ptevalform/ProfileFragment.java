package com.example.ptevalform;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
	Button mSubmit;
	
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
       mSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
       mSubmit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Profile Successfully editted!", Toast.LENGTH_SHORT).show();
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
