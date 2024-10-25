import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortStrategy implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> list) throws IllegalArgumentException {
        if (list.size() > 52) {
            throw new IllegalArgumentException("Список слишком большой для сортировки слиянием");
        }
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return sortedList;
    }
}