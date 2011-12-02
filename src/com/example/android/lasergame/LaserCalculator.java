package com.example.android.lasergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaserCalculator {

    private int mCanvasWidth;
    private int mCanvasHeight;
    private double mDesiredDegrees;
    private LineDrawer mLineDrawer;
    private double mMaxLeftSideDegrees;
    private Triangle[] mTriangles;
    private Beam mBeam;
    
    public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight) {
        mLineDrawer = lineDrawer;
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
        mTriangles = new Triangle[] {};
    }

    public LaserCalculator(LineDrawer lineDrawer, int canvasWidth, int canvasHeight, Triangle[] triangles) {
        mLineDrawer = lineDrawer;
        mTriangles = triangles;
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
    }

    public void fireLaser(double desiredDegrees, boolean firing) {
        mDesiredDegrees = desiredDegrees;
        if (mDesiredDegrees < 5) {
            mDesiredDegrees = 5;
        }

        if (mDesiredDegrees > 175) {
            mDesiredDegrees = 175;
        }
       
        mBeam = new Beam(new Line(new Point(mCanvasWidth / 2, mCanvasHeight), new Point(0, 0)));
        Line firstLine = mBeam.lines.get(0);

        mMaxLeftSideDegrees = getMaxLeftSideDegrees(firstLine.p1);
        if (hittingLeftWall()) {
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            if (!tryToReflect(firstLine, firing)) {
                mLineDrawer.drawLine(firstLine, firing);
                if (!firing)
                    return;
                bounceRightThenLeft(firstLine);
            }
        } else if (hittingBackWall()) {
            if (firingStraightUp()) {
                firstLine.p2.y = 0;
                firstLine.p2.x = firstLine.p1.x;
                mLineDrawer.drawLine(firstLine, firing);
                return;
            } else if (hittingLeftSideOfBackWall()) {
                firstLine.p2.x = (int) (firstLine.p1.x - Math.tan(Math.toRadians(180 - 90 - mDesiredDegrees)) * mCanvasHeight);
                if (!tryToReflect(firstLine, firing)) {
                    mLineDrawer.drawLine(firstLine, firing);
                    return;
                }
            } else { // hitting right side...
                firstLine.p2.x = (int) (firstLine.p1.x + Math.tan(Math.toRadians(mDesiredDegrees - 90)) * mCanvasHeight);
                if (!tryToReflect(firstLine, firing)) {
                    mLineDrawer.drawLine(firstLine, firing);
                    return;
                }
            }
        } else { // hitting right wall
            firstLine.p2.x = mCanvasWidth;
            mDesiredDegrees = 180 - mDesiredDegrees;
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            if (!tryToReflect(firstLine, firing)) {
                mLineDrawer.drawLine(firstLine, firing);
                if (!firing)
                    return;
                bounceLeftThenRight(firstLine);
            }
        }
    }

    private boolean tryToReflect(Line line, boolean firing) {
        for (Triangle triangle : mTriangles) {
            Point i1 = Intersection.detect(line, triangle.edges().get(0));
            Point i2 = Intersection.detect(line, triangle.edges().get(1));
            Point i3 = Intersection.detect(line, triangle.edges().get(2));

            List<Point> p = new ArrayList<Point>();
            if (i1 != null) {
                p.add(i1);
            } else if (i2 != null) {
                p.add(i2);
            } else if (i3 != null) {
                p.add(i3);
            }
            if (!p.isEmpty()) {
                Collections.sort(p, new PointComparator(line.p1));
                line.p2.x = p.get(0).x;
                line.p2.y = p.get(0).y;
                mLineDrawer.drawLine(line, firing);
                // TODO: Start crazy reflection here...
                return true;
            }
        }
        return false;
    }

    private void bounceLeftThenRight(Line line) {
        double nextAngle = 180 - (mDesiredDegrees + 90);
        // -----Heading left ----
        line.p1.x = line.p2.x;
        line.p1.y = line.p2.y;
        line.p2.x = 0; // left wall
        line.p2.y = 0; // back wall

        line.p2.y = (int) (line.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * line.p1.x));
        if (line.p2.y > 0) { // left wall
            mLineDrawer.drawLine(line, true);
            bounceRightThenLeft(line);
        } else { // back wall
            line.p2.y = 0;
            line.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * line.p1.y);
            mLineDrawer.drawLine(line, true);
        }
    }

    private boolean hittingLeftSideOfBackWall() {
        return mDesiredDegrees < 90;
    }

    private boolean firingStraightUp() {
        return mDesiredDegrees == 90;
    }

    /**
     * x ------------>x ****************** y *T* * | * * * | * * * | * * * | * *
     * * y * M* * ****************** Returns the maximum number of degrees this
     * map supports while still hitting the left wall
     */
    private double getMaxLeftSideDegrees(Point startPoint) {
        double T = Math.atan2(startPoint.x, startPoint.y) * 180.0F / Math.PI;
        double M = 180 - (T + 90);
        return M;
    }

    private boolean hittingBackWall() {
        return mDesiredDegrees < mMaxLeftSideDegrees + (180 - mMaxLeftSideDegrees * 2);
    }

    private boolean hittingLeftWall() {
        return mDesiredDegrees < mMaxLeftSideDegrees;
    }

    private void bounceRightThenLeft(Line line) {
        // -----Heading right ---
        line.p1.x = line.p2.x;
        line.p1.y = line.p2.y;
        line.p2.y = 0;

        double nextAngle = 180 - (mDesiredDegrees + 90);
        line.p2.x = (int) (Math.tan(Math.toRadians(nextAngle)) * line.p1.y);
        if (line.p2.x < mCanvasWidth) { // hitting back wall
            mLineDrawer.drawLine(line, true);
        } else { // bounce off right wall
            line.p2.x = mCanvasWidth;
            line.p2.y = (int) (line.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * mCanvasWidth));
            mLineDrawer.drawLine(line, true);

            // -----Heading left ----
            line.p1.x = line.p2.x;
            line.p1.y = line.p2.y;
            line.p2.x = 0; // left wall
            line.p2.y = 0; // back wall

            line.p2.y = (int) (line.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * line.p1.x));
            if (line.p2.y > 0) { // left wall
                mLineDrawer.drawLine(line, true);
                bounceRightThenLeft(line);
            } else { // back wall
                line.p2.y = 0;
                line.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * line.p1.y);
                mLineDrawer.drawLine(line, true);
            }
        }
    }
}
