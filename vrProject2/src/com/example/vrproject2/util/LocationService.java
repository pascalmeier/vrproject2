package com.example.vrproject2.util;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {

	/*public LocationService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public LocationService() {
		super("LocationService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	    // Normally we would do some work here, like download a file.
	    // For our sample, we just sleep for 5 seconds.
		Timer timer = new Timer();
		timer.schedule(new SendLocation(), 5000);
	}
	
	class SendLocation extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);  
			LocationListener mlocListener = new MyLocationListener(); 
			mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener); 
			

		}
		
	}
	class MyLocationListener implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
			ParseUser pu = ParseUser.getCurrentUser();
			pu.put("location", point);
			pu.saveInBackground(new SaveCallback(){
				@Override
				public void done(ParseException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						Log.d("TAG", "Success");
						Toast.makeText( getApplicationContext(),"Location stored", Toast.LENGTH_SHORT ).show(); 

						} else {
						// Sign up didn't succeed. Look at the ParseException
						// to figure out what went wrong
						Log.d("TAG", e.toString());
						}
				}
			});
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			// TODO Auto-generated method stub
			
		} 

	}/* End of Class MyLocationListener */
	/*
	private static final String TAG = "BOOMBOOMTESTGPS";
	private LocationManager mLocationManager = null;
	private static final int LOCATION_INTERVAL = 1000;
	private static final float LOCATION_DISTANCE = 10f;
	Location mLastLocation;
    boolean running =true;
	private class LocationListener implements android.location.LocationListener{
	    public LocationListener(String provider)
	    {
	        Log.e(TAG, "LocationListener " + provider);
	        mLastLocation = new Location(provider);
	    }
	    @Override
	    public void onLocationChanged(Location location)
	    {
	        Log.e(TAG, "onLocationChanged: " + location);
	        mLastLocation.set(location);
	        
	    }
	    @Override
	    public void onProviderDisabled(String provider)
	    {
	        Log.e(TAG, "onProviderDisabled: " + provider);            
	    }
	    @Override
	    public void onProviderEnabled(String provider)
	    {
	        Log.e(TAG, "onProviderEnabled: " + provider);
	    }
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras)
	    {
	        Log.e(TAG, "onStatusChanged: " + provider);
	    }
	} 
	LocationListener[] mLocationListeners = new LocationListener[] {
	        new LocationListener(LocationManager.GPS_PROVIDER),
	        new LocationListener(LocationManager.NETWORK_PROVIDER)
	};
	@Override
	public IBinder onBind(Intent arg0)
	{
	    return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
	    Log.e(TAG, "onStartCommand");
	    super.onStartCommand(intent, flags, startId);       
	    return START_STICKY;
	}
	@Override
	public void onCreate()
	{
	    Log.e(TAG, "onCreate");
	    initializeLocationManager();
	    try {
	        mLocationManager.requestLocationUpdates(
	                LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	                mLocationListeners[1]);
	    } catch (java.lang.SecurityException ex) {
	        Log.i(TAG, "fail to request location update, ignore", ex);
	    } catch (IllegalArgumentException ex) {
	        Log.d(TAG, "network provider does not exist, " + ex.getMessage());
	    }
	    try {
	        mLocationManager.requestLocationUpdates(
	                LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
	                mLocationListeners[0]);
	    } catch (java.lang.SecurityException ex) {
	        Log.i(TAG, "fail to request location update, ignore", ex);
	    } catch (IllegalArgumentException ex) {
	        Log.d(TAG, "gps provider does not exist " + ex.getMessage());
	    }
		while(running){
	    Timer timer = new Timer();
		timer.schedule(new SendLocation(), 5000);
	    }
	}
	@Override
	public void onDestroy()
	{
	    Log.e(TAG, "onDestroy");
	    super.onDestroy();
	    if (mLocationManager != null) {
	        for (int i = 0; i < mLocationListeners.length; i++) {
	            try {
	                mLocationManager.removeUpdates(mLocationListeners[i]);
	            } catch (Exception ex) {
	                Log.i(TAG, "fail to remove location listners, ignore", ex);
	            }
	        }
	    }
	} 
	private void initializeLocationManager() {
	    Log.e(TAG, "initializeLocationManager");
	    if (mLocationManager == null) {
	        mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
	    }
	}
	class SendLocation extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParseGeoPoint point = new ParseGeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude());
			ParseUser pu = ParseUser.getCurrentUser();
			pu.put("location", point);
			pu.saveInBackground(new SaveCallback(){
				@Override
				public void done(ParseException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						Log.d("TAG", "Success");
						Toast.makeText( getApplicationContext(),"Location stored", Toast.LENGTH_SHORT ).show(); 

						} else {
						// Sign up didn't succeed. Look at the ParseException
						// to figure out what went wrong
						Log.d("TAG", e.toString());
						}
				}
			});

		}
		
	}*/
	private static final String TAG = LocationService.class
            .getSimpleName();

    LocationManager locationManager;
    LocationListener gpsLocationListener;
    Location mLastLocation;
	boolean running=true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsLocationListener = new GPSLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10 * 1000L, 0, gpsLocationListener);
        new Thread(new Runnable() {
        	public void run() {
				while(running){
					
					try {
						synchronized (this) {
							wait(5000);
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(mLastLocation!= null){
						ParseGeoPoint point = new ParseGeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude());
						ParseUser pu = ParseUser.getCurrentUser();
						pu.put("location", point);
						pu.saveInBackground(new SaveCallback(){
							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								if (e == null) {
									Log.d("TAG", "Success");
									Toast.makeText( getApplicationContext(),"Location stored", Toast.LENGTH_SHORT ).show(); 
			
									} else {
									// Sign up didn't succeed. Look at the ParseException
									// to figure out what went wrong
									Log.d("TAG", e.toString());
									}
							}
						});
					}
        		}
        	}
        	}).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroying");
        locationManager.removeUpdates(gpsLocationListener);
        running=false;
    }
    
    private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: " + location.toString());
            mLastLocation =location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "onStatusChanged: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

    }

}
