package org.example.model;

import org.example.exceptions.PCAssemblyException;
import org.example.interfaces.IComponent;
import org.example.interfaces.PCAssembler;
import org.example.pc_enum.ComponentType;

import java.util.List;

public class SimplePCAssembler implements PCAssembler {
    private List<IComponent> availableComponents;

    public SimplePCAssembler(List<IComponent> components) {
        this.availableComponents = components;
    }

    @Override
    public PC assemblePC() throws PCAssemblyException {
        try {
            IComponent processor = selectComponent(ComponentType.PROCESSOR);
            IComponent motherboard = selectComponent(ComponentType.MOTHERBOARD);
            IComponent graphicsCard = selectComponent(ComponentType.GRAPHICS_CARD);
            IComponent memory = selectComponent(ComponentType.MEMORY);
            IComponent storage = selectComponent(ComponentType.STORAGE);
            IComponent powerSupply = selectComponent(ComponentType.POWER_SUPPLY);
            IComponent pcCase = selectComponent(ComponentType.CASE);

            return new PC((Processor) processor, (Motherboard) motherboard, (GraphicsCard) graphicsCard, (Memory) memory, (Storage) storage, (PowerSupply) powerSupply, (Case) pcCase, null);
        } catch (Exception e) {
            throw new PCAssemblyException("Failed to assemble PC", this);
        }
    }

    private IComponent selectComponent(ComponentType componentType) throws PCAssemblyException {
        return availableComponents.stream()
                .filter(component -> componentType.equals(getComponentType(component)))
                .findFirst()
                .orElseThrow(() -> new PCAssemblyException("No " + componentType + " available", this));
    }

    private ComponentType getComponentType(IComponent IComponent) {
        if (IComponent instanceof org.example.model.Processor) {
            return ComponentType.PROCESSOR;
        } else if (IComponent instanceof org.example.model.Motherboard) {
            return ComponentType.MOTHERBOARD;
        } else if (IComponent instanceof org.example.model.GraphicsCard) {
            return ComponentType.GRAPHICS_CARD;
        } else if (IComponent instanceof org.example.model.Memory) {
            return ComponentType.MEMORY;
        } else if (IComponent instanceof org.example.model.Storage) {
            return ComponentType.STORAGE;
        } else if (IComponent instanceof org.example.model.PowerSupply) {
            return ComponentType.POWER_SUPPLY;
        } else if (IComponent instanceof org.example.model.Case) {
            return ComponentType.CASE;
        } else {
            return ComponentType.OTHER;
        }
    }
}
