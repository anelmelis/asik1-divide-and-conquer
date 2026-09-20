package dividendconquer;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] array = {7, 2, 9, 1, 5, 3, 8};

        System.out.println("=== Divide-and-Conquer Algorithms ===");
        System.out.println();

        System.out.println("Original array: " + Arrays.toString(array));
        System.out.println();


        int[] mergeArray = array.clone();
        MergeSorter mergeSorter = new MergeSorter();
        mergeSorter.sort(mergeArray);
        System.out.println("MergeSort:");
        System.out.println("Result: " + Arrays.toString(mergeArray));
        System.out.println("Comparisons: " + mergeSorter.getComparisons());
        System.out.println("Recursion depth: " + mergeSorter.getMaxDepth());
        System.out.println();


        int[] quickArray = array.clone();
        QuickSorter quickSorter = new QuickSorter();
        quickSorter.sort(quickArray);
        System.out.println("QuickSort:");
        System.out.println("Result: " + Arrays.toString(quickArray));
        System.out.println("Comparisons: " + quickSorter.getComparisons());
        System.out.println("Swaps: " + quickSorter.getSwaps());
        System.out.println("Recursion depth: " + quickSorter.getMaxDepth());
        System.out.println();


        int[] selectArray = array.clone();
        DeterministicSelector selector = new DeterministicSelector();
        int k = 3;
        int result = selector.select(selectArray, k);
        System.out.println("Deterministic Select:");
        System.out.println("k = " + k);
        System.out.println("Selected element: " + result);
        System.out.println("Comparisons: " + selector.getComparisons());
        System.out.println("Recursion depth: " + selector.getMaxDepth());
        System.out.println();


        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(10, 10)
        };
        ClosestPairSolver closestPairSolver = new ClosestPairSolver();
        double distance = closestPairSolver.findClosest(points);

        System.out.println("Closest Pair:");
        System.out.println("Closest distance: " + distance);
        System.out.println("Comparisons: " + closestPairSolver.getComparisons());
        System.out.println("Recursion depth: " + closestPairSolver.getMaxDepth());
    }
}