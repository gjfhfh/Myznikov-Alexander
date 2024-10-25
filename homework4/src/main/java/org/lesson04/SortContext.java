import java.util.List;

public class SortContext {
    private List<SortStrategy> strategies;

    public SortContext(List<SortStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<Integer> sort(List<Integer> list) throws IllegalArgumentException {
        for (SortStrategy strategy : strategies) {
            try {
                return strategy.sort(list);
            } catch (IllegalArgumentException e) {
                // Log the exception and try the next strategy
                System.err.println("Strategy " + strategy.getClass().getSimpleName() + " failed: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("No suitable sorting strategy found for the given list.");
    }
}