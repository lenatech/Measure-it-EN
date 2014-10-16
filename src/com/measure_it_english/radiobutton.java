package com.measure_it_english;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.app.AlertDialog;


public class radiobutton  extends Activity {
	private  TextView   textView=null;
	private  RadioGroup  radioGroup=null;
	private  RadioButton  radioButton_creditcard,radioButton_paper,radioButton_add,radioButton_paperwidth,radioButton_iphone4,radioButton_iphone5;
	private  RadioButton radioButton_s3,radioButton_note2,radioButton_sonyz,radioButton_htcnew;
	private  ImageButton imageButtoncam,ok,cancel,library;
	private  LinearLayout linearlayout;
	private  FrameLayout framelayout;
	private  ScrollView scrollview;
	private AutoCompleteTextView editTextName;
	private static AutoCompleteTextView editTextNum;
	private String e1;
	private String e2 ;
	
	Uri imgUri;
	AlertDialog radioAlertDialog;
	static int product=1;
	static String a;
	static int screenHeight;
	static int screenWidth;
	
	String text1="";
	String text2="";
	static Bitmap bmp;
	static String b;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.setRequestedOrientation(1);
		setContentView(R.layout.radiobutton);
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth =  dm.widthPixels;
        screenHeight = dm.heightPixels;
        imageButtoncam = (ImageButton) findViewById(R.id.imageButton1);
		imageButtoncam.setOnTouchListener(new ButtonHighlighterOnTouchListener(imageButtoncam));
		
		//framelayout =(FrameLayout)findViewById(R.id.FrameLayout2); 
		 linearlayout = (LinearLayout)findViewById(R.id.LinearLayout3); 
	     scrollview = (ScrollView)findViewById(R.id.ScrollView); 
	
	 	
	 	android.widget.LinearLayout.LayoutParams    lp = (android.widget.LinearLayout.LayoutParams)  linearlayout.getLayoutParams();
        lp.height = screenHeight-imageButtoncam.getHeight();
		
		textView = (TextView)findViewById(R.id.radiogroup_info_id);  
		
		//radioGroup
		radioGroup=(RadioGroup)findViewById(R.id.radioGroup_thing_id);
		radioButton_creditcard=(RadioButton)findViewById(R.id.creditcard_id);
		radioButton_paper=(RadioButton)findViewById(R.id.paper_id);
	
		radioButton_add=(RadioButton)findViewById(R.id.add_id);
		radioButton_paperwidth=(RadioButton)findViewById(R.id.paperwidth_id);
		
		radioButton_iphone4=(RadioButton)findViewById(R.id.iphone4_id);
		radioButton_iphone5=(RadioButton)findViewById(R.id.iphone5_id);
		radioButton_s3=(RadioButton)findViewById(R.id.s3_id);
		radioButton_note2=(RadioButton)findViewById(R.id.note2_id);
		radioButton_sonyz=(RadioButton)findViewById(R.id.sonyz_id);
		radioButton_htcnew=(RadioButton)findViewById(R.id.htcnew_id);
		
		
		
		
		
		library=(ImageButton) findViewById(R.id.imageButton2);
		radioButton_creditcard.setText("Credit card");
		radioButton_paper.setText("A4 paper length");
		radioButton_add.setText("Add new scale");
	
		
		radioGroup.setOnCheckedChangeListener(listen);

		
		library.setOnTouchListener(new ButtonHighlighterOnTouchListener2(library));
		library.setOnClickListener(new OnClickListener(){

	            public void onClick(View arg0){
	            
	            	onPick();
	            	
	            }
	        });  
		
		
		imageButtoncam.setOnClickListener(new OnClickListener(){

	            public void onClick(View w){
	            	
				     Intent intent = new Intent();
	                 intent.setClass(radiobutton.this, Main.class);
	                 startActivity(intent);
	            }
	        });
		
		radioButton_add.setOnClickListener(new OnClickListener(){

            public void onClick(View w){
            	
            	AlertDialog.Builder editDialog = new AlertDialog.Builder(radiobutton.this);
            	editDialog.setCancelable(false);
  	   
            	radioAlertDialog = editDialog.create();  
              	 
              	radioAlertDialog.setTitle("New:");
            	   
            	   LayoutInflater factory = getLayoutInflater();
               	   View loginDialogView = factory.inflate(R.layout.fix, null);  
               	
               	 radioAlertDialog.setView(loginDialogView);  
               
               	 radioAlertDialog.show();  
             	ok = (ImageButton)radioAlertDialog.findViewById(R.id.buttonok);
             	cancel = (ImageButton)radioAlertDialog.findViewById(R.id.buttoncancel);

            	Window window = radioAlertDialog.getWindow();
            	WindowManager.LayoutParams lp = window.getAttributes();
            	lp.alpha=0.6f;
            	window.setAttributes(lp);
            	 editTextName =(AutoCompleteTextView)radioAlertDialog.findViewById(R.id.editTextName);
            	 editTextNum = (AutoCompleteTextView)radioAlertDialog.findViewById(R.id.editTextNum);
	        	 
	        	initAutoComplete("history", editTextName);
    		    initAutoComplete2("history2", editTextNum);
    		
    		    
             	ok.setOnClickListener(new OnClickListener(){

    	            public void onClick(View w){
    	          	 e1 = editTextName.getText().toString();
       	        	 e2 = editTextNum.getText().toString();
       	        	 
       	        	 try {
       	        		 if(!e1.equals("") && !e2.equals("")){
             	   	    
       	        			text1="New:";
      	        			 text2=" CM";
      	        			 imageButtoncam .setVisibility(View.VISIBLE);
      	        			library.setVisibility(View.VISIBLE);
      	        			saveHistory("history",  editTextName);
    						saveHistory2("history2", editTextNum); 
        	        	     }
       	        		 else
       	        		 {
       	        			 e1="Please select again";
       	        			 e2="!";
       	        			
      	        			imageButtoncam .setVisibility(View.INVISIBLE);
      	        			library.setVisibility(View.INVISIBLE);
       	        			 
       	        		 }
       	        	 }
       	        	
       	        	 
       	        		catch(Exception e) { 
       	        		 e.printStackTrace();  
       	        		}
       	        	 
       	        	textView.setText(text1+e1+"  "+e2 +text2);
              	    	a=e1;
              	    	b=e2;
              	    	radioAlertDialog.dismiss();
    	            }
    	        });
               	

        		cancel.setOnClickListener(new OnClickListener(){

        	            public void onClick(View w){
        	            	
        	            	
                  	    	
            	        		e1="Please select again";
       	        			 e2="!";
    	        			
    	        			imageButtoncam .setVisibility(View.INVISIBLE);
    	        			library.setVisibility(View.INVISIBLE);
    	        			textView.setText(text1+e1+"  "+e2 +text2);	
    	        			radioAlertDialog.dismiss();
        	            }
        	        });
            }
        });
		
	}
	private void initAutoComplete(String field, AutoCompleteTextView auto) {
		SharedPreferences sp = getSharedPreferences("network_url", 0);
		String longhistory = sp.getString("history", "1cm");
		String[] hisArrays = longhistory.split(",");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, hisArrays);
	
		if (hisArrays.length > 50) {
			String[] newArrays = new String[50];
			System.arraycopy(hisArrays, 0, newArrays, 0, 50);
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, newArrays);
		}
		auto.setAdapter(adapter);
		auto.setDropDownHeight(300);
		auto.setThreshold(1);
	//	auto.setCompletionHint("history");
		auto.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AutoCompleteTextView view = (AutoCompleteTextView) v;
				if (hasFocus) {
					view.showDropDown();
			//		Toast.makeText(radiobutton.this, "SHOW!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void saveHistory(String field, AutoCompleteTextView auto) {
		String text = auto.getText().toString();
		SharedPreferences sp = getSharedPreferences("network_url", 0);
		String longhistory = sp.getString(field, "1cm");
		if (!longhistory.contains(text + ",")) {
			StringBuilder sb = new StringBuilder(longhistory);
			sb.insert(0, text + ",");
			sp.edit().putString("history", sb.toString()).commit();
		//	Toast.makeText(radiobutton.this, "儲存!", Toast.LENGTH_SHORT).show();
		}
	}
	private void initAutoComplete2(String field, AutoCompleteTextView auto) {
		SharedPreferences sp = getSharedPreferences("network_url", 0);
		String longhistory = sp.getString("history2", "1");
		String[] hisArrays = longhistory.split(",");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, hisArrays);
	
		if (hisArrays.length > 50) {
			String[] newArrays = new String[50];
			System.arraycopy(hisArrays, 0, newArrays, 0, 50);
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, newArrays);
		}
		auto.setAdapter(adapter);
		auto.setDropDownHeight(300);
		auto.setThreshold(1);
	//	auto.setCompletionHint("history");
		auto.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AutoCompleteTextView view = (AutoCompleteTextView) v;
				if (hasFocus) {
					view.showDropDown();
			//		Toast.makeText(radiobutton.this, "SHOW!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void saveHistory2(String field, AutoCompleteTextView auto) {
		String text = auto.getText().toString();
		SharedPreferences sp = getSharedPreferences("network_url", 0);
		String longhistory = sp.getString(field, "1");
		if (!longhistory.contains(text + ",")) {
			StringBuilder sb = new StringBuilder(longhistory);
			sb.insert(0, text + ",");
			sp.edit().putString("history2", sb.toString()).commit();
		//	Toast.makeText(radiobutton.this, "儲存!", Toast.LENGTH_SHORT).show();
		}
	}
	public class ButtonHighlighterOnTouchListener implements OnTouchListener {

		  final ImageButton imageButton;

		  public ButtonHighlighterOnTouchListener(final ImageButton imageButton) {
		    super();
		    this.imageButton = imageButton;
		  }

		  public boolean onTouch(final View view, final MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		    	imageButton.setImageResource(R.drawable.camerac);
		
		    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
		    	imageButton.setImageResource(R.drawable.cam4);
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
		    	 
		    	/*  Context mContext = radiobutton.this;
	           		LayoutInflater mLayoutInflater = (LayoutInflater) mContext
	           				.getSystemService(LAYOUT_INFLATER_SERVICE);
	           		View music_popunwindwow = mLayoutInflater.inflate(
	           				R.layout.say_layout, null);
	           		final PopupWindow mPopupWindow = new PopupWindow(music_popunwindwow,
	           				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

	           		mPopupWindow.showAtLocation(findViewById(R.id.FrameLayout2), Gravity.CENTER, 0, 0);     
	           		
	           		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
	           		  
	           		mPopupWindow.setFocusable(true);  
	           		mPopupWindow.setOutsideTouchable(true); */
	           		
		    	
				
				Toast showImageToast=new Toast(radiobutton.this);		         
		         ImageView imageView=new ImageView(radiobutton.this);	       
		         imageView.setImageResource(R.drawable.say_galleryen);		       
		         showImageToast.setView(imageView);		         
		         showImageToast.setGravity(Gravity.CENTER, 0, 0);		         
		         showImageToast.setDuration(Toast.LENGTH_LONG);
		         showImageToast.show();
		         
		    	imageButton.setImageResource(R.drawable.gallery480c);
		
		    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
		    	imageButton.setImageResource(R.drawable.gallery480);
		    }
		    return false;
		  }

		}
	private OnCheckedChangeListener  listen=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
		int id=	group.getCheckedRadioButtonId();
			switch (group.getCheckedRadioButtonId()) {
			case R.id.creditcard_id:
				textView.setText("Chosen:"+radioButton_creditcard.getText());
				product=1;
				
				imageButtoncam .setVisibility(View.VISIBLE);
    			library.setVisibility(View.VISIBLE);
				
				break;
			case R.id.paper_id:
				textView.setText("Chosen:"+radioButton_paper.getText());
				product=2;
				
				imageButtoncam .setVisibility(View.VISIBLE);
    			library.setVisibility(View.VISIBLE);
				
				break;
			
			
			case R.id.add_id:
		
				product=10;
				
				break;
			case R.id.paperwidth_id:
				textView.setText("Chosen:"+radioButton_paperwidth.getText());
				
				product=3;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			
		
			case R.id.iphone4_id:
				textView.setText("Chosen:"+radioButton_iphone4.getText());
				product=4;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			case R.id.iphone5_id:
				textView.setText("Chosen:"+radioButton_iphone5.getText());
				product=5;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			case R.id.s3_id:
				textView.setText("Chosen:"+radioButton_s3.getText());
				product=6;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			case R.id.note2_id:
				textView.setText("Chosen:"+radioButton_note2.getText());
				product=7;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			case R.id.sonyz_id:
				textView.setText("Chosen:"+radioButton_sonyz.getText());
				product=8;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
			case R.id.htcnew_id:
				textView.setText("Chosen:"+radioButton_htcnew.getText());
				product=9;
				
					imageButtoncam .setVisibility(View.VISIBLE);
	    			library.setVisibility(View.VISIBLE);
				break;
				
            default:
            	textView.setText("Chosen:"+radioButton_creditcard.getText());
            	product=1;
            	
            	imageButtoncam .setVisibility(View.VISIBLE);
    			library.setVisibility(View.VISIBLE);
				break;
			}
			
		}
	};
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       
       
    	
        if(resultCode == Activity.RESULT_OK ) { //要�??��??��??��?
        	
        	switch(requestCode) {
        	
        	case 101: //?��??��?
        		imgUri = convertUri(data.getData());  //?��??��??��???Uri 並�? Uri ?��?轉�?
        		break;
        	}
        	showImg();  //顯示 imgUri ????��??��?
        	
        }
        else { //要�??��??��??��???
        	Toast.makeText(this, "Fail to choose a picture.", Toast.LENGTH_LONG).show();
        }

    }
	   Uri convertUri(Uri uri) {
	   		if(uri.toString().substring(0, 7).equals("content")) {  //如�??�以 "content" ?�頭
				String[] colName = { MediaColumns.DATA };    //�??要查詢�?欄�?
				Cursor cursor = getContentResolver().query(uri, colName,  //�?imgUri ?��??�詢
						                                   null, null, null); 
				cursor.moveToFirst();      //移到?�詢結�??�第�??記�?
				uri = Uri.parse("file://" + cursor.getString(0)); //將路徑�???Uri   			
			}
	    	return uri;   //?��? Uri ?�件
	    }
	  void onPick() {
			Intent it = new Intent(Intent.ACTION_GET_CONTENT);    //?��?設為 "?��??�容" 
			it.setType("image/*");     		  //設�?要選?��?媒�?類�??��????類�??��???
			startActivityForResult(it, 101);  //?��??��?, 並�?求傳?�選?��??��?	
	    }
	 void showImg() {
	    	int iw, ih, vw, vh;
	    	boolean needRotate;  //?��??��??�否????��?

	    	BitmapFactory.Options option = new BitmapFactory.Options(); //建�??��??�件
			option.inJustDecodeBounds = true;      //設�??��?：只�???��?資�??��?載入?��?
			BitmapFactory.decodeFile(imgUri.getPath(), option);  //�???��?資�?存入 Option �?
			iw = option.outWidth;   //??option 中�??��?檔寬�?
			ih = option.outHeight;  //??option 中�??��?檔�?�?
			vw = screenWidth;    //?��? ImageView ?�寬�?
			vh = screenHeight;   //?��? ImageView ?��?�?
			
			int scaleFactor;		
			if(iw<ih) {    //如�??��??�寬度�??��?�?
				needRotate = false;       				//不�?要�?�?
				scaleFactor = Math.min(iw/vw, ih/vh);   // 計�?縮�?比�?
			}
			else {
				needRotate = true;       				//????��?
				scaleFactor = Math.min(iw/vh, ih/vw);   // �?ImageView ?�寬?��?互�?來�?算縮小�???		
			}
	    		
	        option.inJustDecodeBounds = false;  //?��??��??��?檔�?訊�??��?
	        option.inSampleSize = scaleFactor;  //設�?縮�?比�?, 例�? 2 ?�長寬都將縮小為?��???1/2
	        option.inPurgeable = true;        	//設�??��??��?不�??? ?�許系統將�??�內容刪??
	 		bmp = BitmapFactory.decodeFile(imgUri.getPath(), option); //載入?��?

	 		if(needRotate) { //如�?????��?
	    		Matrix matrix = new Matrix();  //建�? Matrix ?�件
	    		matrix.postRotate(90);         //設�??��?角度
	    		bmp = Bitmap.createBitmap(bmp ,//?��?來�? Bitmap ?��?�???��? Bitmap 
	    			  0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
	 		}
	 		
	 		Intent intent = new Intent(radiobutton.this, Library.class);
	 		
	 		startActivity(intent);
	 		
	    }
	 
	 public static Bitmap getBitmap(){
		 return bmp;
		 }
}; 
