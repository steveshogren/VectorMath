package com.example.android.lunarlander;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RealLineDrawer implements LineDrawer {

	private Canvas mCanvas;
	private Paint mPaint;
	
	public RealLineDrawer(Canvas canvas, Paint paint) {
		mCanvas = canvas; 
		mPaint = paint; 
	}
	
	public void drawLine(float xStart, float yStart, float xEnd, float yEnd) {
		mCanvas.drawLine(xStart, yStart, xEnd, yEnd, mPaint);
	}
}
