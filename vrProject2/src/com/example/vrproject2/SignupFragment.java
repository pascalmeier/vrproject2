package com.example.vrproject2;


import com.parse.ParseException;
import com.parse.ParseUser;
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
import android.widget.Button;
import android.widget.EditText;
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
