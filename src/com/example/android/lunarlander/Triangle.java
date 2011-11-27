package com.example.android.lunarlander;

public class Triangle {
	float[] point1;
	float[] point2;
	float[] point3;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		point1 = new float[2];
		point2 = new float[2];
		point3 = new float[2];
		
		point1[0] = (float) x1;
		point1[1] = (float) y1;
		point2[0] = (float) x2;
		point2[1] = (float) y2;
		point3[0] = (float) x3;
		point3[1] = (float) y3;
	}
}
