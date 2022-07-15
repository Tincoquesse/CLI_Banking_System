package banking.service;

import banking.repository.BankDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class BankService {

    private final BankDatabase bankDatabase;

    public BankService(BankDatabase bankDatabase) {
        this.bankDatabase = bankDatabase;
    }

    private long generateCardNumber() {

        Long numberOfAccount = ThreadLocalRandom.current().nextLong(4000_0000_0000_000L, 4000_0099_9999_999L);
        String tempCardNumber = numberOfAccount.toString();

        List<Integer> isolatedNumbers = Arrays.stream(tempCardNumber.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < isolatedNumbers.size(); i += 2) {
            int increaseValue = isolatedNumbers.get(i) * 2;
            isolatedNumbers.set(i, increaseValue);
        }
        for (int i = 0; i < isolatedNumbers.size(); i++) {
            if (isolatedNumbers.get(i) > 9) {
                int minusValue = isolatedNumbers.get(i) - 9;
                isolatedNumbers.set(i, minusValue);
            }
        }
        int sumOfIntegers = isolatedNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        int lastDigit = 10 - (sumOfIntegers % 10);
        if (lastDigit == 10) {
            lastDigit = 0;
        }
        String temp = tempCardNumber.concat(String.valueOf(lastDigit));
        return Long.parseUnsignedLong(temp);
    }

    public void generateAccount() {

        long numberOfAccount = generateCardNumber();
        int pin = ThreadLocalRandom.current().nextInt(1000, 9999);

        this.bankDatabase.saveCardToDatabase(numberOfAccount, pin);
        System.out.printf("Your card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n\n", numberOfAccount, pin);
    }

    public void logIntoAccount(Scanner scanner) {
        boolean flag = true;

        System.out.println("Enter your card number:");
        Long cardNumber = scanner.nextLong();
        System.out.println("Enter your PIN:");
        Long pinNumber = scanner.nextLong();

        if (bankDatabase.getPinFromDatabase(cardNumber) == pinNumber) {
            System.out.println("You have successfully logged in!\n");
            while (flag) {
                System.out.println("1. Balance\n" +
                        "2. Add income\n" +
                        "3. Do transfer\n" +
                        "4. Close account\n" +
                        "5. Log out\n" +
                        "0. Exit\n");

                switch (scanner.nextInt()) {
                    case 1:
                        int balance = bankDatabase.getBalanceFromUserAccount(cardNumber);
                        System.out.println("Balance: " + balance);
                        break;
                    case 2:
                        addIncomeToAccount(scanner, cardNumber);
                        break;
                    case 3:
                        transferToOtherAccount(scanner, cardNumber);
                        break;
                    case 4:
                        closeAccount(cardNumber);
                        System.out.println("The account has been closed!");
                        break;
                    case 5:
                        System.out.println("You have successfully logged out!");
                        flag = false;
                        break;
                    case 0:
                        System.out.println("Bye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Something goes wrong\n");
                        break;
                }
            }
        } else {
            System.out.println("Wrong card number or PIN!\n");
        }
    }

    private void closeAccount(Long cardNumber) {
        bankDatabase.closeAccount(cardNumber);
    }

    private void transferToOtherAccount(Scanner scanner, Long cardNumber) {

        System.out.println("Transfer\n" +
                "Enter card number:");
        String cardNumberToTransferFor = scanner.next();
        if (isCardNumberValid(cardNumberToTransferFor)){
            if (bankDatabase.isCardNumberExist(cardNumberToTransferFor)){
                System.out.println("Enter how much money you want to transfer:");
                int amount = scanner.nextInt();
                doTransfer(amount, cardNumber, cardNumberToTransferFor);
            }else {
                System.out.println("Such a card does not exist.");
            }
        }else {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        }
    }

    private void doTransfer(int amount, Long cardNumber, String cardNumberToTransferFor) {
        if (bankDatabase.getBalanceFromUserAccount(cardNumber) >= amount){
            bankDatabase.addIncome(amount, Long.parseLong(cardNumberToTransferFor));
            bankDatabase.updateSendersBalance(amount, cardNumber);
            System.out.println("Success!");
        } else {
            System.out.println("Not enough money!");
        }
    }

    private void addIncomeToAccount(Scanner scanner, Long accountNumber) {

        System.out.println("Enter income:");
        Integer income = Integer.parseInt(scanner.next());
        scanner.skip("[\r\n]+");
        bankDatabase.addIncome(income, accountNumber);
        System.out.println("Income was added!");
    }

    public boolean isCardNumberValid(String cardNumber) {

        int[] cardIntArray=new int[cardNumber.length()];

        for(int i=0;i<cardNumber.length();i++) {
            char c= cardNumber.charAt(i);
            cardIntArray[i]=  Integer.parseInt(""+c);
        }

        for(int i=cardIntArray.length - 2; i >= 0; i = i - 2) {
            int num = cardIntArray[i];
            num = num * 2;
            if(num>9) {
                num = num%10 + num/10;
            }
            cardIntArray[i]=num;
        }
        int sum = sumDigits(cardIntArray);

        return sum % 10 == 0;
    }

    public static int sumDigits(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
