import org.apache.log4j.Logger;

public class RunnableExample implements Runnable {
    private static final Logger logger = Logger.getLogger(RunnableExample.class);
    private static final int MAX_COUNTER = 100;
    private Counter counter;

    public RunnableExample(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        logger.info("Runnable example "
                + Thread.currentThread().getName() + "was started..");
        while (counter.getCount() < MAX_COUNTER) {
            long count = counter.getCount();
            logger.info(Thread.currentThread().getName() + " (): " + ++count);
            counter.increment();
        }
        logger.info("Runnable example "
                + Thread.currentThread().getName() + "was finished");
    }
}
