import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while (input != 0) {
            System.out.println(input % 2 == 0 ? "even" : "odd");
            input = scanner.nextInt();
        }

    }
}
