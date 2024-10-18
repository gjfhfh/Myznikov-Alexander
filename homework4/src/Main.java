import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 3, 8, 1, 2);

        // Выбор алгоритма сортировки
        SortStrategy strategy = new MergeSortStrategy();
        // SortStrategy strategy = new BubbleSortStrategy();

        // Создание контекста с выбранной стратегией
        SortContext context = new SortContext(strategy);

        // Сортировка списка
        List<Integer> sortedList = context.sort(list);

        // Вывод отсортированного списка
        System.out.println(sortedList);
    }
}
