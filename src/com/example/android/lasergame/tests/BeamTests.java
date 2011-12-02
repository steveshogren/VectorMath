package com.example.android.lasergame.tests;
import java.util.*;
import com.example.android.lasergame.*;

public class BeamTests extends GeometryTestCase {
	
	private Beam beam;
	private Line firstLine;

	protected void setUp() throws Exception {
		super.setUp();
		firstLine = makeLine(0, 0, 1, 1);
		beam = new Beam(firstLine);
	}

	public void testAddLine_addOneLine() {
		Line nextLine = makeLine(1, 1, 0, 2);
		beam.addLine(nextLine);
	
		ArrayList<Line> expectedLines = new ArrayList<Line>();
		Collections.addAll(expectedLines, firstLine, nextLine); 
		
		assertEquals(
			expectedLines,
			beam.getLines()
		);
	}
}