package algorithm.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {11, 8 , 3, 9, 7, 1, 2};
        bubbleSort(a);
        for(int i : a) {
            System.out.println(i);
        }
    }

    private static void bubbleSort(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
}
