package algorithm.sort;

public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {11, 8 , 3, 9, 7, 1, 2};
        selectionSort(a);
        for(int i : a) {
            System.out.println(i);
        }
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = a[i], index = i;
            for(int j = i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    index = j;
                }
            }
            int tmp = a[i];
            a[i] = a[index];
            a[index] = tmp;
        }
    }
}
