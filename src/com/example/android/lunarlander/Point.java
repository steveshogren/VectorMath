package com.example.android.lunarlander;

public class Point {
	
	int x;
	int y;

	public Point(int x1, int y1) {
		x = x1;
		y = y1;
	}
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	public boolean equals(Object o){
		if (o instanceof Point) {
			return ((Point) o).x == x && ((Point) o).y == y;
		}
		return false;
	}
}
