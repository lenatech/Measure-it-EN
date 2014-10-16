package com.measure_it_english;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;



	public class LibraryTouch extends View {
		 private Paint paint = new Paint();
		 private int pro=radiobutton.product;
     	 private String dan ="  "; 
     	 int done =1;
     	 private int screenHeight =  radiobutton.screenHeight;
        	private int screenWidth=  radiobutton.screenWidth;
		 static double newKB;
		 static double newKB2;
		 static double newKB3;
		 Point pa= new Point(screenWidth/4,screenHeight/4);
		 Point 	pb= new Point(screenWidth/4,screenHeight*3/4);
		 Point 	p1= new Point(screenWidth*3/4,screenHeight/4);
		 Point 	p2= new Point(screenWidth*3/4,screenHeight*3/4);
		 private double temp;
		 static double v;
		 static double vv;
		 int x0, y0;
		 FontMetrics fontMetrics = paint.getFontMetrics();  
		 int x = screenWidth/2;
		 float y = fontMetrics.bottom;
		 static Double cm;
		 Rect textBounds = new Rect();
		  
		 String text = "Drag the lines.";
	        int size = getResources().getDimensionPixelSize(R.dimen.myFontSize);
	        int size2 = getResources().getDimensionPixelSize(R.dimen.StrokeWidth);
	
		 int move = 0;
		 static String n = "CM";
		 static int centerX, centerY;

		 
		 
	    public LibraryTouch(Context context) {
	        super(context, null);
	        
	    }

	    public LibraryTouch(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	 
	
	    
	    @Override
	    public void onSizeChanged(int w, int h, int oldw, int oldh) {
	        centerX = w / 2;
	        centerY = h / 2;
	        super.onSizeChanged(w, h, oldw, oldh);
	       
	    }

	    
	    @Override
	    public void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        
	        paint.setStrokeWidth(size2);
	        paint.setTextSize(size);
	        paint.setTextAlign(Paint.Align.CENTER);
	        paint.getTextBounds(text, 0, text.length(), textBounds);
	        
	        
         	   int a =(pa.x)-( pb.x);
           	   int b =(pa.y)-(pb.y);
          	   int aa =(p1.x)-(p2.x);
               int bb =(p1.y)-(p2.y);
               if(pro==1)
	         	{
	         		dan=" Credit card ";
	         		cm=8.6;
	         	}
               else if(pro==2)
	         	{
	         		dan=" A4 paper length ";
	         		cm=29.7;
	         	}
               else if(pro==3)
	         	{
	         		dan=" A4 paper width ";
	         		cm=21.0;
	         	}
             
               else if(pro==4)
	         	{
	         		dan=" iphone4   ";
	         		cm=11.52;
	         	}
           else if(pro==5)
	         	{
	         		dan=" iphone5 ";
	         		cm=12.38;
	         	}
           else if(pro==6)
        	{
        		dan=" S3 ";
        		cm=13.66;
        	}
           else if(pro==7)
        	{
        		dan=" Note2 ";
        		cm=15.11;
        	}
           else if(pro==8)
        	{
        		dan=" Sony Z ";
        		cm=13.9;
        	}
           else if(pro==9)
        	{
        		dan=" New One ";
        		cm=13.74;
        	}
            
               else if(pro==10)
	         	{	         
	         		dan=""+radiobutton.a;
	         		cm=Double.parseDouble(radiobutton.b);
	         	}         
	            canvas.drawColor(Color.TRANSPARENT);
	            double d = FloatMath.sqrt(a*a + b*b);
	            newKB = Math.round(d*1000000.0)/1000000.0;	                     
      
                
                double d2 = FloatMath.sqrt(aa*aa + bb*bb);
	            newKB2 = Math.round(d2*1000000.0)/1000000.0;
            

                paint.setColor(Color.CYAN);
                double d3 = d2/d;
                newKB3 = Math.round(d3*1000000.0)/1000000.0;
	        	 paint.setTextSize(size*2);
	       	    
	       	  
	       	     if(text=="Draw the line~")
	       	     {
	       	    	paint.setColor(Color.BLACK);
	       	    	 canvas.drawRect(0, 0, screenWidth, textBounds.height()*3+3, paint);
	       	    	 paint.setColor(Color.LTGRAY);
	       	    	 canvas.drawText(""+text, x,  textBounds.height()*2+7, paint);
	       	     }
	       	     else
	       	     {
	       	    	paint.setColor(Color.BLACK);
	       	    	canvas.drawRect(0, 0, screenWidth, textBounds.height()*4-5, paint);
	       	     paint.setColor(Color.CYAN);
	        	   canvas.drawText(""+text, x,  textBounds.height()*3-5, paint);
	       	     }
	        	 paint.setTextSize(size);
	        	
	        	
	        	 paint.setColor(Color.GREEN);
	        	int sx=pa.x;
	        	int sy=pa.y;
	        	int ex=pb.x;
	        	int ey=pb.y;
	        	 canvas.drawLine(sx, sy, ex, ey,paint);
	        	 paint.setColor(Color.MAGENTA);

	        	 canvas.drawText(""+dan,(sx+ex)/2-dan.length() ,(sy+ey)/2, paint);
	        	 
	        	 int sx2= p1.x;
		         int sy2= p1.y;
		         int ex2= p2.x;
		         int ey2= p2.y;
		         paint.setColor(Color.rgb(247, 207, 43));
		         canvas.drawLine(sx2, sy2, ex2, ey2,paint);
		       
	
		         paint.setColor(Color.CYAN);
		         canvas.drawText("Object",(sx2+ex2)/2-size/2 ,(sy2+ey2)/2, paint);
	        	 
		         temp = newKB3*cm;  //CM
	       		    if(Library.come==0)  //CM
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    }
	       		    else if(Library.come==1)  //M
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v/100;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		    else if(Library.come==2) //INCH
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v*0.394;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		    else if(Library.come==3) //FT
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v/30.48;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		    
	       		    text =""+v+"  "+n ;
	       		    
	        }
	
	    

	    
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        int action = event.getAction();
	 
	        int x = (int) event.getX();
	        int y = (int) event.getY();
		    Library.t1.setVisibility(View.INVISIBLE);
	        switch (action) {

	        case MotionEvent.ACTION_DOWN: 
	            x0 = x;
	            y0 = y;
	            int dda=x0-pa.x;
	        	int ddb=x0-pb.x;
	        	int dd1=x0-p1.x;
	        	int dd2=x0-p2.x;
	        	int dday= (y0-pa.y);
	        	int ddby= (y0-pb.y);
	        	int dd1y= (y0-p1.y);
	        	int dd2y= (y0-p2.y);
	        	
	        	int cx1= (dda+ddb)/2;
	        	int cy1= (dday+ddby)/2;
	        	int cx2= (dd1+dd2)/2;
	        	int cy2= (dd1y+dd2y)/2;
	        	
	           	double d1= Math.round(FloatMath.sqrt(dda*dda + dday*dday)*10.0)/10.0;	
	        	double d2= Math.round(FloatMath.sqrt(ddb*ddb + ddby*ddby)*10.0)/10.0;	
	        	double d3= Math.round(FloatMath.sqrt(dd1*dd1 + dd1y*dd1y)*10.0)/10.0;	
	        	double d4= Math.round(FloatMath.sqrt(dd2*dd2 + dd2y*dd2y)*10.0)/10.0;
	        	double d5= Math.round(FloatMath.sqrt(cx1*cx1 + cy1*cy1)*10.0)/10.0;
	        	double d6= Math.round(FloatMath.sqrt(cx2*cx2 + cy2*cy2)*10.0)/10.0;

	        	if(d1<d2 && d1<d3 && d1<d4 && d1<d5 && d1<d6)
	        	{
	        		move=1;
	        	}
	        	else if(d2<d1&&d2<d3&&d2<d4 && d2<d5 && d2<d6)
	        	{
	        		move=2;
	        	}
	        	else if(d3<d1&&d3<d2&&d3<d4 && d3<d5 && d3<d6)
	        	{
	        		move=3;
	        	}
	        	else if(d4<d1&&d4<d2&&d4<d3 && d4<d5 && d4<d6)
	        	{
	        		move=4;
	        	}
	        	else if(d5<d1&&d5<d2&&d5<d3 && d5<d4 && d5<d6)
	        	{
	        		move=5;
	        	}
	        	
	        	else if(d6<d1&&d6<d2&&d6<d3&& d6<d4 && d6<d5)
	        	{
	        		move=6;
	        	}
	            break;
	        case MotionEvent.ACTION_MOVE: 
	         
	        	Library.done = 0;
	        	
	        	if(move==1)
	        	{
	        		pa.x += x - x0;
			        pa.y += y - y0;
	        	}
	        	else if(move==2)
	        	{
	        		pb.x += x - x0;
		            pb.y += y - y0;
	        	}
	        	else if(move==3)
	        	{
	        		 p1.x += x - x0;
			         p1.y += y - y0;
	        	}
	        	else if(move==4)
	        	{
	        		p2.x += x - x0;
		            p2.y += y - y0;
	        	}
	
	        	else if(move==5)
	        	{
	        		pa.x += x - x0;
			        pa.y += y - y0;
			        pb.x += x - x0;
		            pb.y += y - y0;
	        	}
	        	else if(move==6)
	        	{
	        		p1.x += x - x0;
			        p1.y += y - y0;
	        		p2.x += x - x0;
		            p2.y += y - y0;
	        	}
	        	
	         x0 = x;
	         y0 = y;
	            invalidate();
	            break;
	        
	            
	        	
	        case MotionEvent.ACTION_UP: 
	            break;
	        }

	       
	        return true;
	        
	    }
	    
	    
	 
	    
	}