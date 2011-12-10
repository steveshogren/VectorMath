package com.example.android.lasergame;

import com.example.android.lasergame.Intersection.Intersects;

public class LaserCalculator {

    private int mCanvasWidth;
    private int mCanvasHeight;
    private double mDesiredDegrees;
    private double mMaxLeftSideDegrees;
    private Triangle[] mTriangles;
    private Beam mBeam;
    private double mMinimumFiringAngle;
    private double mMaximumFiringAngle;

    public LaserCalculator(int canvasWidth, int canvasHeight, double minimumFiringAngle, double maximumFiringAngle) {
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
        mMinimumFiringAngle = minimumFiringAngle;
        mMaximumFiringAngle = maximumFiringAngle;
        mTriangles = new Triangle[] {};
    }

    public LaserCalculator(int canvasWidth, int canvasHeight, double minimumFiringAngle, double maximumFiringAngle,
            Triangle[] triangles) {
        mTriangles = triangles;
        mCanvasWidth = canvasWidth;
        mCanvasHeight = canvasHeight;
        mMinimumFiringAngle = minimumFiringAngle;
        mMaximumFiringAngle = maximumFiringAngle;
    }

    public Beam fireLaser(double desiredDegrees) {
        setDesiredDegrees(desiredDegrees);

        mBeam = new Beam();
        Line firstLine = new Line(new Point(mCanvasWidth / 2, mCanvasHeight), new Point(1, 1));

        mMaxLeftSideDegrees = getMaxLeftSideDegrees(firstLine.p1);
        if (hittingLeftWall()) {
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            reflect(firstLine);
        } else if (hittingBackWall()) {
            if (firingStraightUp()) {
                firstLine.p2.y = 1;
                firstLine.p2.x = firstLine.p1.x;
                mBeam.addLine(firstLine);
                return mBeam;
            } else if (hittingLeftSideOfBackWall()) {
                firstLine.p2.x = (int) (firstLine.p1.x - Math.tan(Math.toRadians(180 - 90 - mDesiredDegrees))
                        * mCanvasHeight);
                reflect(firstLine);
            } else { // hitting right side...
                firstLine.p2.x = (int) (firstLine.p1.x + Math.tan(Math.toRadians(mDesiredDegrees - 90)) * mCanvasHeight);
                reflect(firstLine);
            }
        } else { // hitting right wall
            firstLine.p2.x = mCanvasWidth;
            mDesiredDegrees = 180 - mDesiredDegrees;
            firstLine.p2.y = (int) (mCanvasHeight - Math.tan(Math.toRadians(mDesiredDegrees)) * firstLine.p1.x);
            reflect(firstLine);
        }
        return mBeam;
    }

    private void setDesiredDegrees(double desiredDegrees) {
        mDesiredDegrees = Math.max(desiredDegrees, mMinimumFiringAngle);
        mDesiredDegrees = Math.min(mDesiredDegrees, mMaximumFiringAngle);
    }

    private void reflect(Line incomingLine) {
        Line[] walls = { new Line(1, 1, 1, mCanvasHeight), new Line(1, 1, mCanvasWidth, 1),
                new Line(mCanvasWidth, 1, mCanvasWidth, mCanvasHeight),
                new Line(1, mCanvasHeight, mCanvasWidth, mCanvasHeight) };
        Intersects intersects = Intersection.whichEdgeDoesTheLinePassThroughFirst(mTriangles, walls, incomingLine);

        if (intersects != null) { // it has to intersect with SOMETHING
            incomingLine.p2.x = intersects.intersectionP.x;
            incomingLine.p2.y = intersects.intersectionP.y;
            if (mBeam.lines.size() > 0 && incomingLine.p1.equals(mBeam.lines.get(mBeam.lines.size() - 1).p2)
                    && incomingLine.p2.equals(mBeam.lines.get(mBeam.lines.size() - 1).p1)) {
                // return if "backtracking" here
                return;
            }
            mBeam.addLine(incomingLine);
            if (incomingLine.p2.y <= 1 || incomingLine.p2.y >= mCanvasHeight) {
                return;
            }

            // start bouncing!!!!
            Vector2 vPrime = findVPrime(incomingLine, intersects.edge);

            Line next = new Line(new Point(incomingLine.p2.x, incomingLine.p2.y), new Point(mCanvasWidth, 1));
            // x = ((y - y1)/m) + x1
            if (vPrime.x > 0) {
                next.p2.y = (int) (((vPrime.y / vPrime.x) * (mCanvasWidth - incomingLine.p2.x)) + incomingLine.p2.y);
            } else {
                next.p2.x = 1;
                next.p2.y = (int) (((vPrime.y / vPrime.x) * (1 - incomingLine.p2.x)) + incomingLine.p2.y);
            }

            Intersects nextIntersects = Intersection.whichEdgeDoesTheLinePassThroughFirst(mTriangles, walls, next);
            if (hittingAWall(nextIntersects)) {
                if (nextIntersects != null) {
                    next.p2 = nextIntersects.intersectionP;
                } else {
                    if (intersects.intersectionP.x == mCanvasWidth) {
                        next = new Line(new Point(incomingLine.p2.x, incomingLine.p2.y), new Point(1, 1));
                        // y = m(x-x1)+y1
                        next.p2.y = (int) (((vPrime.y / vPrime.x) * (1 - incomingLine.p2.x)) + incomingLine.p2.y);
                    }
                    if (next.p2.y < 1) {
                        next.p2.y = 1;
                        // x = ((y - y1)/m) + x1
                        next.p2.x = (int) ((1 - incomingLine.p2.y) / (vPrime.y / vPrime.x) + incomingLine.p2.x);
                    }
                    if (next.p2.y > mCanvasHeight) {
                        next.p2.y = mCanvasHeight;
                        // x = ((y - y1)/m) + x1
                        next.p2.x = (int) ((1 - incomingLine.p2.y) / (vPrime.y / vPrime.x) + incomingLine.p2.x);
                    }
                    Intersects nextIntersects2 = Intersection.whichEdgeDoesTheLinePassThroughFirst(mTriangles, walls,
                            next);
                    if (nextIntersects2 != null) {
                        next.p2 = nextIntersects2.intersectionP;
                    }
                }
            } else {
                // hitting a triangle
                if (intersects.triangle != null) {
                    for (Line tLine : intersects.triangle.edges()) {
                        if (tLine != intersects.edge && Intersection.detect(tLine, next) != null) {
                            next = new Line(new Point(incomingLine.p2.x, incomingLine.p2.y), new Point(1, 1));
                            next.p2.y = (int) (((vPrime.y / vPrime.x) * (1 - incomingLine.p2.x)) + incomingLine.p2.y);
                        }
                    }
                }
            }

            reflect(next);
        }
        return;
    }

    private Vector2 findVPrime(Line incomingLine, Line intersectsWithLine) {
        Vector2 n1 = new Vector2(intersectsWithLine.p1.x, intersectsWithLine.p1.y);
        Vector2 n2 = new Vector2(intersectsWithLine.p2.x, intersectsWithLine.p2.y);
        Vector2 nN = n1.add(n2).nor();
        Vector2 lineNorm = n1.sub(n2).nor();
        Vector2 nNPerp = new Vector2(-lineNorm.y, lineNorm.x);

        nN = nNPerp;
        Vector2 v1 = new Vector2(incomingLine.p1.x, incomingLine.p1.y);
        Vector2 v2 = new Vector2(incomingLine.p2.x, incomingLine.p2.y);
        Vector2 vN = v2.sub(v1).nor();

        Vector2 u = nN.mul(vN.dot(nN));
        Vector2 w = vN.sub(u);
        Vector2 vPrime = w.sub(u);
        return vPrime;
    }

    private boolean hittingAWall(Intersects nextIntersects) {
        return nextIntersects == null || (nextIntersects != null && nextIntersects.triangle == null);
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
}
