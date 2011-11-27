package com.example.android.lunarlander;

import junit.framework.TestCase;

public class IntersectionTests extends TestCase {
	public void test1() {
		int[] a = Intersection.detect(10, 0, 7, 3, 6, 0, 12, 4);
		int[] e = {8,1};
		assertEquals(e[0], a[0]);
		assertEquals(e[1], a[1]);
	}
	public void test2() {
		int[] a = Intersection.detect(0, 3, 3, 0, 0, 0, 3, 3);
		int[] e = {1,1};
		assertEquals("x coord", e[0], a[0]);
		assertEquals("y coord", e[1], a[1]);
	}
	public void test3() {
		int[] a = Intersection.detect(0, 3, 3, 0, 2, 0, 2, 2);
		int[] e = {2,1};
		assertEquals("x coord", e[0], a[0]);
		assertEquals("y coord", e[1], a[1]);
	}
	public void testNoIntersect() {
		assertNull(Intersection.detect(0, 3, 3, 0, 2, 0, 0, 2));
	}
	public void testNoIntersect1() {
		assertNull(Intersection.detect(0, 3, 3, 0, 4, 0, 0, 5));
	}
}
