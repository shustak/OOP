package org.example.model;

import org.example.interfaces.IPC;

import java.util.List;

public class PC implements IPC {
    private Processor processor;
    private Motherboard motherboard;
    private GraphicsCard graphicsCard;
    private Memory memory;
    private Storage storage;
    private PowerSupply powerSupply;
    private Case pcCase;
    private List<Component> otherComponents;

    public PC(Processor processor, Motherboard motherboard, GraphicsCard graphicsCard,
              Memory memory, Storage storage, PowerSupply powerSupply,
              Case pcCase, List<Component> otherComponents) {
        this.processor = processor;
        this.motherboard = motherboard;
        this.graphicsCard = graphicsCard;
        this.memory = memory;
        this.storage = storage;
        this.powerSupply = powerSupply;
        this.pcCase = pcCase;
        this.otherComponents = otherComponents;
    }

    @Override
    public Processor getProcessor() {
        return processor;
    }

    @Override
    public Motherboard getMotherboard() {
        return motherboard;
    }

    @Override
    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    @Override
    public Memory getMemory() {
        return memory;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    @Override
    public Case getCase() {
        return pcCase;
    }

    @Override
    public List<Component> getOtherComponents() {
        return otherComponents;
    }

    public double getFrequency() {
        if (processor != null && processor instanceof Processor) {
            return ((Processor) processor).getPerformance();
        }
        return 0.0; // Вернуть 0, если процессор не установлен или не является экземпляром Processor
    }
    // Методы доступа к комплектующим и другие методы, если необходимо

    @Override
    public String toString() {
        return String.format("PC Configuration:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                processor, motherboard, graphicsCard, memory, storage, powerSupply, pcCase, otherComponents);
    }
}
