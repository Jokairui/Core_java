import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {


    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(100);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                atomTest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i <= 100; i++) {
            new Thread(runnable).start();
            countDownLatch.countDown();
        }
    }
    private static void atomTest() throws InterruptedException {
        countDownLatch.await();
        //这个地方通常是getResource，比方说更新token，就像下面这样
        //虽然不能保证只拿一次，但是可以少拿好多次，我测下来100次只会成功个位数次
        //if (getToken && atomicInteger.compareAndSet(0, 1)) {
        if (atomicInteger.compareAndSet(0, 1)) {
            System.out.println("Success");
            atomicInteger.compareAndSet(1, 0);
        } else {
            System.out.println("Failed");
        }
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




