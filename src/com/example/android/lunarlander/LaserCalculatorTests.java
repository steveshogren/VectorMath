package com.example.android.lunarlander;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class LaserCalculatorTests extends TestCase {
	public void test45Degrees() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(45);
	
		verify(mockLine).drawLine(50, 100, 0, 50);
		verify(mockLine).drawLine(0, 50, 50, 0);
	}

	public void test35Degrees() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(35);
	
		verify(mockLine).drawLine(50, 100, 0, 64);
		verify(mockLine).drawLine(0, 64, (float) 91.401474, 0);
	}
	
	public void test5Degrees() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(5);
	
		verify(mockLine).drawLine(50, 100, 0, 95);
		verify(mockLine).drawLine(0, 95, (float) 100, (float) 86.25114);
	}
	
	public void test4DegreesIsTreatedLikeFive() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(4);
	
		verify(mockLine).drawLine(50, 100, 0, 95);
		verify(mockLine).drawLine(0, 95, (float) 100, (float) 86.25114);
	}
//	public void test1804DegreesIsTreatedLike179() {
//		LineDrawer mockLine = mock(LineDrawer.class);
//		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
//		
//		lCalc.fireLaser(179);
//	
//		verify(mockLine).drawLine(50, 100, 0, 95);
//		verify(mockLine).drawLine(0, 95, (float) 100, (float) 86.25114);
//	}
	public void testLastHittingTheLeftWall() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(63);
	
		verify(mockLine).drawLine(50, 100, 0, 1);
	}
	public void testFirstHittingTheBackWall() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(64);
	
		verify(mockLine).drawLine(50, 100, 1, 0);
	}
	public void testHittingTheBackWall() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(90);
	
		verify(mockLine).drawLine(50, 100, 50, 0);
	}
	public void testLastHittingTheBackWall() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(116);
	
		verify(mockLine).drawLine(50, 100, 98, 0);
	}
	public void testFirstHittingTheRightWall() throws Exception {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(118);
	
		verify(mockLine).drawLine(50, 100, 100, 0);
	}
}
