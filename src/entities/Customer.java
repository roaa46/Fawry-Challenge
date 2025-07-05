package entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String address;
    private String mobile;
    private double balance;

    public Customer() {

    }

    public Customer(String name, String address, String mobile, double balance) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
