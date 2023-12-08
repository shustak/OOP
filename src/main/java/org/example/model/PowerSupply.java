package org.example.model;

import org.example.interfaces.IComponent;

public class PowerSupply implements IComponent {
    private String model;
    private int wattage;
    private double price;

    public PowerSupply(String model, int wattage, double price) {
        this.model = model;
        this.wattage = wattage;
        this.price = price;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getWattage() {
        return wattage;
    }

    @Override
    public String toString() {
        return String.format("Power Supply: %s, Wattage: %dW, Price: %.2f", model, wattage, price);
    }
}
