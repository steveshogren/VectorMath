package com.example.android.lasergame;

import java.util.Comparator;

import com.example.android.lasergame.Intersection.Intersects;

public class PointComparator implements Comparator<Intersects> {
    private Point mStartingPoint;

    public PointComparator(Point startingPoint) {
        mStartingPoint = startingPoint;
    }

    public int compare(Intersects i1, Intersects i2) {
        if (i1.intersectionP.distanceTo(mStartingPoint) < i2.intersectionP.distanceTo(mStartingPoint)) {
            return -1;
        } else if (i1.intersectionP.distanceTo(mStartingPoint) > i2.intersectionP.distanceTo(mStartingPoint)) {
            return 1;
        }

        return 0;
    }
}
