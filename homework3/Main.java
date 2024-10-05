import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = 2;
        Object[] data = new Object[n];
        for (int i = 0; i < n; i++) {
            data[i] = input.nextInt();
        }
        СustomArray.CustomArrayList<Integer> arrey = new СustomArray.CustomArrayList<>(data);
        arrey.add(30);
        arrey.print();
        arrey.remove(2);
        arrey.print();
        arrey.get(1);
    }
}
