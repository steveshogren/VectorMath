package com.example.android.lasergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaserCalculator {

    private int mCanvasWidth;
    private int mCanvasHeight;
    private double mDesiredDegrees;
    private double mMaxLeftSideDegrees;
    private Triangle[] mTriangles;
    private Beam mBeam;

    public LaserCalculator(int canvasWidth, int canvasHeight) {
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
        mTriangles = new Triangle[] {};
    }

    public LaserCalculator(int canvasWidth, int canvasHeight, Triangle[] triangles) {
        mTriangles = triangles;
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
    }

    public Beam fireLaser(double desiredDegrees) {
        mDesiredDegrees = desiredDegrees;
        if (mDesiredDegrees < 5) {
            mDesiredDegrees = 5;
        }

        if (mDesiredDegrees > 175) {
            mDesiredDegrees = 175;
        }

        mBeam = new Beam();
        Line firstLine = new Line(new Point(mCanvasWidth / 2, mCanvasHeight), new Point(0, 0));

        mMaxLeftSideDegrees = getMaxLeftSideDegrees(firstLine.p1);
        if (hittingLeftWall()) {
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            if (!tryToReflect(firstLine)) {
                mBeam.addLine(firstLine);
                bounceRightThenLeft(firstLine);
            }
        } else if (hittingBackWall()) {
            if (firingStraightUp()) {
                firstLine.p2.y = 0;
                firstLine.p2.x = firstLine.p1.x;
                mBeam.addLine(firstLine);
                return mBeam;
            } else if (hittingLeftSideOfBackWall()) {
                firstLine.p2.x = (int) (firstLine.p1.x - Math.tan(Math.toRadians(180 - 90 - mDesiredDegrees))
                        * mCanvasHeight);
                if (!tryToReflect(firstLine)) {
                    mBeam.addLine(firstLine);
                    return mBeam;
                }
            } else { // hitting right side...
                firstLine.p2.x = (int) (firstLine.p1.x + Math.tan(Math.toRadians(mDesiredDegrees - 90)) * mCanvasHeight);
                if (!tryToReflect(firstLine)) {
                    mBeam.addLine(firstLine);
                    return mBeam;
                }
            }
        } else { // hitting right wall
            firstLine.p2.x = mCanvasWidth;
            mDesiredDegrees = 180 - mDesiredDegrees;
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            if (!tryToReflect(firstLine)) {
                mBeam.addLine(firstLine);
                bounceLeftThenRight(firstLine);
            }
        }
        return mBeam;
    }

    private boolean tryToReflect(Line line) {
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
                mBeam.addLine(line);
                // TODO: Start crazy reflection here...
                return true;
            }
        }
        return false;
    }

    private void bounceLeftThenRight(Line line) {
        double nextAngle = 180 - (mDesiredDegrees + 90);
        // -----Heading left ----
        Line l2 = new Line(new Point(line.p2.x, line.p2.y), new Point(0, 0));

        l2.p2.y = (int) (l2.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * l2.p1.x));
        if (l2.p2.y > 0) { // left wall
            mBeam.addLine(l2);
            bounceRightThenLeft(l2);
        } else { // back wall
            l2.p2.y = 0;
            l2.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * l2.p1.y);
            mBeam.addLine(l2);
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
        Line l2 = new Line(new Point(line.p2.x, line.p2.y), new Point(0, 0));
        // -----Heading right ---

        double nextAngle = 180 - (mDesiredDegrees + 90);
        l2.p2.x = (int) (Math.tan(Math.toRadians(nextAngle)) * l2.p1.y);
        if (l2.p2.x < mCanvasWidth) { // hitting back wall
            mBeam.addLine(l2);
        } else { // bounce off right wall
            l2.p2.x = mCanvasWidth;
            l2.p2.y = (int) (l2.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * mCanvasWidth));
            mBeam.addLine(l2);

            // -----Heading left ----
            Line l3 = new Line(new Point(l2.p2.x, l2.p2.y), new Point(0, 0));

            l3.p2.y = (int) (l3.p1.y - (float) (Math.tan(Math.toRadians(mDesiredDegrees)) * l3.p1.x));
            if (l3.p2.y > 0) { // left wall
                mBeam.addLine(l3);
                bounceRightThenLeft(l3);
            } else { // back wall
                l3.p2.y = 0;
                l3.p2.x = (int) (mCanvasWidth - Math.tan(Math.toRadians(nextAngle)) * l3.p1.y);
                mBeam.addLine(l3);
            }
        }
    }
}
