import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int temp = 0;
        while (input != 0) {
            temp = temp > input ? temp : input;
            input = scanner.nextInt();
        }
        System.out.println(temp);
    }
}