package org.example.model;

import org.example.interfaces.IComponent;
import org.example.pc_enum.FormFactor;

public class Motherboard implements IComponent {
    private String model;
    private FormFactor formFactor;
    private double price;

    public Motherboard(String model, FormFactor formFactor, double price) {
        this.model = model;
        this.formFactor = formFactor;
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

    public FormFactor getFormFactor() {
        return formFactor;
    }

    @Override
    public String toString() {
        return String.format("Motherboard: %s, Form Factor: %s, Price: %.2f", model, formFactor, price);
    }
}
