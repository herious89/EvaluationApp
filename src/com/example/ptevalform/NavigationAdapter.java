package com.example.ptevalform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NavigationAdapter extends BaseAdapter {

	private static final String[] PAGE = {"Client Profile", "Per-visit", "Quarterly Assessment"};
	private static final String[] STT = { "1", "2", "3"};
	private LayoutInflater mInflater;
	
	public NavigationAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return PAGE.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {
		ListHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_navigation, null);
			holder = new ListHolder();
			holder.sNumber = (TextView) convertView.findViewById(R.id.textview1);
			holder.sName = (TextView) convertView.findViewById(R.id.textview2);
			convertView.setTag(holder);
		} else {
			holder = (ListHolder) convertView.getTag();
		}

		holder.sNumber.setText(STT[position]);
		holder.sName.setText(PAGE[position]);
		return convertView;
	}


	static class ListHolder {
		TextView sNumber;
		TextView sName;
	}
}
