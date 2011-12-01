package com.example.android.lunarlander;
import java.util.*;

public class Triangle {
	private Point p1;
	private Point p2;
	private Point p3;
	
	private Line edge1;
	private Line edge2;
	private Line edge3;
	
	public Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p1 = p2;
		this.p1 = p3;
		
		edge1 = new Line(p1, p2);
		edge2 = new Line(p2, p3);
		edge3 = new Line(p3, p1);
	}
	
	public Float[][] pointCoordinates() {
		return new Float[][] {
				{(float)p1.x, (float)p1.y},
				{(float)p2.x, (float)p2.y},
				{(float)p3.x, (float)p3.y},
		};
	}
	
	public List<Line> edges() {
		List<Line> edges = new ArrayList<Line>();
		Collections.addAll(edges, edge1, edge2, edge3);
		return edges;
	}
	
	public boolean intersectsWith(Line line) {
		for (Line edge : edges()) {
			Point intersection = Intersection.detect(line, edge);
			
			if (intersection != null) {
				return true;
			}
		}
		return false;
	}
}