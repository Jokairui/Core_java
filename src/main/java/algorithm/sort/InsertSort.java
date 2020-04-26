package algorithm.sort;

public class InsertSort {
    public static void main(String[] args) {

        int[] a = {11, 8 , 3, 9, 7, 1, 2};
        insertSort(a);
        for(int i : a) {
            System.out.println(i);
        }
    }

    private static void insertSort(int[] a){
        for(int i = 1; i < a.length; i++) {
            int value = a[i];
            for(int j = i - 1; j >= 0; j--) {
                if (a[j] > value) {
                    int tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
}
