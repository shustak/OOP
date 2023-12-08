package org.example.model;

import org.example.interfaces.IComponent;

public class GraphicsCard implements IComponent {
    private String model;
    private int videoMemory;
    private double performance;
    private double price;

    public GraphicsCard(String model, int videoMemory, double performance, double price) {
        this.model = model;
        this.videoMemory = videoMemory;
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

    public int getVideoMemory() {
        return videoMemory;
    }

    public double getPerformance() {
        return performance;
    }

    @Override
    public String toString() {
        return String.format("Graphics Card: %s, Video Memory: %dGB, Performance: %.2f, Price: %.2f", model, videoMemory, performance, price);
    }
}
