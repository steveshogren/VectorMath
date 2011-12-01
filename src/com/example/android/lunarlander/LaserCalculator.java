package com.example.android.lunarlander;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		Line beam = new Line(new Point(mCanvasWidth/2, mCanvasHeight), new Point(0,0));

		mMaxLeftSideDegrees = getMaxLeftSideDegrees(beam.p1);
		if (hittingLeftWall()) {
			beam.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * beam.p1.x);
			if (! tryToReflect(beam, firing)) {
				mLineDrawer.drawLine(beam, firing);
				if (! firing) return; 
				bounceRightThenLeft(beam);
			}
		} else if (hittingBackWall()) {
			if (firingStraightUp()) {
				beam.p2.y = 0;
				beam.p2.x = beam.p1.x;
				mLineDrawer.drawLine(beam, firing);
	    		return;
			} else if (hittingLeftSideOfBackWall()) {
				beam.p2.x = (int) (beam.p1.x - Math.tan(Math.toRadians(180-90-mDesiredDegrees)) * mCanvasHeight);
				if (! tryToReflect(beam, firing)) {
					mLineDrawer.drawLine(beam, firing);
					return;
				}
			} else { // hitting right side...
				beam.p2.x = (int) (beam.p1.x + Math.tan(Math.toRadians(mDesiredDegrees-90)) * mCanvasHeight);
				if (! tryToReflect(beam, firing)) {
					mLineDrawer.drawLine(beam, firing);
					return;
				}
			}
		} else { // hitting right wall
			beam.p2.x = mCanvasWidth;
			mDesiredDegrees = 180 - mDesiredDegrees;
			beam.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * beam.p1.x);
			if (! tryToReflect(beam, firing)) {
				mLineDrawer.drawLine(beam, firing);
				if (! firing) return; 
				bounceLeftThenRight(beam);
			}
		}
	}
	
	private boolean tryToReflect(Line beam, boolean firing) {
		for (Triangle triangle : mTriangles) {
			Float[][] t = triangle.pointCoordinates();
			Float[] point1 = t[0];
			Float[] point2 = t[1];
			Float[] point3 = t[2];
			
			Point i1 = Intersection.detect(beam, new Line(new Point(point1[0].intValue(),
					 point1[1].intValue()), new Point(point2[0].intValue(), point2[1].intValue())));
			Point i2 = Intersection.detect(beam, new Line(new Point(point2[0].intValue(),
					 point2[1].intValue()), new Point(point3[0].intValue(), point3[1].intValue())));
			Point i3 = Intersection.detect(beam, new Line(new Point( point3[0].intValue(),
					 point3[1].intValue()), new Point(point2[0].intValue(), point2[1].intValue())));

			List<Point> p = new ArrayList<Point>();
			if (i1 != null) {
				p.add(i1);
			} else if (i2 != null) {
				p.add(i2);
			} else if (i3 != null) {
				p.add(i3);
			}
			if (!p.isEmpty()) {
				Collections.sort(p, new PointComparator(beam.p1));
				beam.p2.x = p.get(0).x;
				beam.p2.y = p.get(0).y;
				mLineDrawer.drawLine(beam, firing);
				return true;
			}
		}
		return false;
	}
	private void bounceLeftThenRight(Line beam) {
		double nextAngle = 180 - (mDesiredDegrees + 90);
		// -----Heading left ----
		beam.p1.x = beam.p2.x;
		beam.p1.y = beam.p2.y;
		beam.p2.x = 0; // left wall
		beam.p2.y = 0; // back wall

		beam.p2.y = (int)( beam.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * beam.p1.x));
		if (beam.p2.y > 0) { // left wall
			mLineDrawer.drawLine(beam, true);
			bounceRightThenLeft(beam);
		} else { // back wall
			beam.p2.y = 0;
			beam.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * beam.p1.y);
			mLineDrawer.drawLine(beam, true);
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
	private double getMaxLeftSideDegrees(Point startPoint) {
		double T = Math.atan2(startPoint.x, startPoint.y) * 180.0F / Math.PI;
		double M = 180 - (T + 90);
		return M;
	}

	private boolean hittingBackWall() {
		return mDesiredDegrees < mMaxLeftSideDegrees + (180 - mMaxLeftSideDegrees * 2);
	}

	private boolean hittingLeftWall() {
		return mDesiredDegrees < mMaxLeftSideDegrees;
	}

	private void bounceRightThenLeft(Line beam) {
		// -----Heading right ---
		beam.p1.x = beam.p2.x;
		beam.p1.y = beam.p2.y;
		beam.p2.y = 0;

		double nextAngle = 180 - (mDesiredDegrees + 90);
		beam.p2.x = (int) (Math.tan(Math.toRadians(nextAngle)) * beam.p1.y);
		if (beam.p2.x < mCanvasWidth) { // hitting back wall
			mLineDrawer.drawLine(beam, true);
		} else { // bounce off right wall
			beam.p2.x = mCanvasWidth;
			beam.p2.y = (int)(beam.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * mCanvasWidth));
			mLineDrawer.drawLine(beam, true);

			// -----Heading left ----
			beam.p1.x = beam.p2.x;
			beam.p1.y = beam.p2.y;
			beam.p2.x = 0; // left wall
			beam.p2.y = 0; // back wall

			beam.p2.y = (int) (beam.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * beam.p1.x));
			if (beam.p2.y > 0) { // left wall
				mLineDrawer.drawLine(beam, true);
				bounceRightThenLeft(beam);
			} else { // back wall
				beam.p2.y = 0;
				beam.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * beam.p1.y);
				mLineDrawer.drawLine(beam, true);
			}
		}
	}
}
