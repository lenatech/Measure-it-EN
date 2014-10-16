package com.measure_it_english;




import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;



	public class TouchImageView extends View {
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
	        int size3 = getResources().getDimensionPixelSize(R.dimen.StrokeWidth2);
		 int move = 0;
		 static String n = "CM";
		 
	    public TouchImageView(Context context) {
	        super(context, null);
	        
	    }

	    public TouchImageView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    @Override
	    public void onSizeChanged(int w, int h, int oldw, int oldh) {
	    	screenWidth = w ;
	        screenHeight = h ;
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
	       	    
	       	  
	       	     if(text=="Drag the lines.")
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
	        	 
		         

	        	 paint.setColor(Color.GREEN);
	        
	        	    double H = 5;     
			        double L = 50;  
			        int x3 = 0;  
			        int y3 = 0;  
			        int x4 = 0;  
			        int y4 = 0;  
			        double awrad = Math.atan(L / H); 
			        double arraow_len = Math.sqrt(L * L + H * H);      
			        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
			        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
			        double x_3 = ex - arrXY_1[0];    
			        double y_3 = ey - arrXY_1[1];  
			        double x_4 = ex - arrXY_2[0];  
			        double y_4 = ey - arrXY_2[1];  
			        Double X3 = new Double(x_3);  
			        x3 = X3.intValue();  
			        Double Y3 = new Double(y_3);  
			        y3 = Y3.intValue();  
			        Double X4 = new Double(x_4);  
			        x4 = X4.intValue();  
			        Double Y4 = new Double(y_4);  
			        y4 = Y4.intValue();  
	        	 
			        paint.setStrokeWidth(size3); 
			        canvas.drawLine(x3, y3, x4, y4,paint);
			        
	        	 
			        int x5 = 0;  
			        int y5 = 0;  
			        int x6 = 0;  
			        int y6 = 0;  
			          
			        double[] arrXY_3 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
			        double[] arrXY_4 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
			        double x_5 = sx - arrXY_3[0];    
			        double y_5 = sy - arrXY_3[1];  
			        double x_6 = sx - arrXY_4[0];  
			        double y_6 = sy - arrXY_4[1];  
			        Double X5 = new Double(x_5);  
			        x5 = X5.intValue();  
			        Double Y5 = new Double(y_5);  
			        y5 = Y5.intValue();  
			        Double X6 = new Double(x_6);  
			        x6 = X6.intValue();  
			        Double Y6 = new Double(y_6);  
			        y6 = Y6.intValue();  
	        	 
			        paint.setColor(Color.GREEN);
			        paint.setStrokeWidth(size3); 
			        canvas.drawLine(x5, y5, x6, y6,paint);
			        
			        
			       
			        int x7 = 0;  
			        int y7 = 0;  
			        int x8 = 0;  
			        int y8 = 0;   
			        double[] arrXY_5 = rotateVec(ex2 - sx2, ey2 - sy2, awrad, true, arraow_len);  
			        double[] arrXY_6= rotateVec(ex2 - sx2, ey2 - sy2, -awrad, true, arraow_len);  
			        double x_7 = ex2 - arrXY_5[0];    
			        double y_7 = ey2 - arrXY_5[1];  
			        double x_8 = ex2 - arrXY_6[0];  
			        double y_8 = ey2 - arrXY_6[1];  
			        Double X7 = new Double(x_7);  
			        x7 = X7.intValue();  
			        Double Y7 = new Double(y_7);  
			        y7 = Y7.intValue();  
			        Double X8 = new Double(x_8);  
			        x8 = X8.intValue();  
			        Double Y8 = new Double(y_8);  
			        y8 = Y8.intValue();  
	        	 
			        paint.setColor(Color.rgb(247, 207, 43));
			        paint.setStrokeWidth(size3); 
			        canvas.drawLine(x7, y7, x8, y8,paint);
			        
			      
			        int x9 = 0;  
			        int y9 = 0;  
			        int x10 = 0;  
			        int y10 = 0;     
			        double[] arrXY_7 = rotateVec(ex2 - sx2, ey2 - sy2, awrad, true, arraow_len);  
			        double[] arrXY_8 = rotateVec(ex2 - sx2, ey2 - sy2, -awrad, true, arraow_len);  
			        double x_9 = sx2 - arrXY_7[0];    
			        double y_9 = sy2 - arrXY_7[1];  
			        double x_10 = sx2 - arrXY_8[0];  
			        double y_10 = sy2 - arrXY_8[1];  
			        Double X9 = new Double(x_9);  
			        x9 = X9.intValue();  
			        Double Y9 = new Double(y_9);  
			        y9 = Y9.intValue();  
			        Double X10 = new Double(x_10);  
			        x10 = X10.intValue();  
			        Double Y10 = new Double(y_10);  
			        y10 = Y10.intValue();  
	        	 
			        paint.setColor(Color.rgb(247, 207, 43));
			        paint.setStrokeWidth(size3); 
			        canvas.drawLine(x9, y9, x10, y10,paint);
			        
			        
			        
			        
			        
		         temp = newKB3*cm;  //CM
	       		    if(Scam.come==0)  //CM
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    }
	       		    else if(Scam.come==1)  //M
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v/100;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		    else if(Scam.come==2) //INCH
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v*0.394;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		    else if(Scam.come==3) //FT
	       		    {
	       		    	v = Math.round(temp*10000.0)/10000.0;
	       		    	v = v/30.48;
	       		    	v = Math.round(v*10000.0)/10000.0;
	       		    }
	       		  text =""+v+"  "+n ;
	        }
	
	    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen)  
	    {  
	        double mathstr[] = new double[2];  
	       
	        double vx = px * Math.cos(ang) - py * Math.sin(ang);  
	        double vy = px * Math.sin(ang) + py * Math.cos(ang);  
	        if (isChLen) {  
	            double d = Math.sqrt(vx * vx + vy * vy);  
	            vx = vx / d * newLen;  
	            vy = vy / d * newLen;  
	            mathstr[0] = vx;  
	            mathstr[1] = vy;  
	        }  
	        return mathstr;  
	    }  
	
	   
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        int action = event.getAction();
	 
	        int x = (int) event.getX();
	        int y = (int) event.getY();
		    Scam.t1.setVisibility(View.INVISIBLE);
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
	  //          Log.i("down", "(" + x0 + "," + y0 + ")");
	        	
	        	

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
	         
	        	Scam.done = 0;
	        	
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