public class ThreadExample extends Thread {
    private static final int MAX_COUNTER = 100;
    private final Counter index;

    public ThreadExample(Counter index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("Thread example " + Thread.currentThread().getName() + "was started..");
        while (index.getCount() < MAX_COUNTER) {
            int count = index.getCount();
            System.out.println(Thread.currentThread().getName() + ": " + ++count);
            index.setCount(count);
        }
        System.out.println("Thread example " + Thread.currentThread().getName() + "was finished");
    }
}
