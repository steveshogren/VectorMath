package com.example.android.lasergame.tests;

import com.example.android.lasergame.Beam;

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
}
