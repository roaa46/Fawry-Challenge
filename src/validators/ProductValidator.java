package validators;

import entities.products.IsExpirable;
import entities.products.IsExpirableAndShippable;
import entities.Product;
import service.ShoppingService;

import java.util.Map;

public class ProductValidator {

    private ShoppingService shoppingService;

    public ProductValidator(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public void validateQuantity() {
        Map<Product, Integer> products = shoppingService.getCart();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product.getQuantity() < quantity)
                throw new RuntimeException("Unavailable quantity! You can select up to " + product.getQuantity());
        }
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            int quantity = entry.getValue();
            if (quantity <= 0)
                throw new RuntimeException("Product quantity must be greater than zero for product: " + entry.getKey().getName());
        }
    }

    public void validateExpiration() {
        Map<Product, Integer> products = shoppingService.getCart();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if ((product instanceof IsExpirable && ((IsExpirable) product).isExpired()) ||
                    (product instanceof IsExpirableAndShippable && ((IsExpirableAndShippable) product).isExpired())) {
                throw new RuntimeException("Unfortunately, this product is expired for product: " + product.getName());
            }
        }

    }

    public void validateQuantity(Product product, int quantity) {
        if (product.getQuantity() < quantity)
            throw new RuntimeException("Unavailable quantity! You can select up to " + product.getQuantity());
        if (quantity <= 0)
            throw new RuntimeException("Product quantity must be greater than zero");
    }

    public void validateExpiration(Product product) {
        if ((product instanceof IsExpirable && ((IsExpirable) product).isExpired()) || (product instanceof IsExpirableAndShippable && ((IsExpirableAndShippable) product).isExpired())) {
            throw new RuntimeException("Unfortunately, this product is expired");
        }
    }

    public ShoppingService getShoppingService() {
        return shoppingService;
    }

    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
}
