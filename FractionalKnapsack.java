import java.util.*;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    // Function to calculate maximum value
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> 
            Double.compare((double)b.value / b.weight, (double)a.value / a.weight)
        );

        double totalValue = 0.0; // result value
        int currentWeight = 0;   // current weight in knapsack

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                // Take the whole item
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                // Take the fraction of the remaining capacity
                int remaining = capacity - currentWeight;
                totalValue += (double)item.value * ((double)remaining / item.weight);
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight of item " + (i + 1) + ": ");
            int value = sc.nextInt();
            int weight = sc.nextInt();
            items[i] = new Item(value, weight);
        }

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        double maxValue = getMaxValue(items, capacity);
        System.out.println("\nMaximum value that can be obtained = " + maxValue);
    }
}










/* INPUT:
Enter number of items: 3
Enter value and weight of item 1: 60 10
Enter value and weight of item 2: 100 20
Enter value and weight of item 3: 120 30
Enter capacity of knapsack: 50 

OUTPUT:
Maximum value that can be obtained = 240.0
*/

/*Step	Item Picked	Weight of item	currentWeight before	Can take full?	Weight taken	Value added	totalValue after
1	Item1	10	0	Yes	10	60	60
2	Item2	20	10	Yes	20	100	160
3	Item3	30	30	No	20 (remaining)	120 * (20/30) = 80	240
After step 1: currentWeight = 10, totalValue = 60.
After step 2: currentWeight = 30, totalValue = 160.
Remaining capacity before item3 = 50 - 30 = 20. Item3 weight is 30, so we take fraction 20/30 = 2/3. Value added = 120 * 2/3 = 80. Final totalValue = 160 + 80 = 240. Knapsack now full.
Final answer: Maximum value = 240.0 */