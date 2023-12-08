package org.example.model;


import org.example.interfaces.IComponent;

public class Component implements IComponent {
    private String model;
    private double price;

    public Component(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (Model: %s, Price: %.2f)", getClass().getSimpleName(), model, price);
    }

}