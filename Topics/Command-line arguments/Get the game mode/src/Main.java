import java.util.List;

class Problem {
    public static void main(String[] args) {
        int index = List.of(args).indexOf("mode");
        String print = index % 2 == 0 ? args[index + 1] : "default";
        System.out.println(print);
    }
}
