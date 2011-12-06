package com.example.android.lasergame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.lasergame.LaserView.LaserThread;
import com.example.android.lasergame.R;

/**
 * This is a simple LunarLander activity that houses a single LunarView. It
 * demonstrates...
 * <ul>
 * <li>animating by calling invalidate() from draw()
 * <li>loading and drawing resources
 * <li>handling onPause() in an animation
 * </ul>
 */
public class LaserGame extends Activity {
    private static final int MENU_PAUSE = 4;
    private static final int MENU_RESUME = 5;
    private static final int MENU_START = 6;
    private static final int MENU_STOP = 7;

    /** A handle to the thread that's actually running the animation. */
    private LaserThread mLaserThread;

    /** A handle to the View in which the game is running. */
    private LaserView mLaserView;

    /**
     * Invoked during init to give the Activity a chance to set up its Menu.
     * 
     * @param menu
     *            the Menu to which entries may be added
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    /**
     * Invoked when the user selects an item from the Menu.
     * 
     * @param item
     *            the Menu entry which was selected
     * @return true if the Menu item was legit (and we consumed it), false
     *         otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MENU_START:
            mLaserThread.doStart();
            return true;
        case MENU_STOP:
            mLaserThread.setState(LaserThread.STATE_LOSE, getText(R.string.message_stopped));
            return true;
        case MENU_PAUSE:
            mLaserThread.pause();
            return true;
        case MENU_RESUME:
            mLaserThread.unpause();
            return true;
        }

        return false;
    }

    /**
     * Invoked when the Activity is created.
     * 
     * @param savedInstanceState
     *            a Bundle containing state saved from a previous execution, or
     *            null if this is a new execution
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tell system to use the layout defined in our XML file
        setContentView(R.layout.lunar_layout);

        // get handles to the LunarView from XML, and its LunarThread
        mLaserView = (LaserView) findViewById(R.id.lunar);
        mLaserThread = mLaserView.getThread();
        

        // give the LunarView a handle to the TextView used for messages
        mLaserView.setTextView((TextView) findViewById(R.id.text));
        mLaserView.setSeekBar((SeekBar) findViewById(R.id.seekBar1));
        mLaserView.setOnTouchListener(mLaserView);

        if (savedInstanceState == null) {
            // we were just launched: set up a new game
            mLaserThread.setState(LaserThread.STATE_READY);
            Log.w(this.getClass().getName(), "SIS is null");
        } else {
            // we are being restored: resume a previous game
            mLaserThread.restoreState(savedInstanceState);
            Log.w(this.getClass().getName(), "SIS is nonnull");
        }
    }

    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mLaserView.getThread().pause(); // pause game when Activity pauses
    }

    /**
     * Notification that something is about to happen, to give the Activity a
     * chance to save state.
     * 
     * @param outState
     *            a Bundle into which this Activity should save its state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        mLaserThread.saveState(outState);
        Log.w(this.getClass().getName(), "SIS called");
    }
}
