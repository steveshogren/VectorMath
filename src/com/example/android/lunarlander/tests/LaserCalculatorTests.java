package com.example.android.lunarlander.tests;

import com.example.android.lunarlander.LaserCalculator;
import com.example.android.lunarlander.Line;
import com.example.android.lunarlander.LineDrawer;
import com.example.android.lunarlander.Point;
import com.example.android.lunarlander.Triangle;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class LaserCalculatorTests extends TestCase {
	private LineDrawer mMockLine;
	private LaserCalculator mCalc;

	public void setUp() {
		mMockLine = mock(LineDrawer.class);
		mCalc = new LaserCalculator(mMockLine, 100, 100);
	}

	public void test45Degrees() {
		mCalc.fireLaser(45, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 50)), true);
		verify(mMockLine).drawLine(new Line(new Point(0, 50), new Point(50, 0)), true);
	}

	public void test45DegreesWithTriangle() {
		mMockLine = mock(LineDrawer.class);
		Triangle[] t = {new Triangle(0, 0, 50, 0, 50, 0)};
		mCalc = new LaserCalculator(mMockLine, 100, 100, t);

		mCalc.fireLaser(65, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 50)), true);
	}
	public void test35Degrees() {
		mCalc.fireLaser(35, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 64)), true);
		verify(mMockLine).drawLine(new Line(new Point(0, 64), new Point(91, 0)), true);
	}

	public void test5Degrees() {
		mCalc.fireLaser(5, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 95)), true);
		verify(mMockLine).drawLine(new Line(new Point(0, 95), new Point(100, 86)), true);
	}

	public void test4DegreesIsTreatedLikeFive() {
		mCalc.fireLaser(4, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 95)), true);
		verify(mMockLine).drawLine(new Line(new Point(0, 95), new Point(100, 86)), true);
	}

	public void testLastHittingTheLeftWall() {
		mCalc.fireLaser(63, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(0, 1)), true);
	}

	public void testFirstHittingTheBackWall() {
		mCalc.fireLaser(64, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(1, 0)), true);
	}

	public void testHittingTheBackWall() {
		mCalc.fireLaser(90, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(50, 0)), true);
	}

	public void testLastHittingTheBackWall() {
		mCalc.fireLaser(116, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(98, 0)), true);
	}

	public void testFirstHittingTheRightWall() {
		mCalc.fireLaser(118, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(100, 5)), true);
	}

	public void test175Degrees() {
		mCalc.fireLaser(175, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(100, 95)), true);
		verify(mMockLine).drawLine(new Line(new Point(100, 95), new Point(0, 86)), true);
	}

	public void test179DegreesIsTreatedLike175() {
		mCalc.fireLaser(179, true);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(100, 95)), true);
		verify(mMockLine).drawLine(new Line(new Point(100, 95), new Point(0, 86)), true);
	}

	public void testAiming175Degrees() {
		mCalc.fireLaser(175, false);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(100, 95)), false);
	}

	public void testAiming179DegreesIsTreatedLike175() {
		mCalc.fireLaser(179, false);
		verify(mMockLine).drawLine(new Line(new Point(50, 100), new Point(100, 95)), false);
	}
}
