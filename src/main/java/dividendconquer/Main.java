package dividendconquer;
import java.util.Arrays;
public class Main {

    public static void main(String[] args) {

        int[] array = {7, 2, 9, 1, 5, 3, 8};

        System.out.println("Original array: " + Arrays.toString(array));

        int[] mergeArray = array.clone();
        MergeSorter mergeSorter = new MergeSorter();
        mergeSorter.sort(mergeArray);
        System.out.println("MergeSort: " + Arrays.toString(mergeArray));
        System.out.println("MergeSort comparisons: " + mergeSorter.getComparisons());


        int[] quickArray = array.clone();
        QuickSorter quickSorter = new QuickSorter();
        quickSorter.sort(quickArray);
        System.out.println("QuickSort: " + Arrays.toString(quickArray));
        System.out.println("QuickSort comparisons: " + quickSorter.getComparisons());


        int[] selectArray = array.clone();
        DeterministicSelector selector = new DeterministicSelector();
        int k = 3;
        int result = selector.select(selectArray, k);
        System.out.println("Element with index " + k + ": " + result);


        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(10, 10)
        };
        ClosestPairSolver closestPairSolver = new ClosestPairSolver();
        double distance = closestPairSolver.findClosest(points);
        System.out.println("Closest pair distance: " + distance);
    }
}