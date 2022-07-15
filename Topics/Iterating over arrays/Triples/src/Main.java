import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        int count = 0;
        for (int y = 0; y < size; y++) {
            if (y < size - 2) {
                if (array[y] + 1 == array[y + 1] && array[y + 1] + 1 == array[y + 2]) {  
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
