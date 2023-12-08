package org.example.model;

import org.example.interfaces.IComponent;

public class Processor implements IComponent {
    private String model;
    private double performance;
    private double price;

    public Processor(String model, double performance, double price) {
        this.model = model;
        this.performance = performance;
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

    public double getPerformance() {
        return performance;
    }

    @Override
    public String toString() {
        return String.format("Processor: %s, Performance: %.2f GHz, Price: %.2f", model, performance, price);
    }

}
