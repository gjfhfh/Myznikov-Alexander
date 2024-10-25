import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);
        SortContext context = new SortContext(Arrays.asList(
                new MergeSortStrategy(),
                new BubbleSortStrategy()
        ));

        try {
            List<Integer> sortedList = context.sort(list);
            System.out.println("Sorted list: " + sortedList);
        } catch (IllegalArgumentException e) {
            System.err.println("Sorting failed: " + e.getMessage());
        }
    }
}