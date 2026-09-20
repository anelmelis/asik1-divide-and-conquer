package dividendconquer;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class AlgorithmTest {

    @Test
    void mergeSortTest() {
        MergeSorter sorter = new MergeSorter();

        int[][] tests = {
                {},
                {5},
                {5, 2, 8, 1, 3},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 3, 1, 2, 3, 1, 2}
        };

        for (int[] a : tests) {
            int[] expected = a.clone();
            Arrays.sort(expected);

            sorter.sort(a);

            assertArrayEquals(expected, a);
        }
    }

    @Test
    void quickSortTest() {
        QuickSorter sorter = new QuickSorter();

        int[][] tests = {
                {},
                {5},
                {9, 4, 1, 7, 2, 8},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {4, 4, 2, 4, 1, 2, 4}
        };

        for (int[] a : tests) {
            int[] expected = a.clone();
            Arrays.sort(expected);

            sorter.sort(a);

            assertArrayEquals(expected, a);
        }
    }

    @Test
    void selectTest() {
        DeterministicSelector selector = new DeterministicSelector();

        int[] a = {7, 2, 9, 1, 5, 3, 8};

        assertEquals(1, selector.select(a.clone(), 0));
        assertEquals(5, selector.select(a.clone(), 3));
        assertEquals(9, selector.select(a.clone(), 6));
    }

    @Test
    void randomSelectTest() {
        Random random = new Random();
        DeterministicSelector selector = new DeterministicSelector();

        for (int i = 0; i < 100; i++) {

            int size = random.nextInt(91) + 10;
            int[] a = new int[size];

            for (int j = 0; j < size; j++) {
                a[j] = random.nextInt(1000);
            }

            int[] sorted = a.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(size);

            assertEquals(
                    sorted[k],
                    selector.select(a.clone(), k)
            );
        }
    }

    @Test
    void closestPairTest() {
        ClosestPairSolver solver = new ClosestPairSolver();

        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(10, 10)
        };

        double result = solver.findClosest(points);

        assertEquals(Math.sqrt(2), result, 0.000001);
    }

    @Test
    void closestPairRandomTest() {
        Random random = new Random();
        ClosestPairSolver solver = new ClosestPairSolver();

        for (int n = 2; n <= 2000; n += 100) {

            Point[] points = new Point[n];

            for (int i = 0; i < n; i++) {
                points[i] = new Point(
                        random.nextDouble() * 10000,
                        random.nextDouble() * 10000
                );
            }

            double expected = bruteForce(points);
            double actual = solver.findClosest(points);

            assertEquals(expected, actual, 0.000001);
        }
    }

    private double bruteForce(Point[] points) {

        double best = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {

                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;

                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < best) {
                    best = distance;
                }
            }
        }

        return best;
    }

}
