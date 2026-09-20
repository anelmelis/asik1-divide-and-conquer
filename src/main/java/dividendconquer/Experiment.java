package dividendconquer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Experiment {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("results/results.csv");

            writer.write("algorithm,input_type,size,time_ns,recursion_depth,comparisons,swaps\n");

            int[] sizes = {100, 500, 1000, 5000, 10000};
            String[] types = {"random", "sorted", "reverse", "duplicates"};
            Random random = new Random();

            System.out.println("Algorithm experiments");
            System.out.println();

            for (String type : types) {
                System.out.println("Input type: " + type);
                for (int size : sizes) {
                    int[] a = new int[size];

                    for (int i = 0; i < size; i++) {
                        if (type.equals("random")) {
                            a[i] = random.nextInt(100000);
                        } else if (type.equals("sorted")) {
                            a[i] = i;
                        } else if (type.equals("reverse")) {
                            a[i] = size - i;
                        } else {
                            a[i] = random.nextInt(10);
                        }
                    }


                    int[] mergeArray = a.clone();
                    MergeSorter mergeSorter = new MergeSorter();

                    long start = System.nanoTime();
                    mergeSorter.sort(mergeArray);
                    long end = System.nanoTime();

                    System.out.println(
                            "MergeSort: n=" + size +
                                    ", time=" + (end - start) +
                                    " ns, depth=" + mergeSorter.getMaxDepth() +
                                    ", comparisons=" + mergeSorter.getComparisons()
                    );
                    writer.write(
                            "MergeSort," + type + "," + size + "," +
                                    (end - start) + "," +
                                    mergeSorter.getMaxDepth() + "," +
                                    mergeSorter.getComparisons() + ",0\n"
                    );


                    int[] quickArray = a.clone();
                    QuickSorter quickSorter = new QuickSorter();

                    start = System.nanoTime();
                    quickSorter.sort(quickArray);
                    end = System.nanoTime();

                    System.out.println(
                            "QuickSort: n=" + size +
                                    ", time=" + (end - start) +
                                    " ns, depth=" + quickSorter.getMaxDepth() +
                                    ", comparisons=" + quickSorter.getComparisons() +
                                    ", swaps=" + quickSorter.getSwaps()
                    );
                    writer.write(
                            "QuickSort," + type + "," + size + "," +
                                    (end - start) + "," +
                                    quickSorter.getMaxDepth() + "," +
                                    quickSorter.getComparisons() + "," +
                                    quickSorter.getSwaps() + "\n"
                    );


                    int[] selectArray = a.clone();
                    DeterministicSelector selector = new DeterministicSelector();
                    int k = size / 2;
                    start = System.nanoTime();
                    selector.select(selectArray, k);
                    end = System.nanoTime();

                    System.out.println(
                            "Select: n=" + size +
                                    ", time=" + (end - start) +
                                    " ns, depth=" + selector.getMaxDepth() +
                                    ", comparisons=" + selector.getComparisons()
                    );

                    writer.write(
                            "DeterministicSelect," + type + "," + size + "," +
                                    (end - start) + "," +
                                    selector.getMaxDepth() + "," +
                                    selector.getComparisons() + ",0\n"
                    );


                    Point[] points = new Point[size];
                    for (int i = 0; i < size; i++) {
                        if (type.equals("random")) {
                            points[i] = new Point(
                                    random.nextDouble() * 100000,
                                    random.nextDouble() * 100000
                            );
                        } else if (type.equals("sorted")) {
                            points[i] = new Point(i, i);
                        } else if (type.equals("reverse")) {
                            points[i] = new Point(size - i, i);
                        } else {
                            points[i] = new Point(
                                    random.nextInt(10),
                                    random.nextInt(10)
                            );
                        }
                    }

                    ClosestPairSolver closestPairSolver = new ClosestPairSolver();

                    start = System.nanoTime();
                    closestPairSolver.findClosest(points);
                    end = System.nanoTime();

                    System.out.println(
                            "ClosestPair: n=" + size +
                                    ", time=" + (end - start) +
                                    " ns, depth=" + closestPairSolver.getMaxDepth() +
                                    ", comparisons=" + closestPairSolver.getComparisons()
                    );

                    writer.write(
                            "ClosestPair," + type + "," + size + "," +
                                    (end - start) + "," +
                                    closestPairSolver.getMaxDepth() + "," +
                                    closestPairSolver.getComparisons() + ",0\n"
                    );

                }

                System.out.println();
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
        }

}
