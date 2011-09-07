package com.example.android.lunarlander;

import android.test.ActivityInstrumentationTestCase2;
import com.example.android.lunarlander.LunarLander;

/**
 * Make sure that the main launcher activity opens up properly, which will be
 * verified by {@link #testActivityTestCaseSetUpProperly}.
 */
public class LunarLanderTest2 extends ActivityInstrumentationTestCase2<LunarLander> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the {@link LunarLander} activity.
     */
    public LunarLanderTest() {
        super(LunarLander.class);
    }

    /**
     * Verifies that the activity under test can be launched.
     */
    public void testActivityTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }
}
