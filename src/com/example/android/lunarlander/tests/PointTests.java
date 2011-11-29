package com.example.android.lunarlander.tests;


import com.example.android.lunarlander.Line;
import com.example.android.lunarlander.Point;

import junit.framework.TestCase;

public class PointTests extends TestCase {
	
	private Point p;
	public void setUp() {
		p = new Point(1, 1);
	}
	public void test1() {
		assertEquals(1.0, p.distanceTo(new Line(new Point(2, 0), new Point(2, 2))));
	}

	public void test2() {
		assertEquals(1.0, p.distanceTo(new Line(new Point(2, 0), new Point(2, 3))));
	}
	public void test3() {
		assertEquals(1.414213562373095, p.distanceTo(new Line(new Point(3, 1), new Point(2, 2))));
	}
}
