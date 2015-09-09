package com.newsfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoDetalle extends Fragment {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.e("Test","hello" );
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		View view = inflater.inflate(R.layout.detalle,container,false);
		return view;
	}
	public void setText(String item){
		TextView view= (TextView)getView().findViewById(R.id.detailsText);
		view.setText(item);
	}
}
