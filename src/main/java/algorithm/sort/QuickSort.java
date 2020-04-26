package algorithm.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = {11, 8 , 3, 9, 7, 1, 2};
//        int[] a = {8 , 11, 3, 9};
        quick_sort(a, 0, a.length - 1);
        for(int i : a) {
            System.out.println(i);
        }
    }

    private static void quick_sort(int[] a, int left, int right) {

        if (left >= right) {
            return;
        }
        int q = partition(a, left, right);
        quick_sort(a, left, q - 1);
        quick_sort(a, q + 1, right);
    }

    private static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int i = l;
        for (int j = l; j < r; j++ ) {
            if (a[j] < pivot) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }
}
