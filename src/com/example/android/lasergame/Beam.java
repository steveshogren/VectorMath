package com.example.android.lasergame;

import java.util.ArrayList;
import java.util.List;

public class Beam {

    public final List<Line> lines;

    public Beam(Line firstLine) {
        lines = new ArrayList<Line>();
        lines.add(firstLine);
    }

    public Beam() {
        lines = new ArrayList<Line>();
    }

    public void addLine(Line l) {
        lines.add(l);
    }
    
    public List<Line> getLines() {
        return lines;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ (" + lines.get(0).p1.x + ", " + lines.get(0).p1.y + ")");
        for (Line l : lines) {
            // i figured, no need to show the hard-coded start point
            sb.append(" (" + l.p2.x + ", " + l.p2.y + ") "); 
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Beam) {
            boolean matches = false;
            Beam b2 = ((Beam) o);
            if (lines.size() != b2.lines.size()) {
                return false;
            }
            for (int i = 0; i < lines.size(); i++) {
               matches = lines.get(i).equals(b2.lines.get(i)); 
            }
            return matches;
        }
        return false;
    }
}