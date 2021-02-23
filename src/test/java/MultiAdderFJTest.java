import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MultiAdderFJTest {
    private static List<Integer> collect;
    private static List<Integer> collectHalfMillion;
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    static void beforeAll() {
        forkJoinPool = ForkJoinPool.commonPool();
        collect = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        collectHalfMillion = Stream.iterate(1, n -> n).limit(500_000).collect(Collectors.toList());
    }

    @Test
    void getSum_Ok() {
        Assertions.assertEquals(0, forkJoinPool.invoke(new MultiAdderFJ(new ArrayList<>())));
        Assertions.assertEquals(1_000_000, forkJoinPool.invoke(new MultiAdderFJ(collect)));
        Assertions.assertEquals(500_000,
                forkJoinPool.invoke(new MultiAdderFJ(collectHalfMillion)));
    }
}
