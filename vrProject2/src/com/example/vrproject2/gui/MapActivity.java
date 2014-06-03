package com.example.vrproject2.gui;

import java.util.List;

import mobisocial.nfc.addon.BluetoothConnector;

import com.example.vrproject2.R;
import com.example.vrproject2.R.drawable;
import com.example.vrproject2.R.id;
import com.example.vrproject2.R.layout;
import com.example.vrproject2.R.menu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

public class MapActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
		
		mMap= mapFrag.getMap();
		
		
		final ParseUser pu = ParseUser.getCurrentUser();
		ParseGeoPoint pgp=(ParseGeoPoint) pu.get("location");
		LatLng position= new LatLng(pgp.getLatitude(), pgp.getLongitude());
		mMap.addMarker(new MarkerOptions().position(position).title(pu.getUsername()).snippet("Hier bin ich!")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		ParseGeoPoint southwestOfSF = new ParseGeoPoint(pgp.getLatitude()-1, pgp.getLongitude()-1);
		ParseGeoPoint northeastOfSF = new ParseGeoPoint(pgp.getLatitude()+1, pgp.getLongitude()+1);
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereWithinGeoBox("location", southwestOfSF, northeastOfSF);
		query.findInBackground(new FindCallback<ParseUser>(){

			@Override
			public void done(List<ParseUser> pus, ParseException e) {
				// TODO Auto-generated method stub
				if(e==null){
					Log.d("TAG","Success1");
					for(ParseUser pu1: pus){
						if(!pu.getUsername().equals(pu1.getUsername())){
							Log.d("TAG",pu1.getUsername());
							ParseGeoPoint pgp=(ParseGeoPoint) pu1.get("location");
							LatLng position= new LatLng(pgp.getLatitude(), pgp.getLongitude());
							mMap.addMarker(new MarkerOptions().position(position).title(pu.getUsername()).snippet("Hier bin ich!")
							.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
						}
					}
				}
				else{
					Log.d("TAG", e.toString());
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

}