public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ThreadExample threadExample1 = new ThreadExample(counter);
        threadExample1.start();
        RunnableExample runnableExample = new RunnableExample(counter);
        new Thread(runnableExample).start();
    }
}
