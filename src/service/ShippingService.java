package service;

import entities.products.IsShippable;
import entities.Product;

import java.util.Map;

public class ShippingService {

    public void shippingReport(Map<Product, Integer> ShoppingService) {

        double totalWeight = 0;

        System.out.println("** Shipment notice ** ");
        for (Map.Entry<Product, Integer> productEntry : ShoppingService.entrySet()) {

            Product product = productEntry.getKey();
            int quantity = productEntry.getValue();

            if (product instanceof IsShippable) {

                double w = ((IsShippable) product).getWeight();

                System.out.println(quantity + "x " + product.getName() + "      " + w + "g");

                totalWeight += ((w * quantity) / 1000);

            }
            System.out.println("Total package weight " + totalWeight + "kg");
        }
    }

}
