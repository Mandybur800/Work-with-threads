import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiAdderExecutor implements Callable<Integer> {
    private static final int LIMIT = 1000;
    private final List<Integer> numbers;

    public MultiAdderExecutor(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }

    public static int getSum(List<Integer> list, int threads) {
        if (threads <= 0 || threads > LIMIT) {
            throw new RuntimeException("You try to create incorrect number of threads");
        }
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += list.size() / threads) {
            int to = Math.min((i + list.size() / threads), list.size());
            tasks.add(new MultiAdderExecutor(list.subList(i, to)));
        }
        try {
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            int result = 0;
            for (Future<Integer> future : futures) {
                result += future.get();
            }
            executor.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Sorry, we can't complete adding", e);
        }
    }
}
