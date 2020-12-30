package com.company;

import java.util.Scanner;

//CLASS FOR CONTROLLER

public class Main {

    public static void main(String[] args) {
        //assuming we have several sorted users to query through
        //each user has a checking & savings account
        //this would normally be on the bank side, not atm controller
        User[] users = {new User(12345678,1111,200,510),
                new User(23456781,2222,0,6180999),
                new User(34567812,3333,100000,234671),
                new User(45678123,4444,841,71)};
        //text based for now through stdio
        //insert card
        Scanner input = new Scanner(System.in);
        System.out.println("Please insert your card number: ");
        long card = input.nextLong();
        boolean user_found = false;
        //query user (user must exist in order to have card)
        //this would be different when integrated w bank but for now we iterate since it is a small amount of users
        for(User u : users) {
            if (u.getCard() == card) {
                user_found = true;
                int tries = 3; //3 tries for pin
                boolean verified = false;
                boolean done = false;
                while (!verified && tries > 0) {
                    //insert pin
                    System.out.println("Please enter your 4 digit PIN: ");
                    int pin = input.nextInt();
                    //verify correct pin else loop
                    verified = u.verify(pin);
                    tries --;
                }
                if (!verified && tries <= 0) {
                    System.out.println("Number of tries exceeded.");
                    break;
                }
                input.nextLine();
                while (!done) {
                    //select account
                    System.out.println("Select account [c/s]: "); //choose from checking or savings account
                    String a = input.nextLine();
                    //see balance/deposit/withdraw
                    System.out.println("Select transaction [sb/dep/wd]: "); //choose from checking or savings account
                    String t = input.nextLine();
                    if(t.equals("sb")) {
                        switch (a) {
                            case "c":
                                System.out.println("Checking balance: " + u.getChecking().getBalance());
                                break;
                            case "s":
                                System.out.println("Savings balance: " + u.getSavings().getBalance());
                                break;
                            default:
                                System.out.println("Invalid account selection.");
                                break;
                        }
                    } else if (t.equals("dep")) {
                        System.out.println("Insert the amount you would like to deposit: ");
                        long amount = input.nextLong();
                        input.nextLine();
                        switch (a) {
                            case "c":
                                u.getChecking().deposit(amount);
                                System.out.println("New checking balance: " + u.getChecking().getBalance());
                                break;
                            case "s":
                                u.getSavings().deposit(amount);
                                System.out.println("New savings balance: " + u.getSavings().getBalance());
                                break;
                            default:
                                System.out.println("Invalid account selection.");
                                break;
                        }
                    } else if (t.equals("wd")) {
                        System.out.println("Insert the amount you would like to withdraw: ");
                        long amount = input.nextLong();
                        input.nextLine();
                        switch (a) {
                            case "c":
                                u.getChecking().withdraw(amount);
                                System.out.println("Checking balance: " + u.getChecking().getBalance());
                                break;
                            case "s":
                                u.getSavings().withdraw(amount);
                                System.out.println("Savings balance: " + u.getSavings().getBalance());
                                break;
                            default:
                                System.out.println("Invalid account selection.");
                                break;
                        }
                    } else {
                        System.out.println("Invalid transaction selection.");
                    }
                    //another transaction? [y/n]
                    System.out.println("Would you like to continue with another transaction? [y/n]: ");
                    String another = input.nextLine();
                    done = (another.equals("n"));
                }
            }
            if (user_found) break;
        }
        input.close();
        if (!user_found) {
            System.out.println("User with card number " + card + " was not found.");
        }
    }
}
