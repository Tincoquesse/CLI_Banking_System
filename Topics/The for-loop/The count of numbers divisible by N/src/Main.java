import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int sum = 0;
        for (; a <= b; a++) {
            sum = a % n == 0 ? sum+1 : sum;
        }
        System.out.println(sum);
    }
}