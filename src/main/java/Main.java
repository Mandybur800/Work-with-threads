public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ThreadExample threadExample = new ThreadExample(counter);
        threadExample.start();
        RunnableExample runnableExample = new RunnableExample(counter);
        new Thread(runnableExample).start();
    }
}
