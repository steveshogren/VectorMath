package com.example.android.lunarlander.tests;

import com.example.android.lunarlander.LaserCalculator;
import com.example.android.lunarlander.LineDrawer;

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
		verify(mMockLine).drawLine(50, 100, 0, 50, true);
		verify(mMockLine).drawLine(0, 50, 50, 0, true);
	}

	public void test35Degrees() {
		mCalc.fireLaser(35, true);
		verify(mMockLine).drawLine(50, 100, 0, 64, true);
		verify(mMockLine).drawLine(0, 64, (float) 91.401474, 0, true);
	}
	
	public void test5Degrees() {
		mCalc.fireLaser(5, true);
		verify(mMockLine).drawLine(50, 100, 0, 95, true);
		verify(mMockLine).drawLine(0, 95, (float) 100, (float) 86.25114, true);
	}
	
	public void test4DegreesIsTreatedLikeFive() {
		mCalc.fireLaser(4, true);
		verify(mMockLine).drawLine(50, 100, 0, 95, true);
		verify(mMockLine).drawLine(0, 95, (float) 100, (float) 86.25114, true);
	}
	public void testLastHittingTheLeftWall() {
		mCalc.fireLaser(63, true);
		verify(mMockLine).drawLine(50, 100, 0, 1, true);
	}
	
	public void testFirstHittingTheBackWall() {
		mCalc.fireLaser(64, true);
		verify(mMockLine).drawLine(50, 100, 1, 0, true);
	}
	
	public void testHittingTheBackWall() {
		mCalc.fireLaser(90, true);
		verify(mMockLine).drawLine(50, 100, 50, 0, true);
	}
	
	public void testLastHittingTheBackWall() {
		mCalc.fireLaser(116, true);
		verify(mMockLine).drawLine(50, 100, 98, 0, true);
	}
	
	public void testFirstHittingTheRightWall() {
		mCalc.fireLaser(118, true);
		verify(mMockLine).drawLine(50, 100, 100, 5, true);
	}
	public void test175Degrees() {
		mCalc.fireLaser(175, true);
		verify(mMockLine).drawLine(50, 100, 100, 95, true);
		verify(mMockLine).drawLine(100, 95, (float) 0, (float) 86.25114, true);
	}
	public void test179DegreesIsTreatedLike175() {
		mCalc.fireLaser(179, true);
		verify(mMockLine).drawLine(50, 100, 100, 95, true);
		verify(mMockLine).drawLine(100, 95, (float) 0, (float) 86.25114, true);
	}
	public void testAiming175Degrees() {
		mCalc.fireLaser(175, false);
		verify(mMockLine).drawLine(50, 100, 100, 95, false);
	}
	public void testAiming179DegreesIsTreatedLike175() {
		mCalc.fireLaser(179, false);
		verify(mMockLine).drawLine(50, 100, 100, 95, false);
	}
}
