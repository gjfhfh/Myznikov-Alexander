import java.util.ArrayList;
import java.util.List;

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> list) throws IllegalArgumentException {
        if (list.size() > 52) {
            throw new IllegalArgumentException("Список слишком большой для сортировки пузырьком");
        }
        List<Integer> sortedList = new ArrayList<>(list);
        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(i) > sortedList.get(j)) {
                    int temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }
}