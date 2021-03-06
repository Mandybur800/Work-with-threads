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
        logger.info("Thread example " + Thread.currentThread().getName() + "was started..");
        while (counter.getCount() < MAX_COUNTER) {
            counter.increment();
            logger.info(Thread.currentThread().getName() + ": " + counter.getCount());
        }
        logger.info("Thread example " + Thread.currentThread().getName() + "was finished");
    }
}
