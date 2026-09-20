package dividendconquer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClosestPairSolver {

    private long comparisons;
    private int maxDepth;

    public double findClosest(Point[] points) {
        comparisons = 0;
        maxDepth = 0;

        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }

        Point[] byX = points.clone();
        Point[] byY = points.clone();

        Arrays.sort(byX, (a, b) -> Double.compare(a.x, b.x));
        Arrays.sort(byY, (a, b) -> Double.compare(a.y, b.y));

        return closest(byX, byY, 1);
    }

    private double closest(Point[] byX, Point[] byY, int depth) {

        if (depth > maxDepth) {
            maxDepth = depth;
        }

        int n = byX.length;

        if (n <= 3) {
            return bruteForce(byX);
        }

        int mid = n / 2;

        Point[] leftX = Arrays.copyOfRange(byX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(byX, mid, n);

        Set<Point> leftPoints = new HashSet<>(Arrays.asList(leftX));

        Point[] leftY = new Point[mid];
        Point[] rightY = new Point[n - mid];

        int leftCount = 0;
        int rightCount = 0;

        for (Point point : byY) {

            if (leftPoints.contains(point)) {
                leftY[leftCount] = point;
                leftCount++;
            } else {
                rightY[rightCount] = point;
                rightCount++;
            }
        }

        double leftDistance = closest(leftX, leftY, depth + 1);
        double rightDistance = closest(rightX, rightY, depth + 1);

        double distance = Math.min(leftDistance, rightDistance);

        double middleX = byX[mid].x;

        Point[] strip = new Point[n];
        int stripSize = 0;

        for (Point point : byY) {

            if (Math.abs(point.x - middleX) < distance) {
                strip[stripSize] = point;
                stripSize++;
            }
        }

        for (int i = 0; i < stripSize; i++) {

            for (int j = i + 1; j < stripSize; j++) {

                if (strip[j].y - strip[i].y >= distance) {
                    break;
                }

                comparisons++;

                double currentDistance =
                        getDistance(strip[i], strip[j]);

                if (currentDistance < distance) {
                    distance = currentDistance;
                }
            }
        }

        return distance;
    }

    private double bruteForce(Point[] points) {

        double best = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {

                comparisons++;

                double current = getDistance(points[i], points[j]);

                if (current < best) {
                    best = current;
                }
            }
        }

        return best;
    }

    private double getDistance(Point a, Point b) {

        double dx = a.x - b.x;
        double dy = a.y - b.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
