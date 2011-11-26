package com.example.android.lunarlander;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RealLineDrawer implements LineDrawer {

	private Canvas mCanvas;
	private Paint mPaint;
	private Paint mAimingPaint;
	
	public RealLineDrawer(Canvas canvas, Paint paint, Paint aimingPaint) {
		mCanvas = canvas; 
		mPaint = paint; 
		mAimingPaint = aimingPaint; 
	}
	
	public void drawLine(float xStart, float yStart, float xEnd, float yEnd, boolean firing) {
		if (firing) {
			mCanvas.drawLine(xStart, yStart, xEnd, yEnd, mPaint);
		} else {
			mCanvas.drawLine(xStart, yStart, xEnd, yEnd, mAimingPaint);
		}
	}
}
