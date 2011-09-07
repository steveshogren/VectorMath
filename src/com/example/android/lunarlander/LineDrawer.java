package com.example.android.lunarlander;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineDrawer {

	private Canvas mCanvas;
	
	public LineDrawer(Canvas canvas) {
		mCanvas = canvas; 
	}
	
	public void drawLine(float xStart, float yStart, float xEnd, float yEnd, Paint paint) {
		mCanvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
	}
}
