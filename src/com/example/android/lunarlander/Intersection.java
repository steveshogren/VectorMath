package com.example.android.lunarlander;

public class Intersection {
	
	// Returns the intersection if one exists, null if not
	public static int[] detect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		int d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

		if (d == 0) return null;

		int xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
		int yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

		if (xi < 0 || yi < 0)  return null;
		
		int[] intersection = { xi, yi };
		return intersection;
	}
}