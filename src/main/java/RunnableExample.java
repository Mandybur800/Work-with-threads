public class RunnableExample implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable example "
                + Thread.currentThread().getName() + "was started..");
        System.out.println("TODO:...");
        System.out.println("Runnable example "
                + Thread.currentThread().getName() + "was finished");
    }
}
