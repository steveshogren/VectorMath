package com.example.android.lasergame;

public class Point {

    int x;
    int y;

    public Point(int x1, int y1) {
        x = x1;
        y = y1;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object o) {
        if (o instanceof Point) {
            return ((Point) o).x == x && ((Point) o).y == y;
        }
        return false;
    }

    public double distanceTo(Line line) {
        Point A = line.p1;
        Point B = line.p2;
        double normalLength = Math.sqrt((B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y));
        return Math.abs((x - A.x) * (B.y - A.y) - (y - A.y) * (B.x - A.x)) / normalLength;
    }

    public double distanceTo(Point p) {
        double dx = p.x - x;
        double dy = p.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist;
    }

}
