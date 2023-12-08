package org.example.model;

import org.example.interfaces.IComponent;

public class Memory implements IComponent {
    private String model;
    private int capacity;
    private double speed;
    private double price;

    public Memory(String model, int capacity, double speed, double price) {
        this.model = model;
        this.capacity = capacity;
        this.speed = speed;
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

    public int getCapacity() {
        return capacity;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return String.format("Memory: %s, Capacity: %dGB, Speed: %.2f GHz, Price: %.2f", model, capacity, speed, price);
    }
}
