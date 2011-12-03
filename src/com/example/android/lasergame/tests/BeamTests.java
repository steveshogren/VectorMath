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

	public void testEquality() {
		Beam b1 = new Beam(makeLine(0, 0, 1, 1));
		Beam b2 = new Beam(makeLine(0, 0, 1, 1));
		
		b1.addLine(makeLine(2, 0, 1, 1));
		b2.addLine(makeLine(2, 0, 1, 1));
	
		b1.addLine(makeLine(3, 0, 1, 1));
		b2.addLine(makeLine(3, 0, 1, 1));

		assertEquals(b1, b2);
	}
}