package org.example.model;

import org.example.interfaces.IComponent;
import org.example.pc_enum.StorageType;

public class Storage implements IComponent {
    private String model;
    private StorageType type;
    private int capacity;
    private double price;

    public Storage(String model, StorageType type, int capacity, double price) {
        this.model = model;
        this.type = type;
        this.capacity = capacity;
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

    public StorageType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("Storage: %s, Type: %s, Capacity: %dGB, Price: %.2f", model, type, capacity, price);
    }
}
