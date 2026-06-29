package entity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final Lock lock=new ReentrantLock();

    public BankAccount(String accountNumber,double intialBalance){
        this.accountNumber=accountNumber;
        this.balance=intialBalance;
    }
    public double getBalance(){
        return balance;
    }
    public boolean withdraw(double amount){
        lock.lock();
        try{
            if(balance>=amount){
                //simulate network/processing delay to test thread safety in demo
                Thread.sleep(50);
                balance-=amount;
                return true;
            }
            return  false;
        }catch (Exception e){
            Thread.currentThread().interrupt();
            return false;
        }finally {
            lock.unlock();
        }
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +'}';
    }
}
