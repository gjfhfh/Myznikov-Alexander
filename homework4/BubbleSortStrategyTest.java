import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortStrategyTest {
    @Test
    public void testSort() {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        SortStrategy strategy = new BubbleSortStrategy();
        List<Integer> sortedList = strategy.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 5, 8), sortedList);
    }

    @Test
    public void testSortWithLargeList() {
        List<Integer> largeList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53);
        SortStrategy strategy = new BubbleSortStrategy();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.sort(largeList);
        });
        String expectedMessage = "Список слишком большой для сортировки пузырьком";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}