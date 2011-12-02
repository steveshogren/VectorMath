package com.example.android.lasergame;

import java.util.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle {
    public final Point p1;
    public final Point p2;
    public final Point p3;

    private Line edge1;
    private Line edge2;
    private Line edge3;

    public Triangle(Point pP1, Point pP2, Point pP3) {
        this.p1 = pP1;
        this.p2 = pP2;
        this.p3 = pP3;

        edge1 = new Line(p1, p2);
        edge2 = new Line(p2, p3);
        edge3 = new Line(p3, p1);
    }

    public Float[][] pointCoordinates() {
        return new Float[][] { { (float) p1.x, (float) p1.y }, { (float) p2.x, (float) p2.y },
                { (float) p3.x, (float) p3.y }, };
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

    public void draw(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.lineTo(p1.x, p1.y);
        path.close();

        canvas.drawPath(path, paint);
    }
}