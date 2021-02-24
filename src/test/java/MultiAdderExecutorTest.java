import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MultiAdderExecutorTest {
    private static List<Integer> collect;
    private static MultiAdderExecutor multiAdderExecutor;

    @BeforeAll
    public static void beforeAll() {
        collect = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        multiAdderExecutor = new MultiAdderExecutor();
    }

    @Test
    public void getSum_Ok() {
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 1));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 2));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 3));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 4));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 5));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 6));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 7));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 8));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 9));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 10));
        Assertions.assertEquals(1_000_000, multiAdderExecutor.getSum(collect, 999));
    }

    @Test
    public void getSum_NotOk() {
        Assertions.assertThrows(RuntimeException.class, ()
                        -> multiAdderExecutor.getSum(collect, 0),
                "Was expected exception with 0 or minus threads");
        Assertions.assertThrows(RuntimeException.class, ()
                        -> multiAdderExecutor.getSum(collect, -5),
                "Was expected exception with 0 or minus threads");
        Assertions.assertThrows(RuntimeException.class, ()
                        -> multiAdderExecutor.getSum(collect, 100_000),
                "You should get exception, if have a lot of threads");
    }
}
