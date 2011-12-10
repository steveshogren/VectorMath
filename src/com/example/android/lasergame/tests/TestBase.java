package com.example.android.lasergame.tests;

import java.util.List;

import com.example.android.lasergame.Beam;
import com.example.android.lasergame.Line;
import com.example.android.lasergame.Point;

import junit.framework.TestCase;

public abstract class TestBase extends TestCase {
 
    public void assertEquals(Beam b1, Beam b2) {
        String message = "\n" + b1.toString() 
                      + " \n" + b2.toString() + "\n";
        assertEquals(message, b1.lines.size(), b2.lines.size());
        for (int i = 0; i < b1.lines.size(); i++) {
            assertEquals(message, b1.lines.get(i), b2.lines.get(i));
        }
    }

    public static void addPoints(Beam b, List<Point> p) {
       for(int i = 0; i < p.size()-1; i++) {
           b.addLine(new Line(p.get(i), p.get(i+1)));
       }
    }
    
    
    public Point p(int x, int y) {
        return new Point(x, y);
    }
}
