package com.measure_it_english;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
	 
	public class SplashScreen extends Activity {
	 
	    // Splash screen timer
	    private static int SPLASH_TIME_OUT = 2200;
	    static TextView t1;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setContentView(R.layout.activity_splash);
	        t1 = (TextView) findViewById(R.id.textView1);
			t1.setTextSize(convertPixelsToDp(convertDpToPixel(18,SplashScreen.this),SplashScreen.this));
	        new Handler().postDelayed(new Runnable() {
	        	
	            /*
	             * Showing splash screen with a timer. This will be useful when you
	             * want to show case your app logo / company
	             */
	 
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start your app main activity
	                Intent i = new Intent(SplashScreen.this, SlideMain.class);
	                startActivity(i);
	 
	                // close this activity
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
	    }
	    public static float convertDpToPixel(float dp,Context context){

			  DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			  float px = dp * (metrics.densityDpi/160f);
			  return px;

			}
		 public static float convertPixelsToDp(float px,Context context){

			  DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			  float dp = px / (metrics.densityDpi / 160f);
			  return dp;

			}
	}

