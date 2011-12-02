package com.example.android.lunarlander.tests;
import junit.framework.TestCase;
import com.example.android.lunarlander.*;

public class TriangleTests extends TestCase {
	
	private Triangle triangle;

	public void setUp() {
		triangle = new Triangle(new Point(0, 0), new Point(0, 1), new Point(1, 0));
	}
	
	public void testShouldNotIntersectWithALineThatDoesNotPassThroughIt() {
		assertFalse(triangle.intersectsWith(makeLine(1, 1, 1, 2)));
	}
	
	public void testShouldIntersectWithALineThatPassesThroughIt() {
		assertTrue(triangle.intersectsWith(makeLine(1, 1, 0, 0)));
	}

	private static Line makeLine(int x1, int y1, int x2, int y2) {
		return new Line(
				new Point(x1, y1),
				new Point(x2, y2)
		);
	}
}