package validators;

import entities.Customer;
import service.ShoppingService;

public class BalanceValidator {

    private Customer customer;
    private ShoppingService shoppingService;
    private double cost;

    public BalanceValidator() {
        this.customer = new Customer();
        this.shoppingService = new ShoppingService();
        this.cost = shoppingService.calculateTotalCost();
    }


    public BalanceValidator(Customer customer, ShoppingService shoppingService) {
        this.customer = customer;
        this.shoppingService = shoppingService;
        this.cost = shoppingService.calculateTotalCost();
    }

    public void validateBalance() {
        if (cost > customer.getBalance())
            throw new RuntimeException("unfortunately, Your Balance is insufficient");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShoppingService getShoppingService() {
        return shoppingService;
    }

    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
