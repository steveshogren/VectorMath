package com.example.android.lunarlander;

import java.io.PrintStream;
import java.io.PrintWriter;

public class LaserCalculator {

	private int mCanvasWidth;
	private int mCanvasHeight;
	private double mDesiredDegrees;
	private LineDrawer mLineDrawer;
	private double mMaxLeftSideDegrees;

	public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight) {
		mLineDrawer = lineDrawer;
		mCanvasWidth = canvasWidth;
		mCanvasHeight = canvasHeight;
	}
	public void fireLaser(double desiredDegrees) throws Exception {
		if (desiredDegrees < 5) {
			desiredDegrees = 5;
		}
		mDesiredDegrees = desiredDegrees;

		int xStart = mCanvasWidth / 2;
		int yStart = mCanvasHeight;

		int xEnd = 0;
		int yEnd = 0;

		mMaxLeftSideDegrees = getMaxLeftSideDegrees(xStart, yStart);
//		System.out.println("desiredDegrees: " + mDesiredDegrees +" maxleftlistdegress: "  + maxLeftSideDegrees);
		if (hittingLeftWall()) {
			yEnd = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
			headRightThenLeft(xStart, yStart, xEnd, yEnd);
		} else if (hittingBackWall()) {
			if (firingStraightUp()) {
				mLineDrawer.drawLine(xStart, yStart, xStart, 0);
	    		return;
			} else if (hittingLeftSideOfBackWall()) {
				xEnd = (int) (xStart - Math.tan(Math.toRadians(180-90-mDesiredDegrees)) * mCanvasHeight);
	    		mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
	    		return;
			} else { // hitting right side...
				xEnd = (int) (xStart + Math.tan(Math.toRadians(mDesiredDegrees-90)) * mCanvasHeight);
				System.out.println("desiredDegrees: " + mDesiredDegrees +" xEnd: "  + xEnd);
	    		mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
	    		return;
			}
		} else { // hitting right wall
//			yEnd = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
//			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd);
//			headRightThenLeft(xStart, yStart, xEnd, yEnd);
//			mDesiredDegrees = 180 - mDesiredDegrees;
			headRightThenLeft(xStart, yStart, xStart, yStart);
		}
	}
	private boolean hittingLeftSideOfBackWall() {
		return mDesiredDegrees < 90;
	}
	private boolean firingStraightUp() {
		return mDesiredDegrees == 90;
	}

	/**
	 *     x ------------>x
     *    ******************                                                   
     *  y *T*              *                                                   
     *  | *   *            *                                                   
     *  | *    *           *                                                   
     *  | *     *          *                                                   
     *  | *       *        *                                                   
     *  y *        M*      *                                                   
     *    ******************                                                   
	 * Returns the maximum number of degrees this map supports while still 
	 * hitting the left wall
	 */
	private double getMaxLeftSideDegrees(int xStart, int yStart) {
		double T = Math.atan2(xStart, yStart) * 180.0F / Math.PI;
		double M = 180 - (T + 90);
		return M;
	}

	private boolean hittingBackWall() {
		return mDesiredDegrees < mMaxLeftSideDegrees + (180 - mMaxLeftSideDegrees * 2);
	}

	private boolean hittingLeftWall() {
		return mDesiredDegrees < mMaxLeftSideDegrees;
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
