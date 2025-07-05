import entities.Customer;
import entities.products.IsExpirable;
import entities.products.IsExpirableAndShippable;
import entities.products.IsShippable;
import service.CheckoutService;
import service.ShoppingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n************************************************");
        System.out.println("Valid case with only shippable products");
        System.out.println("************************************************");
        try {

            IsExpirableAndShippable cheese = new IsExpirableAndShippable(
                    "Cheese", 100, 15, LocalDate.now().plusDays(60), 100);
            IsShippable tv = new IsShippable("TV", 1000, 5, 2600);


            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);


            ShoppingService shoppingService = new ShoppingService();


            shoppingService.getCart().put(cheese, 2);
            shoppingService.getCart().put(tv, 1);


            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n****************************************");
        System.out.println("Valid case with only non-shippable product:");
        System.out.println("****************************************");
        try {

            IsExpirable scratch = new IsExpirable("Mobile scratch card", 100, 10, LocalDate.now().plusDays(30));

            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);

            ShoppingService shoppingService = new ShoppingService();

            shoppingService.getCart().put(scratch, 3);

            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n******************************************************");
        System.out.println("Valid case with shippable and non-shippable products:");
        System.out.println("******************************************************");
        try {

            IsExpirableAndShippable cheese = new IsExpirableAndShippable(
                    "Cheese", 100, 15, LocalDate.now().plusDays(60), 100);
            IsShippable tv = new IsShippable("TV", 1000, 5, 2500);
            IsExpirable biscuits = new IsExpirable("Biscuits", 50, 10, LocalDate.now().plusDays(30));


            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);


            ShoppingService shoppingService = new ShoppingService();


            shoppingService.getCart().put(cheese, 2);
            shoppingService.getCart().put(tv, 1);
            shoppingService.getCart().put(biscuits, 1);


            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println("\n*******************************");
        System.out.println("Invalid case (zero quantity):");
        System.out.println("*******************************");
        try {

            IsExpirableAndShippable cheese = new IsExpirableAndShippable(
                    "Cheese", 100, 15, LocalDate.now().plusDays(60), 100);

            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);

            ShoppingService shoppingService = new ShoppingService();

            shoppingService.getCart().put(cheese, 0);

            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n**********************************************");
        System.out.println("Invalid case (unavailable quantity):");
        System.out.println("**********************************************");
        try {

            IsExpirable biscuits = new IsExpirable("Biscuits", 50, 10, LocalDate.now().plusDays(30));

            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);

            ShoppingService shoppingService = new ShoppingService();

            shoppingService.getCart().put(biscuits, 15);

            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n****************************");
        System.out.println("Invalid case (empty cart):");
        System.out.println("****************************");
        try {
            // Create customer
            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);

            ShoppingService cart = new ShoppingService();

            CheckoutService checkoutService = new CheckoutService(customer, cart);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n**************************************");
        System.out.println("Invalid case (insufficient balance):");
        System.out.println("**************************************");
        try {

            IsShippable tv = new IsShippable("TV", 1000, 5, 2500);

            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 500);

            ShoppingService shoppingService = new ShoppingService();

            shoppingService.getCart().put(tv, 2);


            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n***********************************");
        System.out.println("Invalid case (expired product):");
        System.out.println("***********************************");
        try {

            IsExpirable biscuits = new IsExpirable("Biscuits", 50, 10, LocalDate.now().minusDays(30));


            Customer customer = new Customer("Roaa", "Menoufia", "01012345678", 2000);


            ShoppingService shoppingService = new ShoppingService();


            shoppingService.getCart().put(biscuits, 1);


            CheckoutService checkoutService = new CheckoutService(customer, shoppingService);
            checkoutService.checkout();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
