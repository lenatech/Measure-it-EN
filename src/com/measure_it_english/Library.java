package com.measure_it_english;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.MediaColumns;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.SoundPool;

public class Library extends Activity {

    private ImageButton Btn,talk,Btn2,ok,cancel,change; 
    static ImageView imageView,imageView2; 
    private DisplayMetrics mPhone;
    static  int come=0;
    static int done=0;
    Uri imgUri,myUri;
    static  int chge=0;
    static TextView t1;
    LibraryTouch img ;    
    static String cm="CM" ;  //物品實際長
    String path,mPath;
    Double n=LibraryTouch.v;
    private SoundPool sp;//聲明一個SoundPool
    private int music;//定義一個整型用load（）；來設置suondID
    int myProgress = 0,statusBarHeight,width,height; 
    ProgressDialog progDlg;
    Bitmap a,d;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   
	    
		setContentView(R.layout.library);
		this.setRequestedOrientation(1);
		talk = (ImageButton)findViewById(R.id.imageB1);  //reset
		 Btn = (ImageButton) findViewById(R.id.imageButton3);
		 Btn2 = (ImageButton) findViewById(R.id.imageButton2);
		 change = (ImageButton) findViewById(R.id.imageButton1);
		 final FrameLayout background = (FrameLayout)findViewById(R.id.FrameLayout1);
		 imageView = (ImageView)findViewById(R.id.img);
		 
		 t1 = (TextView) findViewById(R.id.textView1);
		 t1.setTextSize(convertPixelsToDp(convertDpToPixel(40,Library.this),Library.this));
		 t1.setBackgroundColor(Color.BLACK);
		 t1.setTextColor(Color.CYAN);
		 progDlg = new ProgressDialog(Library.this);
		 progDlg.setTitle("Please wait... ");
		 progDlg.setMessage("Capturing...");
		 progDlg.setIcon(R.drawable.ic);
		 progDlg.setCancelable(false);
		 progDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		 progDlg.setMax(100);
		 change.setImageResource(R.drawable.check61m);

		 //音效
		  init();
		 mPhone = new DisplayMetrics();
	      getWindowManager().getDefaultDisplay().getMetrics(mPhone);
	      
		 BitmapDrawable back = new BitmapDrawable(radiobutton.getBitmap());
		 background.setBackgroundDrawable(back);
		     t1.setText("Drag the lines.");
		     img = new LibraryTouch(Library.this);
	     Btn.setOnTouchListener(new ButtonHighlighterOnTouchListener(Btn));
	     talk.setOnTouchListener(new ButtonHighlighterOnTouchListener2(talk));
	     Btn2.setOnTouchListener(new ButtonHighlighterOnTouchListener3(Btn2));
	     change.setOnTouchListener(new ButtonHighlighterOnTouchListener4(change));
	     
	        //得到狀態列高度   
	        Rect frame = new Rect();     
	        Library.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);     
	        statusBarHeight = frame.top;      
	           
	        //得到螢幕長和高　
	        width = Library.this.getWindowManager().getDefaultDisplay().getWidth();     
	        height = Library.this.getWindowManager().getDefaultDisplay().getHeight(); 
	        
	       
	     
		Btn.setOnClickListener(new OnClickListener(){

            public void onClick(View w){
                if(done==0){
            	final AlertDialog isExit = new AlertDialog.Builder(Library.this).create();
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
                         intent.setClass(Library.this, SlideMain.class);
                         Library.this.finish(); 
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
                          intent.setClass(Library.this, SlideMain.class);
                          Library.this.finish(); 
                          done=0;
                          startActivity(intent);
                      }
            	
            }
        });

		talk.setOnClickListener(new OnClickListener(){

            public void onClick(View w){
            	 
            	onPick();
            	img = new LibraryTouch(Library.this);
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
            Double b=LibraryTouch.newKB3*LibraryTouch.cm;

      		
            	if(cm=="CM")
	          	{
            		LibraryTouch.n="M";
	          		b=Math.round(b/100*10000.0)/10000.0;
	          		come =1;
	          		cm="M";
	          		change.setImageResource(R.drawable.checkinch);
	          		t1.setText(""+b+"  M");
	          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener6(change));
	          	}
	          	else if(cm=="M")
	          	{
	          		LibraryTouch.n="INCH";
	          		b=Math.round(b*0.394*10000.0)/10000.0;
	          		
	          		cm="INCH";
	          		come =2;
	          		change.setImageResource(R.drawable.checkft);
	          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener7(change));
	          		t1.setText(""+b+"  INCH");
	          	}
	          	else if(cm=="INCH")
	          	{
	          		LibraryTouch.n="FT";
	          		b=Math.round(b/30.48*10000.0)/10000.0;
	          		
	          		cm="FT";
	          		come =3;
	          		change.setImageResource(R.drawable.check61cm);
	          		change.setOnTouchListener(new ButtonHighlighterOnTouchListener4(change));
	          		t1.setText(""+b+"  FT");
	          	}
	          	else if(cm=="FT")
	          	{
	          		LibraryTouch.n="CM";
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
	//    	imgUri = Uri.parse("file://" + dir + "/" + fname);    //依前面的路徑及檔名建立 Uri 物件
	
	    	ContentValues values = new ContentValues();
	        values.put(Images.Media.DATA, gat);
	        values.put(Images.Media.TITLE, fname);
	        values.put(Images.Media.DISPLAY_NAME, fname);
	        values.put(Images.Media.MIME_TYPE, "image/jpeg");
	        imgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	        Log.e("","pic file name : "+fname);
		        try {   
		        	
		        	OutputStream os = Library.this.getContentResolver().openOutputStream(imgUri);
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
		            		a = takeScreenShot(Library.this);	    
		            		
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
		  		Toast.makeText(Library.this, "Picture have saved into the album", Toast.LENGTH_SHORT).show();
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
	    	imageButton.setImageResource(R.drawable.gallery270c);
	    
	      
	    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
	    	imageButton.setImageResource(R.drawable.gallery270);
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
protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    
    if(resultCode == Activity.RESULT_OK) { //要求的意圖成功了
    	switch(requestCode) {
    	
    	case 101: //選取相片
    		imgUri = convertUri(data.getData());  //取得選取相片的 Uri 並做 Uri 格式轉換
    		done=0;
    		break;
    	}
    	showImg();  //顯示 imgUri 所指明的相片

    }
    else { //要求的意圖沒有成功
    	Toast.makeText(this, "You haven't choose the picture.", 
    						 Toast.LENGTH_LONG).show();
    }

}
   Uri convertUri(Uri uri) {
   		if(uri.toString().substring(0, 7).equals("content")) {  //如果是以 "content" 開頭
			String[] colName = { MediaColumns.DATA };    //宣告要查詢的欄位
			Cursor cursor = getContentResolver().query(uri, colName,  //以 imgUri 進行查詢
					                                   null, null, null); 
			cursor.moveToFirst();      //移到查詢結果的第一筆記錄
			uri = Uri.parse("file://" + cursor.getString(0)); //將路徑轉為 Uri   			
		}
    	return uri;   //傳回 Uri 物件
    }
 public void onPick() {
		Intent it = new Intent(Intent.ACTION_GET_CONTENT);    //動作設為 "選取內容" 
		it.setType("image/*");     		  //設定要選取的媒體類型為：所有類型的圖片
		startActivityForResult(it, 101);  //啟動意圖, 並要求傳回選取的圖檔	
    }
 void showImg() {
    	int iw, ih, vw, vh;
    	boolean needRotate;  //用來儲存是否需要旋轉

    	BitmapFactory.Options option = new BitmapFactory.Options(); //建立選項物件
		option.inJustDecodeBounds = true;      //設定選項：只讀取圖檔資訊而不載入圖檔
		BitmapFactory.decodeFile(imgUri.getPath(), option);  //讀取圖檔資訊存入 Option 中
		iw = option.outWidth;   //由 option 中讀出圖檔寬度
		ih = option.outHeight;  //由 option 中讀出圖檔高度
		vw = Library.imageView.getWidth();    //取得 ImageView 的寬度
		vh = Library.imageView.getHeight();   //取得 ImageView 的高度
		
		int scaleFactor;		
		if(iw<ih) {    //如果圖片的寬度小於高度 
			needRotate = false;       				//不需要旋轉
			scaleFactor = Math.min(iw/vw, ih/vh);   // 計算縮小比率
		}
		else {
			needRotate = true;       				//需要旋轉
			scaleFactor = Math.min(iw/vh, ih/vw);   // 將 ImageView 的寬、高互換來計算縮小比率			
		}
    		
        option.inJustDecodeBounds = false;  //關閉只載入圖檔資訊的選項
        option.inSampleSize = scaleFactor;  //設定縮小比例, 例如 2 則長寬都將縮小為原來的 1/2
        option.inPurgeable = true;        	//設定在記憶體不夠時, 允許系統將圖片內容刪除
 		Bitmap bmp = BitmapFactory.decodeFile(imgUri.getPath(), option); //載入圖檔

 		if(needRotate) { //如果需要旋轉
    		Matrix matrix = new Matrix();  //建立 Matrix 物件
    		matrix.postRotate(90);         //設定旋轉角度
    		bmp = Bitmap.createBitmap(bmp ,//用原來的 Bitmap 產生一個新的 Bitmap 
    			  0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
 		}
 		imageView.setImageBitmap(bmp);      //顯示圖片
    }

}

