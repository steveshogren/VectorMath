package com.example.android.lasergame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RealDrawer implements Drawer {

    private Canvas mCanvas;
    private Paint mPaint;
    private Paint mAimingPaint;

    public RealDrawer(Canvas canvas, Paint paint, Paint aimingPaint) {
        mCanvas = canvas;
        mPaint = paint;
        mAimingPaint = aimingPaint;
    }

    public void draw(Beam beam, boolean firing) {
        for (Line l : beam.lines) {
            if (firing) {
                mCanvas.drawLine(l.p1.x, l.p1.y, l.p2.x, l.p2.y, mPaint);
            } else {
                mCanvas.drawLine(l.p1.x, l.p1.y, l.p2.x, l.p2.y, mAimingPaint);
                return;
            }
        }
    }
}
