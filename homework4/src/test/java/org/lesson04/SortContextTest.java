import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortContextTest {

    private SortContext sortContext;

    @BeforeEach
    void setUp() {
        // Инициализация SortContext с двумя стратегиями
        sortContext = new SortContext(Arrays.asList(
                new MergeSortStrategy(),
                new BubbleSortStrategy()
        ));
    }

    @Test
    void testSortWithMergeSort() {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        List<Integer> sortedList = sortContext.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 5, 8), sortedList);
    }

    @Test
    void testSortWithBubbleSort() {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        List<Integer> sortedList = sortContext.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 5, 8), sortedList);
    }

    @Test
    void testSortWithLargeList() {
        List<Integer> largeList = Arrays.asList(new Integer[1001]);
        assertThrows(IllegalArgumentException.class, () -> sortContext.sort(largeList));
    }

    @Test
    void testSortWithEmptyList() {
        List<Integer> emptyList = Arrays.asList();
        List<Integer> sortedList = sortContext.sort(emptyList);
        assertEquals(emptyList, sortedList);
    }

    @Test
    void testSortWithSingleElementList() {
        List<Integer> singleElementList = Arrays.asList(42);
        List<Integer> sortedList = sortContext.sort(singleElementList);
        assertEquals(singleElementList, sortedList);
    }

    @Test
    void testSortWithAlreadySortedList() {
        List<Integer> alreadySortedList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> sortedList = sortContext.sort(alreadySortedList);
        assertEquals(alreadySortedList, sortedList);
    }

    @Test
    void testSortWithReverseSortedList() {
        List<Integer> reverseSortedList = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> sortedList = sortContext.sort(reverseSortedList);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), sortedList);
    }

    @Test
    void testSortWithDuplicateElements() {
        List<Integer> listWithDuplicates = Arrays.asList(5, 3, 8, 1, 2, 3, 5);
        List<Integer> sortedList = sortContext.sort(listWithDuplicates);
        assertEquals(Arrays.asList(1, 2, 3, 3, 5, 5, 8), sortedList);
    }
}