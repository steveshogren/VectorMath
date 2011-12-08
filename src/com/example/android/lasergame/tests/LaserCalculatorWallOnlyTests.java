package com.example.android.lasergame.tests;

import com.example.android.lasergame.Beam;
import com.example.android.lasergame.LaserCalculator;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;

import junit.framework.TestCase;
public class LaserCalculatorWallOnlyTests extends TestCase {
    public interface TriangleTests {}
    public interface WallTests {}
   
    private LaserCalculator mCalc;

    public void setUp() {
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0);
    }

    public void test45Degrees() {
        Beam b = mCalc.fireLaser(45);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(0, 50)));
        e.addLine(new Line(new Point(0, 50), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void test135Degrees() {
        Beam b = mCalc.fireLaser(135);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 50)));
        e.addLine(new Line(new Point(100, 50), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void test35Degrees() {
        Beam b = mCalc.fireLaser(35);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(0, 64)));
        e.addLine(new Line(new Point(0, 64), new Point(88, 0)));
        assertEquals(e, b);
    }

    public void test5Degrees() {
        Beam b = mCalc.fireLaser(5);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(0, 95)));
        e.addLine(new Line(new Point(0, 95), new Point(100, 85)));
        e.addLine(new Line(new Point(100, 85), new Point(0, 75)));
        e.addLine(new Line(new Point(0, 75), new Point(100, 65)));
        e.addLine(new Line(new Point(100, 65), new Point(0, 55)));
        e.addLine(new Line(new Point(0, 55), new Point(100, 45)));
        e.addLine(new Line(new Point(100, 45), new Point(0, 35)));
        e.addLine(new Line(new Point(0, 35), new Point(100, 25)));
        e.addLine(new Line(new Point(100, 25), new Point(0, 15)));
        e.addLine(new Line(new Point(0, 15), new Point(100, 5)));
        e.addLine(new Line(new Point(100, 5), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void test4DegreesIsTreatedLikeFive() {
        Beam b = mCalc.fireLaser(4);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(0, 95)));
        e.addLine(new Line(new Point(0, 95), new Point(100, 85)));
        e.addLine(new Line(new Point(100, 85), new Point(0, 75)));
        e.addLine(new Line(new Point(0, 75), new Point(100, 65)));
        e.addLine(new Line(new Point(100, 65), new Point(0, 55)));
        e.addLine(new Line(new Point(0, 55), new Point(100, 45)));
        e.addLine(new Line(new Point(100, 45), new Point(0, 35)));
        e.addLine(new Line(new Point(0, 35), new Point(100, 25)));
        e.addLine(new Line(new Point(100, 25), new Point(0, 15)));
        e.addLine(new Line(new Point(0, 15), new Point(100, 5)));
        e.addLine(new Line(new Point(100, 5), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void testLastHittingTheLeftWall() {
        Beam b = mCalc.fireLaser(63);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(0, 1)));
        e.addLine(new Line(new Point(0, 1), new Point(0, 0)));
        assertEquals(e, b);
    }

    public void testFirstHittingTheBackWall() {
        Beam b = mCalc.fireLaser(64);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 0)));
        assertEquals(e, b);
    }

    public void testHittingTheBackWall() {
        Beam b = mCalc.fireLaser(90);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void testLastHittingTheBackWall() {
        Beam b = mCalc.fireLaser(116);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(98, 0)));
        assertEquals(e, b);
    }

    public void testFirstHittingTheRightWall() {
        Beam b = mCalc.fireLaser(118);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 5)));
        e.addLine(new Line(new Point(100, 5), new Point(97, 0)));
        assertEquals(e, b);
    }

    public void test179DegreesIsTreatedLike175() {
        Beam b = mCalc.fireLaser(179);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 95)));
        e.addLine(new Line(new Point(100, 95), new Point(0, 85)));
        e.addLine(new Line(new Point(0, 85), new Point(100, 75)));
        e.addLine(new Line(new Point(100, 75), new Point(0, 65)));
        e.addLine(new Line(new Point(0, 65), new Point(100, 55)));
        e.addLine(new Line(new Point(100, 55), new Point(0, 45)));
        e.addLine(new Line(new Point(0, 45), new Point(100, 35)));
        e.addLine(new Line(new Point(100, 35), new Point(0, 25)));
        e.addLine(new Line(new Point(0, 25), new Point(100, 15)));
        e.addLine(new Line(new Point(100, 15), new Point(0, 5)));
        e.addLine(new Line(new Point(0, 5), new Point(50, 0)));
        assertEquals(e, b);
    }

    public void testAiming175Degrees() {
        Beam b = mCalc.fireLaser(175);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 95)));
        e.addLine(new Line(new Point(100, 95), new Point(0, 85)));
        e.addLine(new Line(new Point(0, 85), new Point(100, 75)));
        e.addLine(new Line(new Point(100, 75), new Point(0, 65)));
        e.addLine(new Line(new Point(0, 65), new Point(100, 55)));
        e.addLine(new Line(new Point(100, 55), new Point(0, 45)));
        e.addLine(new Line(new Point(0, 45), new Point(100, 35)));
        e.addLine(new Line(new Point(100, 35), new Point(0, 25)));
        e.addLine(new Line(new Point(0, 25), new Point(100, 15)));
        e.addLine(new Line(new Point(100, 15), new Point(0, 5)));
        e.addLine(new Line(new Point(0, 5), new Point(50, 0)));
        assertEquals(e, b);
    }
}
