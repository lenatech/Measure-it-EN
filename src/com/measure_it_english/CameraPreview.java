package com.measure_it_english;


import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.http.util.LangUtils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class CameraPreview extends SurfaceView 
implements SurfaceHolder.Callback {

static Camera mCamera;

private SurfaceHolder mSurfHolder;
private Activity mActivity;
int a =Main.screenHeight;
int b=Main.screenWidth;

Size optimalSize;
static Bitmap bm;
private Handler autoFocusHandler;
static boolean previewing = true;
private int takePictureType=0;

public CameraPreview(Context context) {
	  super(context);
	// TODO Auto-generated constructor stub

    mSurfHolder = getHolder();
    mSurfHolder.addCallback(this);
    mSurfHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    
    autoFocusHandler = new Handler();
 
	
	
	  
}
   public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

public void set(Activity activity, Camera camera) {
	mActivity = activity;
	mCamera = camera;
	
}
@Override
public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    if (changed) {
    	try {		
   	     Camera.Parameters parameters = mCamera.getParameters();
   	     Camera.Size prevSize;
   	     int width=Main.screenWidth;
   	     int height = Main.screenHeight;
   			List<Size> sizes = parameters.getSupportedPreviewSizes();
   			optimalSize = getOptimalPreviewSize(sizes, width, height);
   			Log.v("TAG", "Optimal height: " +  height);
   		   	 Log.v("TAG", "Optimal width: " +  width);
   		   	Log.v("TAG", "optimalSize.height: " +  optimalSize.height);
   		   	 Log.v("TAG", "optimalSize.width: " +  optimalSize.width);
   		   	    prevSize = parameters.getPreviewSize();
   		   	 parameters.setPictureSize(optimalSize.width, optimalSize.height);
   		   	Log.v("TAG", "Preview.height: " +  prevSize.height);
   		   	 Log.v("TAG", "Preview.width: " +  prevSize.width);
   		   	 
   		   	 
   		   	 if(optimalSize.width==optimalSize.height)  //for htc one x
   		   	 {
   	   		parameters.setPictureSize(prevSize.width, prevSize.height);
   		   	 }
   		   	 else if(optimalSize.width>height) //for htc desire
   		   	 {
   		   		parameters.setPictureSize(prevSize.width, prevSize.height);
   		   	 }
   		   	 else if(prevSize.height==480 && height==1280)
   		   	 {
   		   	//	parameters.setPictureSize(960, 1280);
   		   	//	parameters.setPictureSize(optimalSize.width, optimalSize.height);
   		   	 }
   			else  // for s4 sony
   				{
   				parameters.setPictureSize(optimalSize.width, optimalSize.height);
   				Log.v("TAG", "Optimal height: " + optimalSize.height);
   			   	 Log.v("TAG", "Optimal width: " + optimalSize.width);
   				
   				}
   		   	
   			mCamera.setParameters(parameters);
   			mCamera.startPreview();
   	}catch (Exception e) {
   		// TODO Auto-generated catch block
   	//	Toast.makeText(getContext(), "設置錯誤！", Toast.LENGTH_LONG)
   	//		.show();
   		e.printStackTrace(System.out);
   	
   	}
    	try{
    	Camera.Parameters parameters = mCamera.getParameters();
    	Camera.Size prevSize;
    	prevSize = parameters.getPreviewSize();           //4*3  16*9
    	 int screenHeight = Main.screenHeight;
    	int screenWidth = Main.screenWidth;
    	
    	 double picheight=parameters.getPictureSize().height,previewheight=screenHeight,picwidth=parameters.getPictureSize().width,previewwidth=screenWidth;
		 double a2 = prevSize.height;
		 double b= prevSize.width;
		 
    	 Log.v("TAG", " picheight: " +  picheight);
		 Log.v("TAG", " picwidth: " +  picwidth);
		 Log.v("TAG", " previewheight: " + previewheight);
		 Log.v("TAG", " previewwidth: " +  previewwidth);
		 Log.v("TAG", " a2: " + a2);
		 Log.v("TAG", " b: " +  b);
		 
    	 double h= picheight/picwidth;
    	 double h2=a2/b;
    	 
    	
    			 if(h==0.75)
    			 {
    				 double w1 =  previewwidth/picheight;
    				 int h1 = (int) (picwidth*w1);
    				 int x1 =(int) (previewheight/2-h1/2);
    				 int x2 = h1+x1;
    				 
    				 if(h2!=0.75)
    				 {
    					
    					 (CameraPreview.this).layout(0, 0, (int) previewwidth,  (int) previewheight);
    					// (CameraPreview.this).layout(0, 0, 800, 1280);
    					
    				 }
    				 else
    				 {
    				
    			//	 (CameraPreview.this).getLayoutParams().width=(int) previewwidth;
    			//     (CameraPreview.this).getLayoutParams().height=h1 ;
    				 (CameraPreview.this).layout(0, x1, (int) previewwidth, x2);
    				 }
    				 Log.v("TAG", " setpreviewheight: " +  (CameraPreview.this).getLayoutParams().height);
        			 Log.v("TAG", " setpreviewwidth: " +  (CameraPreview.this).getLayoutParams().width);
    				
    			 }
    			 else if(h==0.5625)
    			 {
    		//		 (CameraPreview.this).getLayoutParams().width=screenWidth;
    			//     (CameraPreview.this).getLayoutParams().height=screenHeight;
    			    	
    			 }
    			 
    			

    	}catch (Exception e) {
    		// TODO Auto-generated catch block
    		//Toast.makeText(getContext(), "設置錯誤！", Toast.LENGTH_LONG)
    		//	.show();
    		
    		e.printStackTrace(System.out);
    	}
    }
}  
@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	// Now that the size is known, set up the camera parameters and begin
			// the preview.
	
	
}
private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
	final double ASPECT_TOLERANCE = 0.05;
	double targetRatio = (double) w / h;
	if (sizes == null)
		return null;

	Size optimalSize = null;
	double minDiff = Double.MAX_VALUE;

	int targetHeight = h;

	// Try to find an size match aspect ratio and size
	for (Size size : sizes) {
		double ratio = (double) size.width / size.height;
		if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
			continue;
		if (Math.abs(size.height - targetHeight) < minDiff) {
			optimalSize = size;
			minDiff = Math.abs(size.height - targetHeight);
		}
	}

	// Cannot find the one match the aspect ratio, ignore the requirement
	if (optimalSize == null) {
		minDiff = Double.MAX_VALUE;
		for (Size size : sizes) {
			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}
	}
	return optimalSize;
}
@Override
public void surfaceCreated(SurfaceHolder holder) {
	// TODO Auto-generated method stub

	try {
		
		
		mCamera.setPreviewDisplay(mSurfHolder);
		
		
		Camera.CameraInfo camInfo = new Camera.CameraInfo();
		Camera.getCameraInfo(0, camInfo);

		int rotation = mActivity.getWindowManager().getDefaultDisplay().getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0; break;
		case Surface.ROTATION_90:
			degrees = 90; break;
		case Surface.ROTATION_180:
			degrees = 180; break;
		case Surface.ROTATION_270:
			degrees = 270; break;
		}

		int result;
		result = (camInfo.orientation - degrees + 360) % 360;
		mCamera.setDisplayOrientation(result);
		
		mCamera.startPreview();
		previewing = true;    
		Camera.Parameters camParas = mCamera.getParameters();
	
		
		
		if (camParas.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_AUTO) || 
			camParas.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_MACRO))
			mCamera.autoFocus(onCamAutoFocus);
		else
			Toast.makeText(getContext(), "Camera does not support auto-focus！", Toast.LENGTH_SHORT)
				.show();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		Toast.makeText(getContext(), "Error with autofocus！", Toast.LENGTH_LONG)
			.show();
		
		e.printStackTrace(System.out);
	}
}

@Override
public void surfaceDestroyed(SurfaceHolder holder) {
	// TODO Auto-generated method stub
	try {
	if (mCamera != null) {
		mCamera.cancelAutoFocus();
          
		mCamera.stopPreview();
    }
    previewing = false;
    } catch (RuntimeException e) {
        
        e.printStackTrace();
    } finally {
        // Make sure that at least these two calls are made
    	mCamera.release();
    	mCamera = null;
}
}
Camera.AutoFocusCallback onCamAutoFocus = new Camera.AutoFocusCallback() {

	@Override
	public void onAutoFocus(boolean success, Camera camera) {
		// TODO Auto-generated method stub
		//顯示拍照說明
		if (success && takePictureType < 6) {  
            takePictureType +=1;  
        } else if (takePictureType > 5) {  
            //myCamera.takePicture(null, null, pictureCallback);  
            previewing = false;  
            takePictureType = 0;  
        } else {  
            takePictureType = 0;  
        }  
        
		try {
		autoFocusHandler.postDelayed(doAutoFocus, 3000);
       
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getContext(), "Error with autofocus！", Toast.LENGTH_LONG);
			}
		
		
	}
		
	
};  

private Runnable doAutoFocus = new Runnable() {  
	    @Override  
	    public void run() {  
	    	 
	    	
	    	try {
	    		if(previewing){  
		            mCamera.autoFocus(onCamAutoFocus);  
		        }  
	           
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			Toast.makeText(getContext(), "Error with autofocus！", Toast.LENGTH_LONG);
	    			}
	    	
	    }  
	}; 
    public static Bitmap getBitmap(){
    	
    	return bm;
		 }

}  //end class

