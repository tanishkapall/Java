import java.util.Scanner;

public class Calculator {
    public int num1, num2;
    public int ch;

    public int addNums(int n1, int n2) {
        return n1 + n2;
    }

    public int subNums(int n1, int n2) {
        return n1 - n2;
    }

    public int mulNums(int n1, int n2) {
        return n1 * n2;
    }

    public float divNums(int n1, int n2) {
        return (float) n1 / n2;
    }

    public int calcMod(int n1, int n2) {
        return n1 % n2;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("Choose the arithmetic operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Modulus");
            System.out.println("0. Exit");

            c.ch = sc.nextInt();

            if (c.ch == 0) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println("Enter the first number:");
            c.num1 = sc.nextInt();

            System.out.println("Enter the second number:");
            c.num2 = sc.nextInt();

            switch (c.ch) {
                case 1:
                    System.out.println("Sum: " + c.addNums(c.num1, c.num2));
                    break;

                case 2:
                    System.out.println("Difference: " + c.subNums(c.num1, c.num2));
                    break;

                case 3:
                    System.out.println("Multiplication: " + c.mulNums(c.num1, c.num2));
                    break;

                case 4:
                    if (c.num2 == 0) {
                        System.out.println("Cannot divide by zero.");
                    } else {
                        System.out.println("Division: " + c.divNums(c.num1, c.num2));
                    }
                    break;

                case 5:
                    System.out.println("Remainder: " + c.calcMod(c.num1, c.num2));
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }


    }
}
