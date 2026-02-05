package ConsoleProject;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Account {


        private static int nextAccNo = 1001;

	    private int accNo;
	    private String name;
	    private double balance;
	    private List<Transaction> transactions;

	    public Account(String name, double initialDeposit) {
	        this.accNo = nextAccNo++;
	        this.name = name;
	        this.balance = initialDeposit;
	        this.transactions = new ArrayList<>();

	        if (initialDeposit > 0) {
	            addTransaction("Initial Deposit", initialDeposit);
	        }
	    }

	    public int getAccNo() { 
	    	return accNo; 
	    }
	    public String getName() { 
	    	return name; 
	    }
	    public double getBalance() { 
	    	return balance; 
	    }

	    public void setName(String newName) { 
	    	this.name = newName; 
	    }

	    public boolean deposit(double amount) {
	        if (amount <= 0) return false;
	        balance += amount;
	        addTransaction("Deposit", amount);
	        return true;
	    }

	    public boolean withdraw(double amount) {
	        if (amount <= 0 || amount > balance) return false;
	        balance -= amount;
	        addTransaction("Withdraw", amount);
	        return true;
	    }

	    public boolean transferOut(double amount, int targetAccNo) {
	        if (amount <= 0 || amount > balance) 
	        	return false;
	        balance -= amount;
	        addTransaction("Transfer Out to " + targetAccNo, amount);
	        return true;
	    }

	    public void transferIn(double amount, int fromAccNo) {
	        balance += amount;
	        addTransaction("Transfer In from " + fromAccNo, amount);
	    }

	    private void addTransaction(String type, double amount) {
	        Transaction t = new Transaction(type, amount, balance, LocalDateTime.now());
	        transactions.add(t);
	    }

	    public void displayShort() {
	        System.out.printf("AccNo: %d | Name: %s | Balance: %.2f%n", accNo, name, balance);
	    }

	    public void displayFull() {
	        System.out.println("=======================================");
	        System.out.println("Account Number : " + accNo);
	        System.out.println("Name           : " + name);
	        System.out.printf("Balance        : %.2f%n", balance);
	        System.out.println("----- Transactions -----");

	        if (transactions.isEmpty()) {
	            System.out.println("No transactions available.");
	        } else {
	            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            for (Transaction t : transactions) {
	                System.out.printf("%s | %s | Amount: %.2f | Balance After: %.2f%n",
	                        t.getTimestamp().format(fmt),
	                        t.getType(),
	                        t.getAmount(),
	                        t.getBalanceAfter());
	            }
	        }
	        System.out.println("=======================================");
	    }
	}
