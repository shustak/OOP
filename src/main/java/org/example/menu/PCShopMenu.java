package org.example.menu;

import org.example.exceptions.PCAssemblyException;
import org.example.interfaces.PCAssembler;
import org.example.model.PC;
import org.example.model.PCShop;
import org.example.model.SimplePCAssembler;

import java.util.List;

public class PCShopMenu {
    private PCShop pcShop;

    public PCShopMenu(PCShop pcShop) {
        this.pcShop = pcShop;
    }

    public void displayMainMenu() {
        System.out.println("PC Shop Main Menu:");
        System.out.println("1. Browse PCs");
        System.out.println("2. Assemble PC");
        System.out.println("3. Search PC with Highest CPU Frequency");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                browsePCs();
                break;
            case 2:
                try {
                    assemblePC();
                } catch (PCAssemblyException e) {
                    System.out.println("Failed to assemble PC: " + e.getMessage());
                }
                break;
            case 3:
                searchHighestCPUPC();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private void browsePCs() {
        List<PC> availablePCs = pcShop.getAvailablePCs();
        System.out.println("Available PCs:");
        for (int i = 0; i < availablePCs.size(); i++) {
            System.out.println((i + 1) + ". " + availablePCs.get(i));
        }
        System.out.println();
    }

    private void assemblePC() throws PCAssemblyException {
        PCAssembler pcAssembler = new SimplePCAssembler(pcShop.getAvailableComponents());
        PC assembledPC = (PC) pcAssembler.assemblePC();
        pcShop.addPC(assembledPC);
        System.out.println("PC Assembled and Added to Inventory.\n");
    }

    private void searchHighestCPUPC() {
        PC pcWithHighestCPU = pcShop.findPCWithHighestCPU();
        if (pcWithHighestCPU != null) {
            System.out.println("PC with Highest CPU Frequency:\n" + pcWithHighestCPU);
        } else {
            System.out.println("No PCs available.");
        }
    }

    private void exit() {
        System.out.println("Exiting PC Shop. Goodbye!");
        System.exit(0);
    }
}
