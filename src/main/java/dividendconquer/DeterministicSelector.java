package dividendconquer;
public class DeterministicSelector {

    private long comparisons;
    private int maxDepth;

    public int select(int[] a, int k) {
        comparisons = 0;
        maxDepth = 0;

        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }

        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Wrong k");
        }

        return select(a, 0, a.length - 1, k, 1);
    }

    private int select(int[] a, int left, int right, int k, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        if (left == right) {
            return a[left];
        }

        int pivot = getPivot(a, left, right);
        int[] p = partition(a, left, right, pivot);

        if (k <= p[0]) {
            return select(a, left, p[0], k, depth + 1);
        }

        if (k >= p[1]) {
            return select(a, p[1], right, k, depth + 1);
        }

        return a[k];
    }

    private int getPivot(int[] a, int left, int right) {
        int n = right - left + 1;

        if (n <= 5) {
            insertionSort(a, left, right);
            return a[left + n / 2];
        }

        int count = 0;

        for (int i = left; i <= right; i += 5) {
            int end = Math.min(i + 4, right);

            insertionSort(a, i, end);

            int middle = i + (end - i) / 2;

            swap(a, left + count, middle);
            count++;
        }

        int middle = left + count / 2;

        return select(a, left, left + count - 1, middle, 1);
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

    private void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
