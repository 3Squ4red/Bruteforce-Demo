import java.util.ArrayList;

class Permutation {
    static ArrayList<String> permutedResult = new ArrayList<>();

    static <T> void addAllRecursive(T[] elements, char delimiter) {
        addAllRecursive(elements.length, elements, delimiter);
    }

    private static <T> void addAllRecursive(int n, T[] elements, char delimiter) {

        if(n == 1) {
            addArray(elements, delimiter);
        } else {
            for(int i = 0; i < n-1; i++) {
                addAllRecursive(n - 1, elements, delimiter);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            addAllRecursive(n - 1, elements, delimiter);
        }
    }
    private static <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private static <T> void addArray(T[] elements, char delimiter) {
        String delimiterSpace = delimiter + "";
        String pass = "";
        for (T word : elements) pass += word + delimiterSpace;
        permutedResult.add(pass);
    }
}
// i;my;cricket;dog;house;pet;love
