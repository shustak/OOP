package org.example.model;

import org.example.interfaces.IComponent;

import java.util.ArrayList;
import java.util.List;

public class PCShop {
    private List<PC> availablePCs;

    private List<IComponent> availableComponents;

    public PCShop(List<PC> initialPCs, List<IComponent> initialComponent) {
        this.availablePCs = new ArrayList<>(initialPCs);
        this.availableComponents = new ArrayList<>(initialComponent);
    }

    public List<PC> getAvailablePCs() {
        return availablePCs;
    }

    public void addPC(PC pc) {
        availablePCs.add(pc);
    }

    public PC findPCWithHighestCPU() {
        if (availablePCs.isEmpty()) {
            return null;
        }

        PC pcWithHighestCPU = availablePCs.get(0);

        for (PC pc : availablePCs) {
            if (pc.getProcessor().getPerformance() > pcWithHighestCPU.getProcessor().getPerformance()) {
                pcWithHighestCPU = pc;
            }
        }

        return pcWithHighestCPU;
    }

    public List<IComponent> getAvailableComponents() {
        return availableComponents;
    }
}
