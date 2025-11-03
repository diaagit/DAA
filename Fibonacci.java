import java.util.Scanner;

public class Fibonacci {

    // Recursive method
    public static int fibonacciRecursive(int n) {
        if (n <= 1) 
            return n;
        
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative method
    public static void fibonacciIterative(int n) {
        int first = 0, second = 1;
        System.out.println("Fibonacci series using Iterative method:");
        for (int i = 0; i < n; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }

    // Main menu-driven program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, n;

        do {
            System.out.println("\n===== Fibonacci Menu =====");
            System.out.println("1. Generate Fibonacci (Iterative)");
            System.out.println("2. Generate Fibonacci (Recursive)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of terms: ");
                    n = sc.nextInt();
                    fibonacciIterative(n);
                    break;

                case 2:
                    System.out.print("Enter number of terms: ");
                    n = sc.nextInt();
                    System.out.println("Fibonacci series using Recursive method:");
                    for (int i = 0; i < n; i++) {
                        System.out.print(fibonacciRecursive(i) + " ");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);

        sc.close();
    }
}


//Recursive	TC: O(2ⁿ)	SC: O(n)	Each call spawns two new calls → exponential growth. Uses recursion stack.
//Iterative (Non-Recursive)	 TC: O(n)	SC: O(1)	Uses only a few variables; most efficient for Fibonacci generation.