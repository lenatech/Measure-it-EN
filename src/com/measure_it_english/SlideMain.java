package com.measure_it_english;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class SlideMain extends Activity implements OnGestureListener{

	private ViewFlipper flipper;//ViewFlipper   
	private GestureDetector detector;//觸摸監聽
	int count= 3;
	int viewSize = 3;
	private ImageView[] imgs;
    private int currentItem = 0;
    ImageButton button2;
    ImageButton bu3;
    ImageView v1;
    ImageButton imageButtoncam,ok,cancel;
      
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_slide_main);
		
		
		
		ImageButton bu = (ImageButton) findViewById(R.id.imageButton1);
		button2 = (ImageButton) findViewById(R.id.imageButton2);
		button2.setVisibility(View.INVISIBLE);
		bu3 = (ImageButton) findViewById(R.id.imageButton3);
		v1 = (ImageView) findViewById(R.id.imageView1);
		detector = new GestureDetector(this);//起始化觸摸
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);//取得ViewFlipper

		imgs = new ImageView[count];
        for(int i = 0; i< viewSize;i++) {
                imgs[i] = (ImageView) flipper.getChildAt(i);
                imgs[i].setEnabled(false);
                imgs[i].setTag(i);
            }
        imgs[0].setEnabled(true);
        bu.setOnTouchListener(new ButtonHighlighterOnTouchListener3(bu));
        button2.setOnTouchListener(new ButtonHighlighterOnTouchListener2(button2));
        bu3.setOnTouchListener(new ButtonHighlighterOnTouchListener(bu3));
		        bu.setOnClickListener(new OnClickListener(){

		            public void onClick(View w){
		            	 Intent intent = new Intent();
		                 intent.setClass(SlideMain.this, radiobutton.class);
		                 startActivity(intent);
		            	
		            }
		        });

		        button2.setOnClickListener(new OnClickListener(){

		            public void onClick(View w){
		            	 if(flipper.getDisplayedChild() == 0){
		          //      	 Toast.makeText(SlideMain.this, "Let's Start!", Toast.LENGTH_SHORT).show();
		            		 v1.setImageResource(R.drawable.slide1);
		            	     flipper.stopFlipping();                    
		                     
		                }else{
		            	flipper.setInAnimation(AnimationUtils.loadAnimation(SlideMain.this, R.layout.rightin));
		        		flipper.setOutAnimation(AnimationUtils.loadAnimation(SlideMain.this, R.layout.rightout));
		        		ViewChange(flipper.getDisplayedChild()-1);
		                flipper.showPrevious(); 
		                }
		            }
		        });
		        
		        bu3.setOnClickListener(new OnClickListener(){
 
		            public void onClick(View w){
		            	
		                	
		              if(flipper.getDisplayedChild() == viewSize-1){
		            	  v1.setImageResource(R.drawable.slide3);
		               		 flipper.stopFlipping();        
		      
		                 }else{
		                		flipper.setOutAnimation(AnimationUtils.loadAnimation(SlideMain.this, R.layout.leftout));
				            	flipper.setInAnimation(AnimationUtils.loadAnimation(SlideMain.this, R.layout.leftin));
		               	ViewChange(flipper.getDisplayedChild()+1);
		               	
		            flipper.showNext();
		                 }
		            }
		        });
		        
		        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide_main, menu);
		return true;
	}
	
	
	@Override  
    public boolean onTouchEvent(MotionEvent event) {   
		Log.i("Fling", "Activity onTouchEvent!");  
        return this.detector.onTouchEvent(event);   
    }  
	 
	

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,   
            float velocityY) {   
    	Log.i("Fling", "Fling Happened!");  
        if (e1.getX() - e2.getX() > 120) { 
        	
        	 if(flipper.getDisplayedChild() == viewSize-1){
        		 flipper.stopFlipping();        
        		 v1.setImageResource(R.drawable.slide3);
                 return false;
          }else{
        	flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.leftout));
        	flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.leftin));
        	ViewChange(flipper.getDisplayedChild()+1);
        
        	this.flipper.showNext();
    		
            return true;  
          }
        } else if (e1.getX() - e2.getX() < -120) {  
        	
             if(flipper.getDisplayedChild() == 0){
        	     flipper.stopFlipping();      
        	     v1.setImageResource(R.drawable.slide1);
                 return false;
            }else{
        	flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.rightin));
    		flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.rightout));
    		ViewChange(flipper.getDisplayedChild()-1);
    		
    		this.flipper.showPrevious(); 
            
            return true;  
            }
        }  
        return true;  
    }  
    public void  ViewChange(int position) {
        if(position < 0 || position > viewSize -1 || currentItem == position) {
                     return;
          }
       imgs[currentItem].setEnabled(false);
       imgs[position].setEnabled(true);
       currentItem = position;
       if(currentItem == 1){
   		v1.setImageResource(R.drawable.slide2);
   		button2.setVisibility(View.VISIBLE);
   		bu3.setVisibility(View.VISIBLE);
   		
   		}
   		else if(currentItem == 0)
   		{
   			v1.setImageResource(R.drawable.slide1);
   			button2.setVisibility(View.INVISIBLE);
   		}
   		else if(currentItem == 2)
   		{
   			v1.setImageResource(R.drawable.slide3);
   			bu3.setVisibility(View.INVISIBLE);
   		}
  }
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}  
	
	//返回離開
		 @Override
		 public boolean onKeyDown(int keyCode, KeyEvent event) {
			   if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			
			    final AlertDialog isExit = new AlertDialog.Builder(this).create();
			    Window window = isExit.getWindow();
            	WindowManager.LayoutParams lp = window.getAttributes();
            	lp.alpha=0.6f;
            	window.setAttributes(lp);
			 
			    isExit.setTitle("Exit?");
			
			   
			    LayoutInflater factory = getLayoutInflater();
            	   View loginDialogView = factory.inflate(R.layout.dialog2, null);  
            	
            	   isExit.setView(loginDialogView); 
            	 
            	   isExit.setView(loginDialogView, 0, 0, 0, 0);
            	   isExit.show();
            
            	 
            	ok = (ImageButton)isExit.findViewById(R.id.buttonok);
            	cancel = (ImageButton)isExit.findViewById(R.id.buttoncancel);
            	ok.setOnClickListener(new OnClickListener(){
            		public void onClick(View w){
            			
          				        Intent startMain = new Intent(Intent.ACTION_MAIN);
          				        startMain.addCategory(Intent.CATEGORY_HOME);
          				        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          				        startActivity(startMain);
          				       System.exit(0);
            		}
            	});
            	
            	cancel.setOnClickListener(new OnClickListener(){
            		 public void onClick(View w){
            			 isExit.cancel();
            		 }
            	});
       
			    return false;
			   }
			   return false;
			  }
		
	
	
	public class ButtonHighlighterOnTouchListener implements OnTouchListener {

		  final ImageButton imageButton;

		  public ButtonHighlighterOnTouchListener(final ImageButton imageButton) {
		    super();
		    this.imageButton = imageButton;
		  }

		  public boolean onTouch(final View view, final MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      //grey color filter, you can change the color as you like
		    	imageButton.setImageResource(R.drawable.right1);
		     
		    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
		    	imageButton.setImageResource(R.drawable.right);
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
		      //grey color filter, you can change the color as you like
		    	imageButton.setImageResource(R.drawable.left1);
		     
		    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
		    	imageButton.setImageResource(R.drawable.left);
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
		      //grey color filter, you can change the color as you like
		    	imageButton.setImageResource(R.drawable.adjustc);
		    	
		     
		    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
		    	imageButton.setImageResource(R.drawable.adjust2);
		    }
		    return false;
		  }

		}
}

