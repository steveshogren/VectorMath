package com.example.android.lasergame.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.android.lasergame.Intersection;
import com.example.android.lasergame.Intersection.Intersects;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;
import com.example.android.lasergame.PointComparator;

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
    
    public void testCheckingDistance() {
        List<Intersects> l =  new ArrayList<Intersects>(); 
        Intersection i =  new Intersection();
        l.add(i.new Intersects(new Point(30, 50), new Line(new Point(0, 0), new Point(0, 0))));
        Intersects closer = i.new Intersects(new Point(50, 50), new Line(new Point(0, 0), new Point(0, 0)));
        l.add(closer);
        Collections.sort(l, new PointComparator(new Point(50, 100)));
        assertEquals(l.get(0).intersectionP, closer.intersectionP);
    }
    public void testCheckingDistance2() {
        List<Intersects> l =  new ArrayList<Intersects>(); 
        Intersection i =  new Intersection();
        Intersects closer = i.new Intersects(new Point(50, 50), new Line(new Point(0, 0), new Point(0, 0)));
        l.add(closer);
        l.add(i.new Intersects(new Point(30, 50), new Line(new Point(0, 0), new Point(0, 0))));
        Collections.sort(l, new PointComparator(new Point(50, 100)));
        assertEquals(l.get(0).intersectionP, closer.intersectionP);
    }
     
}
