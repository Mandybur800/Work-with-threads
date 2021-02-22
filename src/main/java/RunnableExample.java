import org.apache.log4j.Logger;

public class RunnableExample implements Runnable {
    private static final Logger logger = Logger.getLogger(ThreadExample.class);
    private static final int MAX_COUNTER = 100;
    private Counter counter;

    public RunnableExample(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("Runnable example "
                + Thread.currentThread().getName() + "was started..");
        while (counter.getCount() < MAX_COUNTER) {
            long count = counter.getCount();
            logger.info(Thread.currentThread().getName() + ": " + ++count);
            counter.setCount(count);
        }
        System.out.println("Runnable example "
                + Thread.currentThread().getName() + "was finished");
    }
}
