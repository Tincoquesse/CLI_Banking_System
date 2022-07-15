import java.util.Scanner;

public class Main {

    public static String prepareFullName(String firstName, String lastName) {
        if (firstName == null && lastName == null) {
            return "";
        } else if (lastName == null) {
            return firstName;
        } else if (firstName == null) {
            return lastName;
        } else {
            return firstName.concat(" " + lastName);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String firstName = scanner.nextLine();
        firstName = "null".equals(firstName) ? null : firstName;

        String lastName = scanner.nextLine();
        lastName = "null".equals(lastName) ? null : lastName;

        System.out.println(prepareFullName(firstName, lastName));
    }
}
