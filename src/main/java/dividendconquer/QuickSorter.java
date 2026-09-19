package dividendconquer;
import java.util.Random;

public class QuickSorter {

    private final Random random = new Random();

    private long comparisons;
    private long swaps;
    private int maxDepth;

    public void sort(int[] a) {
        comparisons = 0;
        swaps = 0;
        maxDepth = 0;

        if (a == null || a.length < 2) {
            return;
        }

        quickSort(a, 0, a.length - 1, 1);
    }

    private void quickSort(int[] a, int left, int right, int depth) {
        while (left < right) {

            if (depth > maxDepth) {
                maxDepth = depth;
            }

            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = a[pivotIndex];

            int[] parts = partition(a, left, right, pivot);

            int leftEnd = parts[0];
            int rightStart = parts[1];

            // Рекурсируем в меньшую часть
            if (leftEnd - left < right - rightStart) {
                if (left < leftEnd) {
                    quickSort(a, left, leftEnd, depth + 1);
                }

                // Большую часть обрабатываем через цикл
                left = rightStart;
            } else {
                if (rightStart < right) {
                    quickSort(a, rightStart, right, depth + 1);
                }

                right = leftEnd;
            }
        }
    }

    private int[] partition(int[] a, int left, int right, int pivot) {
        int i = left;
        int j = right;

        while (i <= j) {

            while (i <= right) {
                comparisons++;

                if (a[i] >= pivot) {
                    break;
                }

                i++;
            }

            while (j >= left) {
                comparisons++;

                if (a[j] <= pivot) {
                    break;
                }

                j--;
            }

            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }

        return new int[]{j, i};
    }

    private void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        swaps++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}