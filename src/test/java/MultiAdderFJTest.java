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
    private static List<Integer> collectHalfBillion;
    private static List<Integer> collectionIncremented;
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    public static void beforeAll() {
        forkJoinPool = ForkJoinPool.commonPool();
        collect = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        collectHalfMillion = Stream.iterate(1, n -> n).limit(500_000).collect(Collectors.toList());
        collectHalfBillion = Stream.iterate(1, n -> n).limit(500_000_000).collect(Collectors.toList());
        collectionIncremented = Stream
                .iterate(1, n -> n + 1).limit(1_000_000).collect(Collectors.toList());
    }

    @Test
    public void getSum_Ok() {
        Assertions.assertEquals(0, forkJoinPool.invoke(new MultiAdderFJ(new ArrayList<>())));
        Assertions.assertEquals(55, forkJoinPool.invoke(new MultiAdderFJ(List.of(10,10,20,15))));
        Assertions.assertEquals(1_000_000, forkJoinPool.invoke(new MultiAdderFJ(collect)));
        Assertions.assertEquals(1_784_293_664,
                forkJoinPool.invoke(new MultiAdderFJ(collectionIncremented)));
        Assertions.assertEquals(500_000,
                forkJoinPool.invoke(new MultiAdderFJ(collectHalfMillion)));
        Assertions.assertEquals(500_000_000,
                forkJoinPool.invoke(new MultiAdderFJ(collectHalfBillion)));
    }
}
