package com.example.android.lasergame.tests;

import com.example.android.lasergame.Intersection;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;

import junit.framework.TestCase;

public class IntersectionTests extends TestCase {
    public void test1() {
        Point a = Intersection.detect(new Line(new Point(10, 0), new Point(7, 3)), new Line(new Point(6, 0), new Point(
                12, 4)));

        Point e = new Point(8, 1);
        assertEquals(e, a);
    }

    public void test2() {
        Point a = Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(0, 0), new Point(
                3, 3)));

        Point e = new Point(1, 1);
        assertEquals(e, a);
    }

    public void testFirstLineVertical() {
        Point a = Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(2, 0), new Point(
                2, 2)));

        Point e = new Point(2, 1);
        assertEquals(e, a);
    }

    public void testSecondLineVertical() {
        Point a = Intersection.detect(new Line(new Point(2, 0), new Point(2, 2)), new Line(new Point(0, 3), new Point(
                3, 0)));

        Point e = new Point(2, 1);
        assertEquals(e, a);
    }

    public void testSecondLineHorizontal() {
        Point a = Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(0, 2), new Point(
                2, 2)));

        Point e = new Point(1, 2);
        assertEquals(e, a);
    }

    public void testFirstLineHorizontal() {
        Point a = Intersection.detect(new Line(new Point(0, 2), new Point(2, 2)), new Line(new Point(0, 3), new Point(
                3, 0)));

        Point e = new Point(1, 2);
        assertEquals(e, a);
    }

    public void testNoIntersect() {
        assertNull(Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(2, 0), new Point(
                0, 2))));
    }

    public void testNoIntersect1() {
        assertNull(Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(4, 0), new Point(
                0, 5))));
    }

    public void testNoIntersect2() {
        assertNull(Intersection.detect(new Line(new Point(0, 3), new Point(3, 0)), new Line(new Point(1, 4), new Point(
                3, 4))));
    }

    public void testNoIntersect3() {
        Point point = Intersection.detect(new Line(new Point(1, 1), new Point(3, 1)), new Line(new Point(4, 2),
                new Point(4, 4)));
        assertNull("point: " + point, point);
    }
}
