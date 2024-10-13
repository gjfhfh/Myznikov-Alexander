import java.util.List;

public class SortContext {
    private SortStrategy strategy;

    public SortContext(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Integer> sort(List<Integer> list) throws IllegalArgumentException {
        return strategy.sort(list);
    }
}