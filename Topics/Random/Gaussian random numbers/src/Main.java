import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        double m = sc.nextDouble();

        Random random = new Random(k);

        int check = 0;

        while (true) {
            for (int i = 0; i < n; i++) {

                double randomNumber = random.nextGaussian();
                if (randomNumber > m) {
                    random.setSeed(++k);
                    check = 0;
                    break;
                }
                check++;
            }
            if (check == n) {
                break;
            }
        }
        System.out.println(k);
    }
}