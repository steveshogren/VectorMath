package com.example.android.lasergame;

import java.util.ArrayList;
import java.util.List;

public class Beam {

    public final List<Line> lines;
   
    public Beam(Line l){
        lines = new ArrayList<Line>();
        lines.add(l);
    }
   
    public void addLine(Line l) {
        lines.add(l);
    }
    
    public List<Line> getLines() {
    	return lines;
    }
}