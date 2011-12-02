package com.example.android.lasergame;

public class Intersection {

    // Returns the intersection if one exists, null if not
    public static Point detect(Line l1, Line l2) {
        double dyline1, dxline1;
        double dyline2, dxline2, e, f;
        double x1line1, y1line1, x2line1, y2line1;
        double x1line2, y1line2, x2line2, y2line2;
        if (!intersects(l1, l2)) {
            return null;
        }
        /*
         * first, check to see if the segments intersect by parameterization on
         * s and t; if s and t are both between [0,1], then the segments
         * intersect
         */
        x1line1 = (double) l1.p1.x;
        y1line1 = (double) l1.p1.y;
        x2line1 = (double) l1.p2.x;
        y2line1 = (double) l1.p2.y;
        x1line2 = (double) l2.p1.x;
        y1line2 = (double) l2.p1.y;
        x2line2 = (double) l2.p2.x;
        y2line2 = (double) l2.p2.y;

        /*
         * check to see if the segments have any endpoints in common. If they
         * do, then return the endpoints as the intersection point
         */
        if ((x1line1 == x1line2) && (y1line1 == y1line2)) {
            return (new Point((int) x1line1, (int) y1line1));
        }

        if ((x1line1 == x2line2) && (y1line1 == y2line2)) {
            return (new Point((int) x1line1, (int) y1line1));
        }

        if ((x2line1 == x1line2) && (y2line1 == y1line2)) {
            return (new Point((int) x2line1, (int) y2line1));
        }

        if ((x2line1 == x2line2) && (y2line1 == y2line2)) {
            return (new Point((int) x2line1, (int) y2line1));
        }

        dyline1 = -(y2line1 - y1line1);
        dxline1 = x2line1 - x1line1;
        dyline2 = -(y2line2 - y1line2);
        dxline2 = x2line2 - x1line2;

        e = -(dyline1 * x1line1) - (dxline1 * y1line1);
        f = -(dyline2 * x1line2) - (dxline2 * y1line2);

        /*
         * compute the intersection point using ax+by+e = 0 and cx+dy+f = 0 If
         * there is more than 1 intersection point between two lines,
         */
        if ((dyline1 * dxline2 - dyline2 * dxline1) == 0)
            return null;

        return (new Point((int) (-(e * dxline2 - dxline1 * f) / (dyline1 * dxline2 - dyline2 * dxline1)),
                (int) (-(dyline1 * f - dyline2 * e) / (dyline1 * dxline2 - dyline2 * dxline1))));
    }

    private static boolean intersects(Line l1, Line l2) {
        Point start1 = l1.p1;
        Point end1 = l1.p2;
        Point start2 = l2.p1;
        Point end2 = l2.p2;
        // First find Ax+By=C values for the two lines
        double A1 = end1.y - start1.y;
        double B1 = start1.x - end1.x;
        double C1 = A1 * start1.x + B1 * start1.y;

        double A2 = end2.y - start2.y;
        double B2 = start2.x - end2.x;
        double C2 = A2 * start2.x + B2 * start2.y;

        double det = (A1 * B2) - (A2 * B1);

        if (det == 0) {
            // Lines are either parallel, are collinear (the exact same
            // segment), or are overlapping partially, but not fully
            // To see what the case is, check if the endpoints of one line
            // correctly satisfy the equation of the other (meaning the two
            // lines have the same y-intercept).
            // If no endpoints on 2nd line can be found on 1st, they are
            // parallel.
            // If any can be found, they are either the same segment,
            // overlapping, or two segments of the same line, separated by some
            // distance.
            // Remember that we know they share a slope, so there are no other
            // possibilities

            // Check if the segments lie on the same line
            // (No need to check both points)
            if ((A1 * start2.x) + (B1 * start2.y) == C1) {
                // They are on the same line, check if they are in the same
                // space
                // We only need to check one axis - the other will follow
                if ((Math.min(start1.x, end1.x) < start2.x) && (Math.max(start1.x, end1.x) > start2.x))
                    return true;

                // One end point is ok, now check the other
                if ((Math.min(start1.x, end1.x) < end2.x) && (Math.max(start1.x, end1.x) > end2.x))
                    return true;

                // They are on the same line, but there is distance between them
                return false;
            }

            // They are simply parallel
            return false;
        } else {
            // Lines DO intersect somewhere, but do the line segments intersect?
            double x = (B2 * C1 - B1 * C2) / det;
            double y = (A1 * C2 - A2 * C1) / det;

            // Make sure that the intersection is within the bounding box of
            // both segments
            if ((x >= Math.min(start1.x, end1.x) && x <= Math.max(start1.x, end1.x))
                    && (y >= Math.min(start1.y, end1.y) && y <= Math.max(start1.y, end1.y))) {
                // We are within the bounding box of the first line segment,
                // so now check second line segment
                if ((x >= Math.min(start2.x, end2.x) && x <= Math.max(start2.x, end2.x))
                        && (y >= Math.min(start2.y, end2.y) && y <= Math.max(start2.y, end2.y))) {
                    // The line segments do intersect
                    return true;
                }
            }

            // The lines do intersect, but the line segments do not
            return false;
        }
    }
}