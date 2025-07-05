package entities.products;

import entities.Product;
import util.Expiration;
import util.Shipping;

import java.time.LocalDate;

public class IsExpirableAndShippable extends Product implements Expiration, Shipping {

    private LocalDate expiryDate;
    private double weight;

    public IsExpirableAndShippable(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return null;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isExpired() {
        return this.expiryDate.isBefore(LocalDate.now());
    }
}
