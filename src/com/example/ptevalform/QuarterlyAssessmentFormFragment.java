package com.example.ptevalform;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuarterlyAssessmentFormFragment extends Fragment {
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_quarterly_assessment, container, false);

       return rootView;
    }
    
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((FormActivity) activity).onSectionAttached(2);
	}

}
