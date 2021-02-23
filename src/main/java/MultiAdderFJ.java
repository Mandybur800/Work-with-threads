import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MultiAdderFJ extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 100_000;
    private final List<Integer> numbers;

    public MultiAdderFJ(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected Integer compute() {
        if (numbers.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return processing();
    }

    private Collection<MultiAdderFJ> createSubtasks() {
        List<MultiAdderFJ> dividedTasks = new ArrayList<>();
        dividedTasks.add(new MultiAdderFJ(numbers.subList(0, numbers.size() / 2)));
        dividedTasks.add(new MultiAdderFJ(numbers.subList(numbers.size() / 2, numbers.size())));
        return dividedTasks;
    }

    private Integer processing() {
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }
}
