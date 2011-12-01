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
	
	public void drawLine(Line beam, boolean firing) {
		if (firing) {
			mCanvas.drawLine(beam.p1.x, beam.p1.y, beam.p2.x, beam.p2.y, mPaint);
		} else {
			mCanvas.drawLine(beam.p1.x, beam.p1.y, beam.p2.x, beam.p2.y, mAimingPaint);
		}
	}
}
