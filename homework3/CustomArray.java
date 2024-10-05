import java.util.Arrays;

public class СustomArray {

    public static class CustomArrayList<A> implements Interface.method<A> {

        private Object[] arr;

        public CustomArrayList(Object[] arr) {
            this.arr = arr;
        }
        @Override
        public void add(A elem) {
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr[arr.length - 1] = elem;
        }

        @Override
        public void get(int index) {
            System.out.println(arr[index]);
        }

        @Override
        public void remove(int index) {
            Object[] arr1 = new Object[arr.length - 1];
            for (int i = 0; i < arr.length - 1; i++) {
                if (i > index) arr1[i] = arr[i + 1];
                else if (i < index) arr1[i] = arr[i];
            }
            arr = arr1;
        }

        @Override
        public void print() {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

    }
}
