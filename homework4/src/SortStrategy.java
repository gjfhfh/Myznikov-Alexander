import java.util.List;

public interface SortStrategy {
    List<Integer> sort(List<Integer> list) throws IllegalArgumentException;
}