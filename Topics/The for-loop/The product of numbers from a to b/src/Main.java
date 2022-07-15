import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long multi = 1;
        for (; a < b; a++) {
            multi *= a;
        }
        System.out.println(multi);
    }
}