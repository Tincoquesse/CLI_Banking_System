import java.util.*;

public class Main {
    static int[] getFirstAndLast(int[] arg) {
        return new int[]{arg[0], arg[arg.length - 1]};
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] result = getFirstAndLast(array);
        Arrays.stream(result).forEach(e -> System.out.print(e + " "));
    }
}
