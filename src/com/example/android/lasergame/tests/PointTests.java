package com.example.android.lasergame.tests;

import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;

import junit.framework.TestCase;

public class PointTests extends TestCase {

    private Point p;

    public void setUp() {
        p = new Point(1, 1);
    }

    public void testDistanceToLine1() {
        assertEquals(1.0, p.distanceTo(new Line(new Point(2, 0), new Point(2, 2))));
    }

    public void testDistanceToLine2() {
        assertEquals(1.0, p.distanceTo(new Line(new Point(2, 0), new Point(2, 3))));
    }

    public void testDistanceToLine3() {
        assertEquals(1.414213562373095, p.distanceTo(new Line(new Point(3, 1), new Point(2, 2))));
    }

    public void testDistanceToPoint1() {
        assertEquals(1.0, p.distanceTo(new Point(2, 1)));
    }

    public void testDistanceToPoint2() {
        assertEquals(2.0, p.distanceTo(new Point(1, 3)));
    }
}
