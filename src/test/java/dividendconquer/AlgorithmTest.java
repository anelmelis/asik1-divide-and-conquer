package dividendconquer;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


public class AlgorithmTest {
    @Test
    void mergeSortTest() {
        MergeSorter sorter = new MergeSorter();
        int[] a = {5, 2, 8, 1, 3};
        int[] expected = {1, 2, 3, 5, 8};

        sorter.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    void quickSortTest() {
        QuickSorter sorter = new QuickSorter();
        int[] a = {9, 4, 1, 7, 2, 8};
        int[] expected = {1, 2, 4, 7, 8, 9};

        sorter.sort(a);
        assertArrayEquals(expected, a);
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

}
