package entities.products;

import entities.Product;
import util.Shipping;

public class IsShippable extends Product implements Shipping {

    private double weight;

    public IsShippable(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


}
