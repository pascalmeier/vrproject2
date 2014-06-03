package com.example.vrproject2.gui;

import com.example.vrproject2.R;
import com.example.vrproject2.R.layout;
import com.example.vrproject2.R.menu;
import com.example.vrproject2.util.CameraPreview;
import com.parse.ParseUser;

import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private Camera mCamera;
    private CameraPreview mPreview;
	
	@SuppressLint("ResourceAsColor")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		ParseUser pu = ParseUser.getCurrentUser();
		
		final FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		TextView tv = new TextView(getApplicationContext());//(TextView) findViewById(R.id.textView1);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rlProfile);
		Log.d("TAG",""+getWindowManager().getDefaultDisplay().getWidth());
		LayoutParams lp = preview.getLayoutParams(); 
        lp.height = getWindowManager().getDefaultDisplay().getWidth()-100;
        preview.setLayoutParams(lp);
		//tv.setHeight(tv.getWidth());
		//tv.setText("Klicken Sie um ein Foto zu schieﬂen");
		//tv.setWidth(rl.getWidth());
		//tv.setHeight(rl.getWidth());
        //preview.layout(0, 0,tv.getWidth(), tv.getWidth());
        preview.setBackgroundColor(R.color.gray);
		
		if(pu.get("foto")!=null){
			ImageView iv = new ImageView(getApplicationContext());
			iv.setImageDrawable((Drawable) pu.get("foto"));
			preview.addView(iv);
		}
		
		preview.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Create an instance of Camera
		        mCamera = getCameraInstance();
		        
		        
		        // Create our Preview view and set it as the content of our activity.
		        mPreview = new CameraPreview(getApplicationContext(), mCamera);
		        
		        
		        //LayoutParams params = new LayoutParams(preview.getWidth(), preview.getWidth());
		        //mPreview.setLayoutParams(params);
		        preview.addView(mPreview);
			}
		});
		
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(1); // attempt to get a Camera instance
	        c.setDisplayOrientation(90);
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
