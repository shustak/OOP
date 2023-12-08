package org.example.interfaces;

import org.example.model.Component;

import java.util.List;

public interface IPC {
    IComponent getProcessor();
    IComponent getMotherboard();
    IComponent getGraphicsCard();
    IComponent getMemory();
    IComponent getStorage();
    IComponent getPowerSupply();
    IComponent getCase();
    List<Component> getOtherComponents();
}
