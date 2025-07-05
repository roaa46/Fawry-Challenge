package service;

import entities.products.IsExpirableAndShippable;
import entities.products.IsShippable;
import entities.Product;
import validators.CartValidator;
import validators.ProductValidator;

import java.util.HashMap;
import java.util.Map;

public class ShoppingService {

    Map<Product, Integer> cart = new HashMap<>();
    private double shippingFees;
    private ProductValidator productValidator;
    private CartValidator cartValidator;

    public ShoppingService() {
        this.cartValidator = new CartValidator(this);
        this.productValidator = new ProductValidator(this);
    }

    public ShoppingService(Map<Product, Integer> cart, double shippingFees) {
        this.cart = cart;
        this.shippingFees = shippingFees;
    }


    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void addToCart(Product product, int quantity) {

        productValidator.validateQuantity(product, quantity);

        productValidator.validateExpiration(product);

        cart.put(product, quantity);

    }

    public double calculateOrderSubtotal() {
        double priceOfAllProducts = 0;
        for (Map.Entry<Product, Integer> product : cart.entrySet()) {
            priceOfAllProducts += (product.getKey().getPrice() * product.getValue());
        }
        return priceOfAllProducts;
    }

    public double calculateTotalWeight() {

        cartValidator.validateCart();

        double totalWeight = 0;

        for (Map.Entry<Product, Integer> productEntry : cart.entrySet()) {

            Product product = productEntry.getKey();
            if (product instanceof IsShippable) {
                totalWeight += ((((IsShippable) product).getWeight() * productEntry.getValue()) / 1000.0);
            } else if (product instanceof IsExpirableAndShippable) {
                totalWeight += ((((IsExpirableAndShippable) product).getWeight() * productEntry.getValue()) / 1000.0);
            }

        }

        return totalWeight;
    }

    public double calculateShippingFees() {
        double shippingFeesPerKg = 25;
        return this.shippingFees = calculateTotalWeight() * shippingFeesPerKg;
    }

    public double calculateTotalCost() {
        return calculateShippingFees() + calculateOrderSubtotal();
    }
}
