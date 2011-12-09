package com.example.android.lasergame.tests;

import com.example.android.lasergame.Beam;
import com.example.android.lasergame.LaserCalculator;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;
import com.example.android.lasergame.Triangle;

import junit.framework.TestCase;
public class LaserCalculatorTriangleTests extends TestCase {
    private LaserCalculator mCalc;

    public void setUp() {
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0);
    }
    
    public void testUsingTheEmulatorBoard118() {
        Triangle[] t = { new Triangle(new Point(1, 80), new Point(320/2, 508/2), new Point(320, 80))};
        mCalc = new LaserCalculator(320, 508, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(118);
        Beam e = new Beam();
        e.addLine(new Line(new Point(160, 508), new Point(320, 207)));
        e.addLine(new Line(new Point(320, 207), new Point(276, 127)));
        e.addLine(new Line(new Point(276, 127), new Point(320, 146)));
        e.addLine(new Line(new Point(320, 146), new Point(220, 188)));
        e.addLine(new Line(new Point(220, 188), new Point(0, 865)));
        assertEquals(e, b);
    }

    public void testUsingTheEmulatorBoard47() {
        Triangle[] t = { new Triangle(new Point(1, 80), new Point(320/2, 508/2), new Point(320, 80))};
        mCalc = new LaserCalculator(320, 508, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(47);
        Beam e = new Beam();
        e.addLine(new Line(new Point(160, 508), new Point(0, 336)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt45Degrees() {
        Triangle[] t = { new Triangle(new Point(1, 30), new Point(70, 30), new Point(1, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(91);
        Beam e = new Beam();
        e.addLine(new Line(new Point(50, 100), new Point(50, 49)));
        e.addLine(new Line(new Point(50, 49), new Point(100, 48)));
        e.addLine(new Line(new Point(100, 48), new Point(53, 47)));
        e.addLine(new Line(new Point(53, 47), new Point(51, 100)));
        assertEquals(e, b);
    }
    
    
    public void testReflectingOffAndKeepGoing() {
        Triangle[] t = { new Triangle(new Point(1, 30), new Point(70, 30), new Point(1, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(92);
        Beam e = new Beam();
        e.addLine(new Line(new Point(50, 100), new Point(51, 48)));
        e.addLine(new Line(new Point(51, 48), new Point(100, 46)));
        e.addLine(new Line(new Point(100, 46), new Point(57, 43)));
        e.addLine(new Line(new Point(57, 43), new Point(53, 100)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt45DegreesToLeft() {
        Triangle[] t = { new Triangle(new Point(100, 30), new Point(30, 30), new Point(100, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(91);
        Beam e = new Beam();
        e.addLine(new Line(new Point(50, 100), new Point(50, 50)));
        e.addLine(new Line(new Point(50, 50), new Point(0, 50)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt60Degrees() {
        Triangle[] t = { new Triangle(new Point(1, 70), new Point(70, 1), new Point(1, 1)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(60);
        Beam e = new Beam();
        e.addLine(new Line(new Point(50,100), new Point(21, 49)));
        e.addLine(new Line(new Point(21, 49), new Point(100, 93)));
        assertEquals(e, b);
    }

}
