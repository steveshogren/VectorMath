package com.example.android.lasergame.tests;

import com.example.android.lasergame.Beam;
import com.example.android.lasergame.LaserCalculator;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;

public class LaserCalculatorWallOnlyTests extends TestBase {
    public interface TriangleTests {}
    public interface WallTests {}
   
    private LaserCalculator mCalc;

    public void setUp() {
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0);
    }

    public void test45Degrees() {
        Beam b = mCalc.fireLaser(45);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 50)));
        e.addLine(new Line(new Point(1, 50), new Point(49, 1)));
        assertEquals(e, b);
    }

    public void test135Degrees() {
        Beam b = mCalc.fireLaser(135);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 50)));
        e.addLine(new Line(new Point(100, 50), new Point(51, 1)));
        assertEquals(e, b);
    }

    public void test35Degrees() {
        Beam b = mCalc.fireLaser(35);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 64)));
        e.addLine(new Line(new Point(1, 64), new Point(87, 1)));
        assertEquals(e, b);
    }

    public void test5Degrees() {
        Beam b = mCalc.fireLaser(5);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 95)));
        e.addLine(new Line(new Point(1, 95), new Point(100, 84)));
        e.addLine(new Line(new Point(100, 84), new Point(1, 73)));
        e.addLine(new Line(new Point(1, 73), new Point(100, 62)));
        e.addLine(new Line(new Point(100, 62), new Point(1, 51)));
        e.addLine(new Line(new Point(1, 51), new Point(100, 40)));
        e.addLine(new Line(new Point(100, 40), new Point(1, 29)));
        e.addLine(new Line(new Point(1, 29), new Point(100, 18)));
        e.addLine(new Line(new Point(100, 18), new Point(1, 7)));
        e.addLine(new Line(new Point(1, 7), new Point(60, 1)));
        assertEquals(e, b);
    }

    public void test4DegreesIsTreatedLikeFive() {
        Beam b = mCalc.fireLaser(4);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 95)));
        e.addLine(new Line(new Point(1, 95), new Point(100, 84)));
        e.addLine(new Line(new Point(100, 84), new Point(1, 73)));
        e.addLine(new Line(new Point(1, 73), new Point(100, 62)));
        e.addLine(new Line(new Point(100, 62), new Point(1, 51)));
        e.addLine(new Line(new Point(1, 51), new Point(100, 40)));
        e.addLine(new Line(new Point(100, 40), new Point(1, 29)));
        e.addLine(new Line(new Point(1, 29), new Point(100, 18)));
        e.addLine(new Line(new Point(100, 18), new Point(1, 7)));
        e.addLine(new Line(new Point(1, 7), new Point(60, 1)));
        assertEquals(e, b);
    }

    public void testLastHittingTheLeftWall() {
        Beam b = mCalc.fireLaser(63);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 1)));
        assertEquals(e, b);
    }

    public void testFirstHittingTheBackWall() {
        Beam b = mCalc.fireLaser(64);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(1, 1)));
        assertEquals(e, b);
    }

    public void testHittingTheBackWall() {
        Beam b = mCalc.fireLaser(90);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(50, 1)));
        assertEquals(e, b);
    }

    public void testLastHittingTheBackWall() {
        Beam b = mCalc.fireLaser(116);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(98, 1)));
        assertEquals(e, b);
    }

    public void testFirstHittingTheRightWall() {
        Beam b = mCalc.fireLaser(118);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 5)));
        e.addLine(new Line(new Point(100, 5), new Point(97, 1)));
        assertEquals(e, b);
    }

    public void test179DegreesIsTreatedLike175() {
        Beam b = mCalc.fireLaser(179);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 95)));
        e.addLine(new Line(new Point(100, 95), new Point(1, 85)));
        e.addLine(new Line(new Point(1, 85), new Point(100, 75)));
        e.addLine(new Line(new Point(100, 75), new Point(1, 65)));
        e.addLine(new Line(new Point(1, 65), new Point(100, 55)));
        e.addLine(new Line(new Point(100, 55), new Point(1, 45)));
        e.addLine(new Line(new Point(1, 45), new Point(100, 35)));
        e.addLine(new Line(new Point(100, 35), new Point(1, 25)));
        e.addLine(new Line(new Point(1, 25), new Point(100, 15)));
        e.addLine(new Line(new Point(100, 15), new Point(1, 5)));
        e.addLine(new Line(new Point(1, 5), new Point(40, 1)));
        assertEquals(e, b);
    }

    public void testAiming175Degrees() {
        Beam b = mCalc.fireLaser(175);
        Beam e = new Beam(new Line(new Point(50, 100), new Point(100, 95)));
        e.addLine(new Line(new Point(100, 95), new Point(1, 85)));
        e.addLine(new Line(new Point(1, 85), new Point(100, 75)));
        e.addLine(new Line(new Point(100, 75), new Point(1, 65)));
        e.addLine(new Line(new Point(1, 65), new Point(100, 55)));
        e.addLine(new Line(new Point(100, 55), new Point(1, 45)));
        e.addLine(new Line(new Point(1, 45), new Point(100, 35)));
        e.addLine(new Line(new Point(100, 35), new Point(1, 25)));
        e.addLine(new Line(new Point(1, 25), new Point(100, 15)));
        e.addLine(new Line(new Point(100, 15), new Point(1, 5)));
        e.addLine(new Line(new Point(1, 5), new Point(40, 1)));
        assertEquals(e, b);
    }
}
