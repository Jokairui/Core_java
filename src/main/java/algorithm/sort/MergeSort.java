package algorithm.sort;

public class MergeSort {
    public static void main(String[] args){
//        int[] a = {11, 8};
//        int[] b = Arrays.copyOfRange(a, 0, 1);
//        int[] c = Arrays.copyOfRange(a, 1, 2);
//        System.out.println(b[0]);
//        System.out.println(c[0]);
        int[] a = {11, 8 , 3, 9, 7, 1, 2};
//        int[] a = {8 , 11, 3, 9};
        merge_sort(a, 0, a.length - 1);
        for(int i : a) {
            System.out.println(i);
        }
    }

    private static void merge_sort(int[] origin, int left, int right){
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        merge_sort(origin, left, mid);
        merge_sort(origin, mid + 1 , right);

        merge(origin, left, mid, right);
    }

    private static void merge(int[] origin, int l, int m, int r) {
        int[] left = new int[m - l + 1];
        int[] right = new int[r - m];
        for(int i = 0; i < left.length; i++) {
            left[i] = origin[l + i];
        }
        for(int i = 0; i < right.length; i++) {
            right[i] = origin[m + 1 + i];
        }


        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]){
                origin[k++] = left[i++];
            } else {
                origin[k++] = right[j++];
            }
        }

        while(i < left.length) {
            origin[k++] = left[i++];
        }

        while(j < right.length) {
            origin[k++] = right[j++];
        }
    }
}
