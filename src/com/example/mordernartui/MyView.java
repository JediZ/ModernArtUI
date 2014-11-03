package com.example.mordernartui;

import android.content.Context; 
import android.graphics.Canvas; 
import android.graphics.Color; 
import android.graphics.Paint; 
import android.graphics.Rect; 
import android.graphics.Paint.Style;
import android.util.AttributeSet; 
import android.view.View;

public class MyView extends View {
	  private Paint mPaint;
	  private int w;
	  private int h;
	  private int color1,color2,color3,color4,color5;
	  private Rect rect1,rect2,rect3,rect4,rect5;

	  public MyView(Context context,AttributeSet attr) 
	  { 
		  super(context,attr); 
		  init();
		  } 
	  
	  private void init(){
		  mPaint = new Paint();
		  color1 = Color.RED;
		  color2 = Color.BLUE;
		  color3 = Color.GREEN;
		  color4 = Color.WHITE;
		  color5 = Color.YELLOW;
		  rect1 = new Rect();
		  rect2 = new Rect();
		  rect3 = new Rect();
		  rect4 = new Rect();
		  rect5 = new Rect();
	  }

	  @Override
	  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        this.w = w;
	        this.h = h;
	        super.onSizeChanged(w, h, oldw, oldh);
	  }

	  @Override
	  protected void onDraw(Canvas canvas) { 
		  // TODO Auto-generated method stub 
		  super.onDraw(canvas); 
		  w = getWidth();
		  h = getHeight();
		  
		  rect1.set(10, 10, w/2-5, h/3-5);
		  rect2.set(10, h/3+5,w/2-5,h*2/3-5);
		  rect3.set(10, h*2/3+5, w/2-5, h-10);
		  rect4.set(w/2+5, 10, w-5, h/2-5);
		  rect5.set(w/2+5, h/2+5, w-5, h-10);

		  mPaint.setStyle(Style.FILL); 
		  canvas.drawColor(Color.BLACK);

		  // draw block 1
		  mPaint.setColor(color1);  
		  canvas.drawRect(rect1, mPaint); 
		  // draw block 2
		  mPaint.setColor(color2); 
		  canvas.drawRect(rect2, mPaint); 
		  // draw block 3
		  mPaint.setColor(color3); 
		  canvas.drawRect(rect3, mPaint); 
		  // draw block 4
		  mPaint.setColor(color4); 
		  canvas.drawRect(rect4, mPaint); 
		  // draw block 5
		  mPaint.setColor(color5); 
		  canvas.drawRect(rect5, mPaint); 
		  } 
	  
	  private int hueChange(int c,int deg){
	       float[] hsv = new float[3];       //array to store HSV values
	       Color.colorToHSV(c,hsv); //get original HSV values of pixel
	       hsv[0]=hsv[0]+deg;                //add the shift to the HUE of HSV array
	       hsv[0]=hsv[0]%360;                //confines hue to values:[0,360]
	       return Color.HSVToColor(Color.alpha(c),hsv);
	    }
	  
	  protected void changeColors(int delta){
		  color1 = hueChange(Color.RED,delta);
		  color2 = hueChange(Color.GREEN,delta);
		  color3 = hueChange(Color.BLUE,delta);
		  color4 = hueChange(Color.WHITE,0);
		  color5 = hueChange(Color.YELLOW,delta);
		  //DONE: send message to draw
		  this.invalidate();
	  }
}
