import java.util.Scanner;

public class KnapsackDP {
    // Function to solve 0/1 Knapsack using DP
    public static int knapsack(int[] weights, int[] values, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0; // base case
                else if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] values = new int[n];
        int[] weights = new int[n];

        System.out.println("Enter value and weight of each item:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
            weights[i] = sc.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        int maxValue = knapsack(weights, values, capacity, n);
        System.out.println("Maximum value that can be obtained = " + maxValue);
    }
}


/*Enter number of items: 3
Enter value and weight of each item:
60 10
100 20
120 30
Enter capacity of knapsack: 50
Maximum value that can be obtained = 220
 */

 //TC: O(n*capacity) where n is the number of items.
 //SC: O(n*capacity) for the DP table.