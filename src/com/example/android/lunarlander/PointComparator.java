package com.example.android.lunarlander;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
		private Point mStartingPoint;

		public PointComparator(Point startingPoint) {
			mStartingPoint = startingPoint;
		}
		
	    public int compare(Point p1, Point p2) {
	        if ( p1.distanceTo(mStartingPoint) > p2.distanceTo(mStartingPoint) ) {
	            return -1;
	        } else if ( p1.distanceTo(mStartingPoint) < p2.distanceTo(mStartingPoint) ) {
	            return 1;
	        }

	        return 0;
	    }
}
