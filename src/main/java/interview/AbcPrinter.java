package interview;

import java.util.concurrent.locks.ReentrantLock;

public class AbcPrinter {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        new Thread(new Printer("A", lock)).start();
        new Thread(new Printer("B", lock)).start();
        new Thread(new Printer("C", lock)).start();

    }
}

class Printer implements Runnable{

    private String name;
    private ReentrantLock lock;

    public Printer(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(name);
        lock.unlock();
    }
}
