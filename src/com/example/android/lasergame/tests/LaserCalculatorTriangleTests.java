package com.example.android.lasergame.tests;

import java.util.Arrays;

import com.example.android.lasergame.Beam;
import com.example.android.lasergame.LaserCalculator;
import com.example.android.lasergame.Point;
import com.example.android.lasergame.Triangle;

public class LaserCalculatorTriangleTests extends TestBase {
    private LaserCalculator mCalc;

    public void setUp() {
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0);
    }
    
    public void testUsingTheEmulatorBoard118() {
        Triangle[] t = { new Triangle(new Point(1, 80), new Point(320/2, 508/2), new Point(320, 80))};
        mCalc = new LaserCalculator(320, 508, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(118);
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(160, 508), p(320, 207), p(277, 126), p(320, 144), p(224, 183), p(122, 508)));
        assertEquals(e, b);
    }

    public void testUsingTheEmulatorBoard47() {
        Triangle[] t = { new Triangle(new Point(1, 80), new Point(320/2, 508/2), new Point(320, 80))};
        mCalc = new LaserCalculator(320, 508, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(47);
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(160, 508), p(1, 336), p(118, 208), p(1, 297), p(279, 508)));
        assertEquals(e, b);
    }

    public void testUsingTheEmulatorBoard97() {
        Triangle[] t = { new Triangle(new Point(1, 80), new Point(320/2, 508/2), new Point(320, 80))};
        mCalc = new LaserCalculator(320, 508, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(97);
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(160, 508), p(195, 215), p(320, 189), p(235, 171), p(320, 453), p(303, 508)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt45Degrees() {
        Triangle[] t = { new Triangle(new Point(1, 30), new Point(70, 30), new Point(1, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(91);
        
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(50, 100), p(50, 49), p(100, 48), p(53, 47), p(54, 100)));
        assertEquals(e, b);
    }
    
    
    public void testReflectingOffAndKeepGoing() {
        Triangle[] t = { new Triangle(new Point(1, 30), new Point(70, 30), new Point(1, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(92);
        
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(50, 100), p(51, 48), p(100, 46), p(56, 43), p(60, 100)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt45DegreesToLeft() {
        Triangle[] t = { new Triangle(new Point(100, 30), new Point(30, 30), new Point(100, 100)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(91);
        
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(50, 100), p(50, 50), p(1, 50)));
        assertEquals(e, b);
    }

    public void testReflectingOffAt60Degrees() {
        Triangle[] t = { new Triangle(new Point(1, 70), new Point(70, 1), new Point(1, 1)) };
        mCalc = new LaserCalculator(100, 100, 5.0, 175.0, t);

        Beam b = mCalc.fireLaser(60);
        
        Beam e = new Beam();
        addPoints(e, Arrays.asList(p(50, 100), p(21, 49), p(100, 93), p(87, 100)));
        assertEquals(e, b);
    }

}
