import org.apache.log4j.Logger;

public class ThreadExample extends Thread {
    private static final Logger logger = Logger.getLogger(ThreadExample.class);
    private static final int MAX_COUNTER = 100;
    private final Counter counter;

    public ThreadExample(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Thread example " + Thread.currentThread().getName() + "was started..");
        while (counter.getCount() < MAX_COUNTER) {
            long count = counter.getCount();
            logger.info(Thread.currentThread().getName() + ": " + ++count);
            counter.setCount(count);
        }
        System.out.println("Thread example " + Thread.currentThread().getName() + "was finished");
    }
}
