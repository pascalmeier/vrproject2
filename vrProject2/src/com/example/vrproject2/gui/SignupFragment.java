package com.example.vrproject2.gui;


import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import com.example.vrproject2.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;

public class SignupFragment extends Fragment {

	public SignupFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		final View rootView = inflater.inflate(R.layout.fragment_signup,
				container, false);
		List<ParseObject> results;
		final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Attack");
		final ParseQuery<ParseObject> query1 = ParseQuery.getQuery("monster");
		final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		final ParseQuery<ParseObject> query = ParseQuery.getQuery("monster");
		try {
			results = query.find();
			for(ParseObject monsters: results){
				Log.d("Monster", monsters.getString("name"));
				list.add(monsters.getString("name"));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		
		
		Button signupButton = (Button) rootView.findViewById(R.id.button1);
		final EditText name = (EditText) rootView.findViewById(R.id.editText1);
		final EditText email = (EditText) rootView.findViewById(R.id.editText2);
		final EditText pw1 = (EditText) rootView.findViewById(R.id.editText3);
		final EditText pw2 = (EditText) rootView.findViewById(R.id.editText4);
		signupButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name.setError(null);
				email.setError(null);
				pw1.setError(null);
				pw2.setError(null);
				boolean cancel = false;
				View focusView = null;

				if (TextUtils.isEmpty(name.getText().toString())) {
					name.setError(getString(R.string.error_field_required));
					focusView = name;
					cancel = true;
				}

				if (TextUtils.isEmpty(email.getText().toString())) {
					email.setError(getString(R.string.error_field_required));
					focusView = email;
					cancel = true;
				}

				if (TextUtils.isEmpty(pw1.getText().toString())) {
					pw1.setError(getString(R.string.error_field_required));
					focusView = pw1;
					cancel = true;
				}
				if (TextUtils.isEmpty(pw2.getText().toString())) {
					pw2.setError(getString(R.string.error_field_required));
					focusView = pw2;
					cancel = true;
				}
				
				else if(!pw2.getText().toString().equals(pw1.getText().toString())){
					pw2.setError(getString(R.string.error_different_passwords));
					focusView = pw2;
					cancel = true;
				}
				
				if (cancel) {
					// There was an error; don't attempt login and focus the
					// first
					// form field with an error.
					focusView.requestFocus();
				} else {
					ParseUser user = new ParseUser();
					user.setUsername(name.getText().toString());
					user.setPassword(pw1.getText().toString());
					user.setEmail(email.getText().toString());
					
					
					 
					 
					user.signUpInBackground(new SignUpCallback() {
					  public void done(ParseException e) {
					    if (e == null) {
					      Log.d("TAG", "Success");
					      
					      
					      final ParseObject relation = new ParseObject("relation");
							relation.put("TID", ParseUser.getCurrentUser().getObjectId().toString() );
							query1.whereEqualTo("name", spinner.getSelectedItem().toString());
							query1.findInBackground(new FindCallback<ParseObject>() {
							    public void done(List<ParseObject> scoreList, ParseException e) {
							        if (e == null) {
							        	relation.put("MID", scoreList.get(0).getObjectId());
							        	query2.whereEqualTo("name", "Tackle");
										query2.findInBackground(new FindCallback<ParseObject>() {
										    public void done(List<ParseObject> scoreList, ParseException e) {
										        if (e == null) {
										        	relation.put("Att1", scoreList.get(0).getObjectId());
										        	relation.saveInBackground(new SaveCallback(){

														@Override
														public void done(ParseException e) {
															// TODO Auto-generated method stub
															if (e == null) {
															      Log.d("TAG", "Success");
															    } else {
															      // Sign up didn't succeed. Look at the ParseException
															      // to figure out what went wrong
															    	Log.d("TAG", e.toString());
															    }
														}
														
													});
										        } else {
										            Log.d("score", "Error: " + e.getMessage());
										        }
										    }
										});
										
							        } else {
							            Log.d("score", "Error: " + e.getMessage());
							        }
							    }
							});
							
					      
					      
					      
					    } else {
					      // Sign up didn't succeed. Look at the ParseException
					      // to figure out what went wrong
					    	Log.d("TAG", e.toString());
					    }
					  }
					});
					
					
				}
				
			}
			
		});
		
		return rootView;
	}

}
