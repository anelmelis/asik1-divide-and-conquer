package dividendconquer;

public class MergeSorter {

    private static final int CUTOFF = 16;

    private long comparisons;
    private int maxDepth;

    public void sort(int[] a) {
        comparisons = 0;
        maxDepth = 0;

        if (a == null || a.length < 2) {
            return;
        }

        int[] temp = new int[a.length];
        mergeSort(a, temp, 0, a.length - 1, 1);
    }

    private void mergeSort(int[] a, int[] temp, int left, int right, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        if (right - left + 1 <= CUTOFF) {
            insertionSort(a, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(a, temp, left, mid, depth + 1);
        mergeSort(a, temp, mid + 1, right, depth + 1);

        comparisons++;
        if (a[mid] <= a[mid + 1]) {
            return;
        }
        merge(a, temp, left, mid, right);
    }

    private void merge(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            comparisons++;

            if (a[i] <= a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }

            k++;
        }

        while (i <= mid) {
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = a[j];
            j++;
            k++;
        }

        for (int x = left; x <= right; x++) {
            a[x] = temp[x];
        }
    }

    private void insertionSort(int[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int value = a[i];
            int j = i - 1;

            while (j >= left) {
                comparisons++;

                if (a[j] <= value) {
                    break;
                }

                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = value;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}