package com.measure_it_english;

import java.io.ByteArrayOutputStream;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.graphics.Paint.Style;
import android.hardware.Camera;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class Main extends Activity {

	Uri imageUri; 
	private Camera mCamera;
	private CameraPreview mCamPreview;
	private boolean mCapturing = true;
	static DisplayMetrics mPhone;
	static Bitmap mScaleBitmap;
	static int screenHeight;
	static int screenWidth;
	static int x;
	int k = 45; 
	SensorManager mySensorManager;
	DrawOnTop mDraw;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setFormat(PixelFormat.TRANSLUCENT); 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(1);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth =  dm.widthPixels;
        screenHeight = dm.heightPixels;
        
        mCamPreview = new CameraPreview(this);
        mDraw = new DrawOnTop(this);
        setContentView(mCamPreview);
        addContentView(mDraw, new LayoutParams 
        		(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mPhone = new DisplayMetrics();
	      getWindowManager().getDefaultDisplay().getMetrics(mPhone);
    }
    

    class DrawOnTop extends View {

    	Paint paint = new Paint();   
    	//Bitmap shangBitmap1;
 	    Bitmap shangBitmap2;
 	   // Bitmap zuoBitmap1;
 	    Bitmap zuoBitmap2;
 	    Bitmap say1Bitmap;
 	    Bitmap level;
 	    int shang1_X = 10 ;
 	    int shang1_Y = 80 ;
 	    int zuo1_X = 80; 
 		int zuo1_Y = 10;
 	    int shang2_X;
 	    int shang2_Y;
 	    int zuo2_X; 
 		int zuo2_Y;
 	    int say1_X ;
 	    int say1_Y ; 
    		
    		
        public DrawOnTop(Context context) { 
                super(context); 
                initBitmap(); 
        		initLocation(); 
               
                // TODO Auto-generated constructor stub 
    	

    	}
      private void initBitmap(){ 
    	   
    		shangBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.level3_1);
    		zuoBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.level3_2);
    		level = BitmapFactory.decodeResource(getResources(), R.drawable.level);
    		say1Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.say01en);
    		x = say1Bitmap.getHeight()/2;
    	}

    	private void initLocation(){ 

    		shang2_X = shang1_X + level.getWidth()/2- shangBitmap2.getWidth()/2;
    		shang2_Y = zuo1_Y + level.getHeight()/2- shangBitmap2.getHeight()/2;
    		zuo2_X = shang1_X + level.getWidth()/2- zuoBitmap2.getWidth()/2;
    		zuo2_Y = zuo1_Y + level.getHeight()/2- zuoBitmap2.getHeight()/2;

    	}
        @Override 
        protected void onDraw(Canvas canvas) { 
                      // TODO Auto-generated method stub
         super.onDraw(canvas); 
            paint.setStyle(Style.STROKE); 
            
            say1_X = (screenWidth - say1Bitmap.getWidth())/2;
            say1_Y = screenHeight- say1Bitmap.getHeight()-10;
            canvas.drawBitmap(level,shang1_X,zuo1_Y,paint);
            canvas.drawBitmap(shangBitmap2, shang2_X,shang2_Y, paint);
            canvas.drawBitmap(zuoBitmap2, zuo2_X,zuo2_Y, paint); 
            canvas.drawBitmap(say1Bitmap, say1_X, say1_Y, paint);
         
    		paint.setColor(Color.GRAY);
    		
    		
               
        }
 
}
    
   
    private final SensorListener mSensorLisener =new SensorListener(){

		@Override
		public void onAccuracyChanged(int sensor, int accuracy) { }
		
		@Override
		public void onSensorChanged(int sensor, float[] values) {
			if(sensor == SensorManager.SENSOR_ORIENTATION){
				double pitch = values[SensorManager.DATA_Y];
				double roll = values[SensorManager.DATA_Z];
				int x=0; int y=0;

				if(Math.abs(roll)<=k){
					mDraw.shang2_X = mDraw.shang1_X 
							+ (int)(((mDraw.level.getWidth()
							-mDraw.shangBitmap2.getWidth())/2.0)
							-(((mDraw.level.getWidth()
							-mDraw.shangBitmap2.getWidth())/2.0)*roll)/k);
	
				}else if(roll>k){
					mDraw.shang2_X=mDraw.shang1_X; 
				}else{
					mDraw.shang2_X=mDraw.shang1_X+
							mDraw.level.getWidth()
							- mDraw.shangBitmap2.getWidth();
				}
				
				if(Math.abs(pitch)<=k){
					mDraw.zuo2_Y=mDraw.zuo1_Y 
							+ (int)(((mDraw.level.getHeight()
							-mDraw.zuoBitmap2.getHeight())/2.0)
							+(((mDraw.level.getHeight()
							-mDraw.zuoBitmap2.getHeight())/2.0)*pitch)/k);
	
				
				}else if(pitch>k){
					mDraw.zuo2_Y=mDraw.zuo1_Y
							+mDraw.level.getHeight()
							-mDraw.zuoBitmap2.getHeight();  
					
					
				}else{
					mDraw.zuo2_Y = mDraw.zuo1_Y; 
				}

				mDraw.postInvalidate();
			}
		} 
		
	};
    
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		mCamera = Camera.open();
		mCamPreview.set(this, mCamera);
		 mySensorManager.registerListener(mSensorLisener,SensorManager.SENSOR_ORIENTATION);
		super.onResume();
	}
/*
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		mCamera.stopPreview();
	    mCamPreview.previewing = false;
	    mCamera.release();
	    mCamera = null;
	    mySensorManager.unregisterListener (mSensorLisener);
	   finish();

	    super.onPause();
	}
*/
	
	@Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mCapturing) {
            processEvent(event);
            
            return true;
        } else {
            return false;
        }
    }
	
	private boolean processEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_UP) {
        				Toast.makeText(Main.this, "Taken...Please wait!", Toast.LENGTH_SHORT).show();
			mCamera.takePicture(camShutterCallback, camRawDataCallback, jpegPictureCallback);
        	
        }

        return false;
	}


	ShutterCallback camShutterCallback = new ShutterCallback() {
		public void onShutter() {
			
		}
	};

	PictureCallback camRawDataCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
	
						
			
		}
	};
	
	private PictureCallback jpegPictureCallback = new PictureCallback()
    {
		@Override
		public void onPictureTaken(byte[] data, Camera arg1) 
		{
			
			int width = arg1.getParameters().getPictureSize().width;  
            int height = arg1.getParameters().getPictureSize().height; 
   	 Log.v("TAG", "getPictureSize height: " +  height);
   	 Log.v("TAG", "getPictureSize width: " +  width);
   	
			
			final int DESIRED_WIDTH = 1440;
			
			
			final BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
			sizeOptions.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(data, 0, data.length,sizeOptions);
			Log.d("", "Bitmap is " + sizeOptions.outWidth + "x"
			            + sizeOptions.outHeight);
			

			// Now use the size to determine the ratio you want to shrink it
			final float widthSampling = sizeOptions.outWidth /DESIRED_WIDTH;
			sizeOptions.inJustDecodeBounds = false;
			// Note this drops the fractional portion, making it smaller
			sizeOptions.inSampleSize = (int) widthSampling;
			Log.d("", "Sample size = " + sizeOptions.inSampleSize);
			Bitmap result = BitmapFactory.decodeByteArray(data, 0, data.length,
			        sizeOptions);
			
			if(sizeOptions.outWidth<800)
			{
			//	 Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				
				
			 result = BitmapFactory.decodeByteArray(data, 0, data.length);
			}
			
			
   	try {
   		
			    
		float mScale = (float)mPhone.widthPixels/(float)result.getWidth();
						        
		Matrix mMat = new Matrix() ;
		
		if(width==4000)
		{
			mMat.setScale(mScale, mScale);  
		}
			   
				mMat.postRotate(90);
						         
				mScaleBitmap = Bitmap.createBitmap(result, 0,0,result.getWidth(),result.getHeight(),mMat,false);   
				Log.v("TAG", "pic height: " + mScaleBitmap.getHeight());
				Log.v("TAG", "pic width: " + mScaleBitmap.getWidth());
			   Intent intent = new Intent();
		      intent.setClass(Main.this, Scam.class);
		       startActivity(intent);    
   	} catch (Exception err) {
   		err.printStackTrace(System.out);
   /*		float mScale = (float)mPhone.widthPixels/(float)result.getWidth();
        
		Matrix mMat = new Matrix() ;
			mMat.setScale(mScale, mScale);     
				mMat.postRotate(90);
						         
				mScaleBitmap = Bitmap.createBitmap(result, 0,0,result.getWidth(),result.getHeight(),mMat,false);   
				Log.v("TAG", "pic height: " + mScaleBitmap.getHeight());
				Log.v("TAG", "pic width: " + mScaleBitmap.getWidth());
			   Intent intent = new Intent();
		      intent.setClass(Main.this, Scam.class);
		       startActivity(intent);    */
   	}
   	      

			Intent intent = new Intent();
		      intent.setClass(Main.this, Scam.class);
		       startActivity(intent);   	
		}
    };

    public void surfaceDestroyed(SurfaceHolder holder) {
    	// TODO Auto-generated method stub
    	try {
    	if (mCamera != null) {
    		mCamera.cancelAutoFocus();
              
    		mCamera.stopPreview();
        }
        
        } catch (RuntimeException e) {
            
            e.printStackTrace();
        } finally {
            // Make sure that at least these two calls are made
        	mCamera.release();
        	mCamera = null;
    }
    }
    public static Bitmap getBitmap(){
		 return mScaleBitmap;
		 }
  
}
