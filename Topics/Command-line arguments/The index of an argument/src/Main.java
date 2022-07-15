
import java.util.Arrays;

class Problem {
    public static void main(String[] args) {

        int index = -1;
        for (String string: args) {
            if (string.equals("test")) {
                index = Arrays.asList(args).indexOf(string);
            }
        }
        System.out.println(index);
    }
}