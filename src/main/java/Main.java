public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ThreadExample threadExample1 = new ThreadExample(counter);
        threadExample1.start();
        ThreadExample threadExample2 = new ThreadExample(counter);
        threadExample2.start();
        RunnableExample runnableExample = new RunnableExample();
        new Thread(runnableExample).start();
    }
}
