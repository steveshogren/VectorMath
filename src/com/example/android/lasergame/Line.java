package com.example.android.lasergame;

public class Line {
    Point p1;
    Point p2;

    public Line(Point pA, Point pB) {
        p1 = pA;
        p2 = pB;
    }

    public String toString() {
        return "{(" + p1.x + ", " + p1.y + ")(" + p2.x + ", " + p2.y + ")}";
    }

    public boolean equals(Object o) {
        if (o instanceof Line) {
            return ((Line) o).p1.x == p1.x && ((Line) o).p1.y == p1.y && ((Line) o).p2.x == p2.x
                    && ((Line) o).p2.y == p2.y;
        }
        return false;
    }
}
