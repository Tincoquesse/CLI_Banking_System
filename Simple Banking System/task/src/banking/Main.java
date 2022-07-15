package banking;

import banking.repository.BankDatabase;
import banking.service.BankService;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        BankDatabase bankDatabase = new BankDatabase(args[1]);
        BankService bankService = new BankService(bankDatabase);

        while (flag) {
            System.out.println("1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit\n");
            switch (scanner.nextInt()) {
                case 1:
                    bankService.generateAccount();
                    break;
                case 2:
                    bankService.logIntoAccount(scanner);
                    break;
                case 0:
                    System.out.println("Bye!");
                    flag = false;
                    break;
                default:
                    System.out.println("Something goes wrong\n");
            }
        }
    }
}