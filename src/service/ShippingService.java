package service;

import entities.Product;
import util.Shipping;
import java.text.DecimalFormat;
import java.util.Map;

public class ShippingService {


    public void shippingReport(Map<Product, Integer> ShoppingService) {

        double totalWeightKg = 0;
        DecimalFormat df = new DecimalFormat("#.###");

        System.out.println("** Shipment notice ** ");
        for (Map.Entry<Product, Integer> productEntry : ShoppingService.entrySet()) {

            Product product = productEntry.getKey();
            int quantity = productEntry.getValue();

            if (product instanceof Shipping) {

                double weightPerUnitGrams = ((Shipping) product).getWeight();
                double totalProductWeightGrams = weightPerUnitGrams * quantity;
                double totalProductWeightKg = totalProductWeightGrams / 1000.0;

                System.out.println(quantity + "x " + product.getName() + "      " + df.format(totalProductWeightGrams) + "g");

                totalWeightKg += totalProductWeightKg;
            }
        }
        System.out.println("Total package weight " + df.format(totalWeightKg) + "kg");
    }



}
