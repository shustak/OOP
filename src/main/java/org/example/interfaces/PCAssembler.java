package org.example.interfaces;

import org.example.exceptions.PCAssemblyException;

public interface PCAssembler {
    IPC assemblePC() throws PCAssemblyException;
}
