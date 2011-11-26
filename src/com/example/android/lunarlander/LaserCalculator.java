package com.example.android.lunarlander;

public class LaserCalculator {

	private int mCanvasWidth;
	private int mCanvasHeight;
	private double mDesiredDegrees;
	private LineDrawer mLineDrawer;

	public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight) {
		mLineDrawer = lineDrawer;
		mCanvasWidth = canvasWidth;
		mCanvasHeight = canvasHeight;
	}

	public void fireLaser(double desiredDegrees) {
		if (desiredDegrees < 5) {
			desiredDegrees = 5;
		}
		mDesiredDegrees = desiredDegrees;

		int xStart = mCanvasWidth / 2;
		int yStart = mCanvasHeight;

		int xEnd = 0;
		int yEnd = 0;

		double degrees = Math.atan2(xStart, yStart) * 180.0F / Math.PI;
		double maxLeftSideDegrees = 180 - (degrees + 90);
		if (mDesiredDegrees < maxLeftSideDegrees) {
			yEnd = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
			headRightThenLeft(xStart, yStart, xEnd, yEnd);
		} else if (mDesiredDegrees < maxLeftSideDegrees + (180 - maxLeftSideDegrees * 2)) {
			// hitting back wall
		} else {
			mDesiredDegrees = 180 - (mDesiredDegrees + 90);
			headRightThenLeft(xStart, yStart, xStart, yStart);
		}
	}

	private void headRightThenLeft(float xStart, float yStart, float xEnd, float yEnd) {
		// -----Heading right ---
		xStart = xEnd;
		yStart = yEnd;
		yEnd = 0;

		double nextAngle = 180 - (mDesiredDegrees + 90);
		xEnd = (float) (Math.tan(Math.toRadians(nextAngle)) * yStart);
		if (xEnd < mCanvasWidth) { // hitting back wall
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
		} else { // bounce off right wall
			xEnd = mCanvasWidth;
			yEnd = yStart - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * mCanvasWidth);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);

			// -----Heading left ----
			xStart = xEnd;
			yStart = yEnd;
			xEnd = 0; // left wall
			yEnd = 0; // back wall

			yEnd = yStart - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			if (yEnd > 0) { // left wall
				mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
				headRightThenLeft(xStart, yStart, xEnd, yEnd);
			} else { // back wall
				yEnd = 0;
				xEnd = (float) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * yStart);
				mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
			}
		}
	}
}
