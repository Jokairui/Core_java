import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {
        float f = 0.1f;
        float sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += f;
        }
        System.out.println(sum);

    }

    private static void merge_sort(int[] origin, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        merge_sort(origin, left, mid);
        merge_sort(origin, mid + 1, right);

        merge(origin, left, mid, right);
    }

    private static void merge(int[] origin, int l, int m, int r) {
        int[] left = new int[m - l + 1];
        int[] right = new int[r - m];
        for (int i = 0; i < left.length; i++) {
            left[i] = origin[l + i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = origin[m + 1 + i];
        }


        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                origin[k++] = left[i++];
            } else {
                origin[k++] = right[j++];
            }
        }

        while (i < left.length) {
            origin[k++] = left[i++];
        }

        while (j < right.length) {
            origin[k++] = right[j++];
        }
    }

}




