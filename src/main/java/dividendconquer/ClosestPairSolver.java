package dividendconquer;
import java.util.Arrays;

public class ClosestPairSolver {

    private long comparisons;
    private int maxDepth;

    public double findClosest(Point[] points) {
        comparisons = 0;
        maxDepth = 0;

        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }

        Point[] a = points.clone();
        Point[] temp = new Point[a.length];

        Arrays.sort(a, (p1, p2) -> Double.compare(p1.x, p2.x));

        return closest(a, temp, 0, a.length - 1, 1);
    }

    private double closest(Point[] a, Point[] temp,
                           int left, int right, int depth) {

        if (depth > maxDepth) {
            maxDepth = depth;
        }

        int n = right - left + 1;

        if (n <= 3) {
            return bruteForce(a, left, right);
        }

        int mid = left + (right - left) / 2;

        double d1 = closest(a, temp, left, mid, depth + 1);
        double d2 = closest(a, temp, mid + 1, right, depth + 1);

        double d = Math.min(d1, d2);
        double middleX = a[mid].x;

        int count = 0;

        for (int i = left; i <= right; i++) {
            if (Math.abs(a[i].x - middleX) < d) {
                temp[count] = a[i];
                count++;
            }
        }

        Arrays.sort(temp, 0, count,
                (p1, p2) -> Double.compare(p1.y, p2.y));

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {

                if (temp[j].y - temp[i].y >= d) {
                    break;
                }

                comparisons++;

                double current = distance(temp[i], temp[j]);

                if (current < d) {
                    d = current;
                }
            }
        }

        return d;
    }

    private double bruteForce(Point[] a, int left, int right) {
        double best = Double.POSITIVE_INFINITY;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                comparisons++;

                double current = distance(a[i], a[j]);

                if (current < best) {
                    best = current;
                }
            }
        }

        return best;
    }

    private double distance(Point a, Point b) {
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

