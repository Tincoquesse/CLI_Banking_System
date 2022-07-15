import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int sum = 0;
        while (input != 0) {
            sum += input;
            if (sum >= 1000) {
                System.out.println(sum - 1000);
                break;
            }
            input = scanner.nextInt();
        }
        if (input == 0) {
            System.out.println(sum);
        }
    }
}