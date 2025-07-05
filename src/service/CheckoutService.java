package service;

import entities.Customer;
import entities.products.IsShippable;
import entities.Product;
import validators.BalanceValidator;
import validators.CartValidator;
import validators.ProductValidator;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {

    private Customer customer;
    private ShoppingService shoppingService;
    private ShippingService shippingService;
    private ProductValidator productValidator;
    private CartValidator cartValidator;
    private BalanceValidator balanceValidator;

    public CheckoutService(Customer customer, ShoppingService shoppingService) {
        this.customer = customer;
        this.shoppingService = shoppingService;
        this.productValidator = new ProductValidator(shoppingService);
        this.cartValidator = new CartValidator(shoppingService);
        this.balanceValidator = new BalanceValidator(customer, shoppingService);
        this.shippingService = new ShippingService();
    }

    public void startValidation() {
        productValidator.validateExpiration();
        productValidator.validateQuantity();
        cartValidator.validateCart();
        balanceValidator.validateBalance();
    }

    public void checkout() {

        startValidation();

        Map<Product, Integer> shippableProducts = new HashMap<>();

        for (Map.Entry<Product, Integer> entry : shoppingService.getCart().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.setQuantity(product.getQuantity() - quantity);

            if (product instanceof IsShippable) {
                shippableProducts.put((IsShippable) product, quantity);
            }
        }

        shippingService.shippingReport(shippableProducts);
        System.out.println("\n\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : shoppingService.getCart().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + "        " + product.getPrice() * quantity);
        }
        System.out.println("-------------------");
        System.out.println("Subtotal         " + shoppingService.calculateOrderSubtotal());
        System.out.println("Shipping         " + shoppingService.calculateShippingFees());
        System.out.println("Total            " + shoppingService.calculateTotalCost());

    }


}
