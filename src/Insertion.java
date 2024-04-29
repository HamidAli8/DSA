
public class Insertion {
    public static void main(String[] args) {
        String[] arr = {"9", "3", "10", "8", "2", "7", "A", "4", "5", "6"};

        System.out.println("This is the insertion Sort code!!");

        System.out.println("Deck before sorting:");
        printarray(arr);

   
        insertionSort(arr, 0, 9);

        System.out.println("\nDeck after sorting:");
        printarray(arr);
    }

   
    static void insertionSort(String[] deck, int startIndex, int endIndex) {
        for (int i = startIndex + 1; i <= endIndex; ++i) {
            String key = deck[i];
            int j = i - 1;

            while (j >= startIndex && compare(deck[j], key) > 0) {
                deck[j + 1] = deck[j];
                j = j - 1;
            }
            deck[j + 1] = key;
        }
    }

    static int compare(String a, String b) {
        if (a.equals("A")) {
            return (b.equals("A")) ? 0 : -1; 
        } else if (b.equals("A")) {
            return 1; 
        } else {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        }
    }

    static void printarray(String[] arr) {
        for (String value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
