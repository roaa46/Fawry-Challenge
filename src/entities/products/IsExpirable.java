package entities.products;

import entities.Product;
import util.Expiration;

import java.time.LocalDate;

public class IsExpirable extends Product implements Expiration {

    private LocalDate expiryDate;

    public IsExpirable(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return this.expiryDate.isBefore(LocalDate.now());
    }
}
