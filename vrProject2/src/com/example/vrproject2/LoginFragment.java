package com.example.vrproject2;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;



import android.os.Bundle;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

	public LoginFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		final View rootView = inflater.inflate(R.layout.fragment_login,
				container, false);
		
		final EditText name = (EditText) rootView.findViewById(R.id.editText1);
		final EditText pw = (EditText) rootView.findViewById(R.id.editText2);
		Button login = (Button) rootView.findViewById(R.id.button1);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParseUser.logInInBackground(name.getText().toString(), pw.getText().toString(), new LogInCallback() {
			@Override  
			public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      Intent i= new Intent();
			      i.setClass(getActivity().getApplicationContext(), MenuActivity.class);
			      startActivity(i);
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    }
			  }
			});
			}
		});
		

		
		return rootView;
	}
}
