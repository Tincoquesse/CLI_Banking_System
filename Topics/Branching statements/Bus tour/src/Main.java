import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int busHeight = scanner.nextInt();
        int bridgeNumber = scanner.nextInt();
        boolean crash = false;
        for (int i = 1; i <= bridgeNumber; i++) {
            int bridgeHeight = scanner.nextInt();
            if (busHeight >= bridgeHeight) {
                System.out.println("Will crash on bridge " + i);
                crash = true;
                break;
            }
        }
        if (!crash) {
            System.out.println("Will not crash");
        }
    }
}
