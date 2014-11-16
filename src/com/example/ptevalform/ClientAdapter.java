package com.example.ptevalform;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ClientAdapter extends ArrayAdapter<Client> {
	Context mContext;
	int mLayoutResourceId;
	ArrayList<Client> mClientList = new ArrayList<Client>();
	
	/**
	 * Constructor of the class with 3 arguments
	 */
	public ClientAdapter(Context xContext, int xLayoutResourceId,
			ArrayList<Client> xClientList) {
		super(xContext, xLayoutResourceId, xClientList);
		mContext = xContext;
		mLayoutResourceId = xLayoutResourceId;
		mClientList = xClientList;
	}
	
	/**
	 * The getView function provides the layout for the list
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ClientHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
			holder = new ClientHolder();
			holder.mTextName = (TextView) row.findViewById(R.id.name);
			holder.mTextDOB = (TextView) row.findViewById(R.id.birthDate);
			holder.mTextAge = (TextView) row.findViewById(R.id.age);
//			holder.mBtnEdit = (Button) row.findViewById(R.id.btnEdit);
//			holder.mBtnEval = (Button) row.findViewById(R.id.btnEval);
			
//			holder.btnRate = (Button) row.findViewById(R.id.button2);
			row.setTag(holder);
		} else {
			holder = (ClientHolder) row.getTag();
		}
		Client m = mClientList.get(position);
		holder.mTextName.setText(m.getLastName() + ", " + m.getFirstName());
		holder.mTextName.setSelected(true);
		holder.mTextName.requestFocus();
		holder.mTextDOB.setText("DOB: " + m.getBirthDate());
		holder.mTextAge.setText("Age: " + m.getAge());
//		holder.mBtnEval.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.i("Rate Button Clicked", "**********");
//				Toast.makeText(context, "Rate button Clicked",
//						Toast.LENGTH_LONG).show();
//			}
//		});
		return row;
	}
	
	static class ClientHolder {
		TextView mTextName;
		TextView mTextDOB;
		TextView mTextAge;
//		Button mBtnEdit;
//		Button mBtnEval;
	}
	
	public int getCount() {
		return mClientList.size();
	}
}