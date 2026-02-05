package ConsoleProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankManagementSystem {

	    private List<Account> accounts;
	    private Scanner sc;

	    public BankManagementSystem() {
	        accounts = new ArrayList<>();
	        sc = new Scanner(System.in);
	    }

	    private Account findAccount(int accNo) {
	        for (Account a : accounts) {
	            if (a.getAccNo() == accNo)
	                return a;
	        }
	        return null;
	    }

	    private void createAccount() {
	        System.out.println("--- Create New Account ---");
	        sc.nextLine();
	        System.out.print("Enter account holder name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter initial deposit: ");
	        double amount = sc.nextDouble();

	        Account acc = new Account(name, amount);
	        accounts.add(acc);

	        System.out.println("Account Created Successfully!");
	        acc.displayShort();
	    }

	    private void displayAccount() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        Account acc = findAccount(accNo);
	        if (acc == null) {
	            System.out.println("Account Not Found!");
	            return;
	        }
	        acc.displayFull();
	    }

	    private void deposit() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        Account acc = findAccount(accNo);
	        if (acc == null) {
	            System.out.println("Account Not Found!");
	            return;
	        }

	        System.out.print("Enter amount to deposit: ");
	        double amount = sc.nextDouble();

	        if (acc.deposit(amount)) {
	            System.out.println("Deposit Successful!");
	        } else {
	            System.out.println("Invalid amount.");
	        }
	    }

	    private void withdraw() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        Account acc = findAccount(accNo);
	        if (acc == null) {
	            System.out.println("Account Not Found!");
	            return;
	        }

	        System.out.print("Enter amount to withdraw: ");
	        double amount = sc.nextDouble();

	        if (acc.withdraw(amount)) {
	            System.out.println("Withdrawal Successful!");
	        } else {
	            System.out.println("Insufficient Balance / Invalid Amount.");
	        }
	    }

	    private void transfer() {
	        System.out.print("Enter Source Account No: ");
	        int srcAcc = sc.nextInt();

	        System.out.print("Enter Target Account No: ");
	        int tgtAcc = sc.nextInt();

	        Account src = findAccount(srcAcc);
	        Account tgt = findAccount(tgtAcc);

	        if (src == null || tgt == null) {
	            System.out.println("Invalid account number(s).");
	            return;
	        }

	        System.out.print("Enter amount to transfer: ");
	        double amount = sc.nextDouble();

	        if (src.transferOut(amount, tgtAcc)) {
	            tgt.transferIn(amount, srcAcc);
	            System.out.println("Transfer Successful!");
	        } else {
	            System.out.println("Transfer failed. Check balance.");
	        }
	    }

	    private void updateAccount() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        Account acc = findAccount(accNo);
	        if (acc == null) {
	            System.out.println("Account Not Found!");
	            return;
	        }

	        sc.nextLine();
	        System.out.print("Enter new name: ");
	        String newName = sc.nextLine();
	        acc.setName(newName);

	        System.out.println("Account Updated Successfully!");
	    }

	    private void deleteAccount() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        Account acc = findAccount(accNo);
	        if (acc == null) {
	            System.out.println("Account Not Found!");
	            return;
	        }

	        accounts.remove(acc);
	        System.out.println("Account Deleted Successfully!");
	    }

	    private void listAllAccounts() {
	        System.out.println("--- All Accounts ---");
	        for (Account a : accounts) {
	            a.displayShort();
	        }
	    }

	    private void menu() {
	        System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
	        System.out.println("1. Create Account");
	        System.out.println("2. Display Account");
	        System.out.println("3. Deposit");
	        System.out.println("4. Withdraw");
	        System.out.println("5. Transfer");
	        System.out.println("6. Update Account");
	        System.out.println("7. Delete Account");
	        System.out.println("8. List All Accounts");
	        System.out.println("9. Exit");
	        System.out.print("Enter choice: ");
	    }

	    public void start() {
	        while (true) {
	            menu();
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1 -> createAccount();
	                case 2 -> displayAccount();
	                case 3 -> deposit();
	                case 4 -> withdraw();
	                case 5 -> transfer();
	                case 6 -> updateAccount();
	                case 7 -> deleteAccount();
	                case 8 -> listAllAccounts();
	                case 9 -> {
	                    System.out.println("Thank You!");
	                    return;
	                }
	                default -> System.out.println("Invalid Choice!");
	            }
	        }
	    }

	    public static void main(String[] args) {
	        new BankManagementSystem().start();
	    }
	}	
