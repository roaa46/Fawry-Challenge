package validators;

import entities.Product;
import service.ShoppingService;

import java.util.Map;

public class CartValidator {

    private ShoppingService shoppingService;

    public CartValidator(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public void validateCart() {

        if (shoppingService.getCart().isEmpty())
            throw new RuntimeException("Your cart is empty, please insert products");

        int totalQuantity = 0;
        for (Map.Entry<Product, Integer> entry : shoppingService.getCart().entrySet()) {
            totalQuantity += entry.getValue();
        }
        if (totalQuantity == 0)
            throw new RuntimeException("Product quantity must be greater than zero for all products in the cart");
    }

    public ShoppingService getShoppingService() {
        return shoppingService;
    }

    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
}
