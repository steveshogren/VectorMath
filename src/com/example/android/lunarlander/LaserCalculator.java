package com.example.android.lunarlander;

public class LaserCalculator {

	private int mCanvasWidth;
	private int mCanvasHeight;
	private double mDesiredDegrees;
	private LineDrawer mLineDrawer;
	private double mMaxLeftSideDegrees;
	private Triangle[] mTriangles;

	public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight) {
		mLineDrawer = lineDrawer;
		mCanvasWidth = canvasWidth;
		mCanvasHeight = canvasHeight;
		mTriangles = new Triangle[] {};
	}
	public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight, Triangle[] triangles) {
		mLineDrawer = lineDrawer;
		mTriangles = triangles;
		mCanvasWidth = canvasWidth;
		mCanvasHeight = canvasHeight;
	}
	public void fireLaser(double desiredDegrees, boolean firing) {
		mDesiredDegrees = desiredDegrees;
		if (mDesiredDegrees < 5) {
			mDesiredDegrees = 5;
		}

		if (mDesiredDegrees > 175) {
			mDesiredDegrees = 175;
		}
		float xStart = mCanvasWidth / 2;
		float yStart = mCanvasHeight;
		float xEnd = 0;
		float yEnd = 0;

		mMaxLeftSideDegrees = getMaxLeftSideDegrees(xStart, yStart);
		if (hittingLeftWall()) {
			yEnd = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			for (Triangle t : mTriangles) {
				int[] i1 = Intersection.detect((int) xStart, (int) yStart, (int) xEnd, (int) yEnd, (int) t.point1[0], (int) t.point1[1],(int)  t.point2[0],(int)  t.point2[1]);
				int[] i2 = Intersection.detect((int) xStart, (int) yStart, (int) xEnd, (int) yEnd, (int) t.point2[0], (int) t.point2[1], (int) t.point3[0], (int) t.point3[1]);
				int[] i3 = Intersection.detect((int) xStart, (int) yStart, (int) xEnd, (int) yEnd, (int) t.point3[0],(int)  t.point3[1], (int) t.point1[0], (int) t.point1[1]);
				if (i1 != null) {
					// something something find the closest
					// INTERSECTION!
					mLineDrawer.drawLine(xStart, yStart, i1[0], i1[1], firing);
					return;
				} else if (i2 != null) {
					mLineDrawer.drawLine(xStart, yStart, i2[0], i2[1], firing);
					return;
				} else if (i3 != null) {
					mLineDrawer.drawLine(xStart, yStart, i3[0], i3[1], firing);
					return;
				}
			}
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, firing);
			if (! firing) return; 
			bounceRightThenLeft(xStart, yStart, xEnd, yEnd);
		} else if (hittingBackWall()) {
			if (firingStraightUp()) {
				mLineDrawer.drawLine(xStart, yStart, xStart, 0, firing);
	    		return;
			} else if (hittingLeftSideOfBackWall()) {
				xEnd = (int) (xStart - Math.tan(Math.toRadians(180-90-mDesiredDegrees)) * mCanvasHeight);
	    		mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, firing);
	    		return;
			} else { // hitting right side...
				xEnd = (int) (xStart + Math.tan(Math.toRadians(mDesiredDegrees-90)) * mCanvasHeight);
	    		mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, firing);
	    		return;
			}
		} else { // hitting right wall
			xEnd = mCanvasWidth;
			mDesiredDegrees = 180 - mDesiredDegrees;
			yEnd = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, firing);
			if (! firing) return; 
			bounceLeftThenRight(xEnd, yEnd);
		}
	}
	
	private void bounceLeftThenRight(float xEnd, float yEnd) {
		float xStart;
		float yStart;
		double nextAngle = 180 - (mDesiredDegrees + 90);
		// -----Heading left ----
		xStart = xEnd;
		yStart = yEnd;
		xEnd = 0; // left wall
		yEnd = 0; // back wall

		yEnd = yStart - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
		if (yEnd > 0) { // left wall
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);
			bounceRightThenLeft(xStart, yStart, xEnd, yEnd);
		} else { // back wall
			yEnd = 0;
			xEnd = (float) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * yStart);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);
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
	private double getMaxLeftSideDegrees(float xStart, float yStart) {
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

	private void bounceRightThenLeft(float xStart, float yStart, float xEnd, float yEnd) {
		// -----Heading right ---
		xStart = xEnd;
		yStart = yEnd;
		yEnd = 0;

		double nextAngle = 180 - (mDesiredDegrees + 90);
		xEnd = (float) (Math.tan(Math.toRadians(nextAngle)) * yStart);
		if (xEnd < mCanvasWidth) { // hitting back wall
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);
		} else { // bounce off right wall
			xEnd = mCanvasWidth;
			yEnd = yStart - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * mCanvasWidth);
			mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);

			// -----Heading left ----
			xStart = xEnd;
			yStart = yEnd;
			xEnd = 0; // left wall
			yEnd = 0; // back wall

			yEnd = yStart - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * xStart);
			if (yEnd > 0) { // left wall
				mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);
				bounceRightThenLeft(xStart, yStart, xEnd, yEnd);
			} else { // back wall
				yEnd = 0;
				xEnd = (float) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * yStart);
				mLineDrawer.drawLine(xStart, yStart, xEnd, yEnd, true);
			}
		}
	}
}
