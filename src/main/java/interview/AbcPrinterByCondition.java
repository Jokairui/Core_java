package interview;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbcPrinterByCondition {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition aCondition = lock.newCondition();
        Condition bCondition = lock.newCondition();
        Condition cCondition = lock.newCondition();

        new Thread(new ConditionPrinter("A", aCondition, bCondition, lock)).start();
        new Thread(new ConditionPrinter("B", bCondition, cCondition, lock)).start();
        new Thread(new ConditionPrinter("C", cCondition, aCondition, lock)).start();

    }
}

@AllArgsConstructor
class ConditionPrinter implements Runnable{
    private String name;
    private Condition selfCondition;
    private Condition nextCondition;
    private Lock lock;
    @SneakyThrows
    @Override
    public void run() {

        int count = 0;

        while(count++ < 10) {
            try{
                lock.lock();
                System.out.println(name + count);
                nextCondition.signalAll();
                if (count < 10) {
                    selfCondition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
