package com.measure_it_english;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.measure_it_english.Library.ButtonHighlighterOnTouchListener4;
import com.measure_it_english.Library.ButtonHighlighterOnTouchListener5;
import com.measure_it_english.Library.ButtonHighlighterOnTouchListener6;
import com.measure_it_english.Library.ButtonHighlighterOnTouchListener7;



import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.SoundPool;

public class Scam extends Activity {

    private ImageButton Btn; 
    ImageButton Btn2;
    static ImageView imageView,imageView2; 
    private DisplayMetrics mPhone;
    static  int come=0,chge=0,done=0 ;
    static TextView t1;
    TouchImageView img ;
    static String cm="CM" ;  //物品實際長
    Double n=TouchImageView.v;
    Uri myUri,imgUri;
    private SoundPool sp;//聲明一個SoundPool
    private int music;//定義一個整型用load（）；來設置suondID
    int myProgress = 0; 
    ProgressDialog progDlg;
    int statusBarHeight, width,height;
    Bitmap a,d;
    ImageButton ok,cancel,change;
    static int screenHeight;
	static int screenWidth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   
	    
		setContentView(R.layout.cam);
		this.setRequestedOrientation(1);
		ImageButton talk = (ImageButton)findViewById(R.id.imageB1);  //reset
		 Btn = (ImageButton) findViewById(R.id.imageButton3);
		 Btn2 = (ImageButton) findViewById(R.id.imageButton2);
		 change = (ImageButton) findViewById(R.id.imageButton1);
		 imageView = (ImageView)findViewById(R.id.img);
		 img = new TouchImageView(this);
		 final FrameLayout background = (FrameLayout)findViewById(R.id.FrameLayout1);
		 t1 = (TextView) findViewById(R.id.textView1);
		 t1.setTextSize(convertPixelsToDp(convertDpToPixel(40,Scam.this),Scam.this));
		 t1.setBackgroundColor(Color.BLACK);
		 t1.setTextColor(Color.CYAN);
		 progDlg = new ProgressDialog(Scam.this);
		 progDlg.setTitle("Please wait... ");
		 progDlg.setMessage("Capturing...");
		 progDlg.setIcon(R.drawable.ic);
		 progDlg.setCancelable(false);
		 progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		 progDlg.setMax(100);
		 change.setImageResource(R.drawable.check61m);
		 //音效
		 DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        screenWidth =  dm.widthPixels;
	        screenHeight = dm.heightPixels;
		  init();
		 mPhone = new DisplayMetrics();
	      getWindowManager().getDefaultDisplay().getMetrics(mPhone);
		 BitmapDrawable back = new BitmapDrawable(Main.getBitmap());
		 Bitmap b = back.getBitmap(); 
		 double picheight=b.getHeight(),previewheight=screenHeight,picwidth=b.getWidth(),previewwidth=screenWidth;
		 Log.v("TAG", " picheight: " +  picheight);
		 Log.v("TAG", " picwidth: " +  picwidth);
		 Log.v("TAG", " previewheight: " + previewheight);
		 Log.v("TAG", " previewwidth: " +  previewwidth);
		 
		// double h = Math.round((picwidth/picheight)*1000000.0)/1000000.0;
	     double h= picwidth/picheight;
	//	 double 
	//	 Log.v("TAG", " h: " +  h);
	//	 imageView.getLayoutParams().height = 1067;
		 if(h==0.75)
		 {
			 double w1 =  previewwidth/picwidth;
		//	 Log.v("TAG", " w1: " +  ""+w1);
			 int h1 = (int) (picheight*w1);
			 imageView.getLayoutParams().height = h1;
		 }
		 else if(h==0.5625)
		 {
		//	 double w1 =  previewwidth/picwidth;
		//	 Log.v("TAG", " w1: " +  ""+w1);
		//	 int h1 = (int) (picheight*w1);
			 imageView.getLayoutParams().height = (int) previewheight;
		 }
	/*	 if(b.getHeight()==720)   //sony
		 {
			 imageView.getLayoutParams().height = 960;
		 }
		 else
		 {
			 imageView.getLayoutParams().height = b.getHeight();
		 }
	*/	 
		// imageView.getLayoutParams().height = b.getWidth();
	//	 imageView.getLayoutParams().height = (screenWidth/b.getWidth())*b.getHeight();
		 Log.v("TAG", " x: " +  screenWidth);
		 Log.v("TAG", " x: " +  b.getHeight());
		 Log.v("TAG", " x: " +  b.getWidth());
		 Log.v("TAG", " height: " +  (imageView.getLayoutParams().height));

		 imageView.setBackgroundDrawable(back);
		// background.setBackgroundDrawable(back);
		 
	     t1.setText("Drag the lines.");
	     Btn.setOnTouchListener(new ButtonHighlighterOnTouchListener(Btn));
	     talk.setOnTouchListener(new ButtonHighlighterOnTouchListener2(talk));
	     Btn2.setOnTouchListener(new ButtonHighlighterOnTouchListener3(Btn2));
	     change.setOnTouchListener(new ButtonHighlighterOnTouchListener4(change));
	     
	        //得到狀態列高度   
	        Rect frame = new Rect();     
	        Scam.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);     
	        statusBarHeight = frame.top;      
	           
	        //得到螢幕長和高　
	        width = Scam.this.getWindowManager().getDefaultDisplay().getWidth();     
	        height = Scam.this.getWindowManager().getDefaultDisplay().getHeight(); 
	     
	     
		Btn.setOnClickListener(new OnClickListener(){

            public void onClick(View w){
            //	done=TouchImageView.done ;
            	if(done==0){
                	final AlertDialog isExit = new AlertDialog.Builder(Scam.this).create();
    			    Window window = isExit.getWindow();
                	WindowManager.LayoutParams lp = window.getAttributes();
                	lp.alpha=0.6f;
                	window.setAttributes(lp);
    			 
    			    isExit.setTitle("Result unsaved!");
    			
    			   
    			    LayoutInflater factory = getLayoutInflater();
                	   View loginDialogView = factory.inflate(R.layout.librarydialog, null);  
                	
                	   isExit.setView(loginDialogView); 
                	 
                	   isExit.setView(loginDialogView, 0, 0, 0, 0);
                	   isExit.show();
                
                	 
                	ok = (ImageButton)isExit.findViewById(R.id.buttonok);
                	cancel = (ImageButton)isExit.findViewById(R.id.buttoncancel);
                	ok.setOnClickListener(new OnClickListener(){
                		public void onClick(View w){

                        	Intent intent = new Intent();
                             intent.setClass(Scam.this, SlideMain.class);
                             Scam.this.finish();  
                             done=0;
                             startActivity(intent);
                             
                			
                		}
                	});
                	
                	cancel.setOnClickListener(new OnClickListener(){
                		 public void onClick(View w){
                			 isExit.cancel();
                		 }
                	});
           
                          }
                          else
                          {
                        	  Intent intent = new Intent();
                              intent.setClass(Scam.this, SlideMain.class);
                              Scam.this.finish();  
                              done=0;
                              startActivity(intent);
                             
                          }
                	
                }
            });
            
		talk.setOnClickListener(new OnClickListener(){

            public void onClick(View w){
          
            	Intent intent = new Intent();
                intent.setClass(Scam.this, Main.class);
                Scam.this.finish();  
                startActivity(intent);
	}
        });
		
		Btn2.setOnClickListener(new OnClickListener(){  //save

            public void onClick(View w){
            	
    			progDlg.show();
    			progDlg.setProgress(5);  
    			new Thread(new Runnable() {
    				 public void run() {
    				   
    					 while(myProgress<100){  
    					     try{  
    					      myHandle.sendMessage(myHandle.obtainMessage());  
    					      Thread.sleep(30);  
    					     }catch(Throwable t){  
    					     }  
    					    }  
    					 progDlg.cancel();
    					 Handle.sendMessage(Handle.obtainMessage()); 
    					 
    					   }  
    					}).start();   	
    			done=1;
            }
        });
		
		change.setOnClickListener(new OnClickListener(){

            public void onClick(View arg0){
            Double b=TouchImageView.newKB3*TouchImageView.cm;
            if(cm=="CM")
          	{
            	TouchImageView.n="M";
          		b=Math.round(b/100*10000.0)/10000.0;
          		come =1;
          		cm="M";
          		change.setImageResource(R.drawable.checkinch);
          		t1.setText(""+b+"  M");
          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener6(change));
          	}
          	else if(cm=="M")
          	{
          		TouchImageView.n="INCH";
          		b=Math.round(b*0.394*10000.0)/10000.0;
          		
          		cm="INCH";
          		come =2;
          		change.setImageResource(R.drawable.checkft);
          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener7(change));
          		t1.setText(""+b+"  INCH");
          	}
          	else if(cm=="INCH")
          	{
          		TouchImageView.n="FT";
          		b=Math.round(b/30.48*10000.0)/10000.0;
          		
          		cm="FT";
          		come =3;
          		change.setImageResource(R.drawable.check61cm);
          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener4(change));
          		t1.setText(""+b+"  FT");
          	}
          	else if(cm=="FT")
          	{
          		TouchImageView.n="CM";
          		b=Math.round(b*10000.0)/10000.0;
          		cm="CM";
          		come =0;
          		change.setImageResource(R.drawable.check61m);
          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener5(change));
          		t1.setText(""+b+"  CM");
          	}
            	t1.setVisibility(View.VISIBLE);
            	
            }
        });  
		
	}//onCreat End
	
	 private void init() {
	        // TODO Auto-generated method stub
	        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一個參數為同時撥放數據流的最大個數，第二數據流類型，第三為聲音質量
	        music = sp.load(this, R.raw.sound01, 1); //聲音素材放到res/raw，第二個參數極為資源文件，第三個為音樂的優先級
	         
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide_main, menu);
		return true;
	}


		    private  Bitmap takeScreenShot(Activity activity){   
		        //View是你需要截圖的View   
		    	
		        View view = activity.getWindow().getDecorView();  
		        
		        view.setDrawingCacheEnabled(true); 
		      
		        view.buildDrawingCache();
		        Bitmap b1 = view.getDrawingCache(); 
		        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight-Btn.getHeight());
		   
		        view.destroyDrawingCache();   
		        return b;   
		    }   
		    private Bitmap Scale(Bitmap b)
		    {
		    	
		    	float  mScale = (float)mPhone.widthPixels/(float)b.getWidth();
	         Matrix mMat = new Matrix() ;
	         mMat.setScale(mScale, mScale);
             Bitmap mScaleBitmap = Bitmap.createBitmap(b,
	                                                   0,
	                                                   0,
	                                                   b.getWidth(),
	                                                   b.getHeight(),
	                                                   mMat,
	                                                   false); 
             return  mScaleBitmap;
		    }
		    //儲存到sdcard   
           private void savePic(Bitmap b){   
		    	
		    	
		    	String dir = Environment.getExternalStoragePublicDirectory(  //取得系統的公用圖檔路徑
	                     Environment.DIRECTORY_DCIM).toString();
		        String str;
	            Date date=null;
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	            date =new Date();
	            str=format.format(date);
	            
	    	String fname = str + ".jpg";  //利用目前時間組合出一個不會重複的檔名
	    	String gat =dir + "/Measure_it/" + fname;
	    	ContentValues values = new ContentValues();
	        values.put(Images.Media.DATA, gat);
	        values.put(Images.Media.TITLE, fname);
	        values.put(Images.Media.DISPLAY_NAME, fname);
	        values.put(Images.Media.MIME_TYPE, "image/jpeg");
	        imgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	        Log.e("","pic file name : "+fname);
		        try {   
		        	
		        	OutputStream os = Scam.this.getContentResolver().openOutputStream(imgUri);
		                b.compress(Bitmap.CompressFormat.PNG, 90, os);   
		                os.flush();   
		                os.close();   
		            
		        } catch (FileNotFoundException e) {   
		            e.printStackTrace();   
		        } catch (IOException e) {   
		            e.printStackTrace();   
		        }   
		    }  
		    Handler myHandle = new Handler(){  
          	  
			  	  @Override  
	               	  public void handleMessage(Message msg) {  
		           	   // TODO Auto-generated method stub  
		           	   
		            	if(myProgress==0)
		            	{
		            		 sp.play(music, 1, 1, 0, 0, 1);
		            		 
		            	}
		            	else if(myProgress==15)
		            	{
		            		progDlg.setMessage("Capturing...");
		            		a = takeScreenShot(Scam.this);	    
		            		
		            	}
		            	else if (myProgress==25)
		            	{
		            		progDlg.setMessage("Compressing...");
		            		d = Scale(a) ;
		            		
		            		//savePic(a, ""+ path);
		            	}
		            	else if(myProgress==40)
		            	{
		            		progDlg.setMessage("Saving...please wait");
		            		
		            	}
		            	
		            	else if(myProgress==45)
		            	{
		            		savePic(d);
		            	}
		            	
		            	if(myProgress<100)
		            	{
		            	myProgress=myProgress+5;  
		            	}
		            	progDlg.setProgress(myProgress);  
              }  
			                	   
      };
      
      Handler Handle = new Handler(){  
    	  
		  	  @Override  
             	  public void handleMessage(Message msg) {  
	           	   // TODO Auto-generated method stub  
		  		Toast.makeText(Scam.this, "Picture have saved into the album", Toast.LENGTH_SHORT).show();
				 myProgress = 0;
          }  
		                	   
      	};



public class ButtonHighlighterOnTouchListener implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.homec);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.home);
	    }
	    return false;
	  }

	}
public class ButtonHighlighterOnTouchListener2 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener2(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.resetc);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.reset);
	    }
	    return false;
	  }

	}

public class ButtonHighlighterOnTouchListener3 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener3(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.storec);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.save);
	    }
	    return false;
	  }

	}

public class ButtonHighlighterOnTouchListener4 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener4(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.check1c);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.check61cm);
	    }
	    return false;
	  }

	}
public class ButtonHighlighterOnTouchListener5 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener5(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.check2c);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.check61m);
	    }
	    return false;
	  }

	}
public class ButtonHighlighterOnTouchListener6 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener6(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.checkinchc);
	    	
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.checkinch);
	    }
	    return false;
	  }

	}
public class ButtonHighlighterOnTouchListener7 implements OnTouchListener {

	  final ImageButton imageButton;

	  public ButtonHighlighterOnTouchListener7(final ImageButton imageButton) {
	    super();
	    this.imageButton = imageButton;
	  }

	  public boolean onTouch(final View view, final MotionEvent motionEvent) {
	    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	    	imageButton.setImageResource(R.drawable.checkftc);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.checkft);
	    }
	    return false;
	  }

	}
  
}

