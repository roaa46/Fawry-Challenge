import data.TestScenarios;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting test scenarios...");

        TestScenarios.validShippableOnly();
        TestScenarios.validNonShippableOnly();
        TestScenarios.validMixedProducts();
        TestScenarios.invalidZeroQuantity();
        TestScenarios.invalidUnavailableQuantity();
        TestScenarios.invalidEmptyCart();
        TestScenarios.invalidInsufficientBalance();
        TestScenarios.invalidExpiredProduct();

        System.out.println("Test scenarios completed.");
    }
}
