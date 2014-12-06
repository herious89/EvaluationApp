package com.example.ptevalform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PTAdapter extends ArrayAdapter<String> {
	Context mContext;
	int mLayoutResourceId;
	ArrayList<String> mPTList = new ArrayList<String>();
	
	/**
	 * Constructor of the class with 3 arguments
	 */
	public PTAdapter(Context xContext, int xLayoutResourceId,
			ArrayList<String> xPTList) {
		super(xContext, xLayoutResourceId, xPTList);
		mContext = xContext;
		mLayoutResourceId = xLayoutResourceId;
		mPTList = xPTList;
	}
	
	/**
	 * The getView function provides the layout for the list
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PTHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
			holder = new PTHolder();
			holder.mTextName = (TextView) row.findViewById(R.id.name);
			row.setTag(holder);
		} else {
			holder = (PTHolder) row.getTag();
		}
		holder.mTextName.setText(mPTList.get(position));
		holder.mTextName.setSelected(true);
		holder.mTextName.requestFocus();
		return row;
	}
	
	static class PTHolder {
		TextView mTextName;
	}
	
	public int getCount() {
		return mPTList.size();
	}
}