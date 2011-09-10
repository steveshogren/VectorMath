package com.example.android.lunarlander;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class LaserCalculatorTests extends TestCase {

	public void test45Degrees() {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(45);
	
		verify(mockLine).drawLine(50, 100, 0, 50);
		verify(mockLine).drawLine(0, 50, 50, 0);
	}

	public void test4Degrees() {
		LineDrawer mockLine = mock(LineDrawer.class);
		LaserCalculator lCalc = new LaserCalculator(mockLine, 100, 100);
		
		lCalc.fireLaser(35);
	
		verify(mockLine).drawLine(50, 100, 0, 64);
		verify(mockLine).drawLine(0, 64, (float) 91.401474, 0);
	}
}
