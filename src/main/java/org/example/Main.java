package org.example;

import org.example.helper.JsonComponentsFile;
import org.example.helper.JsonComputerFile;
import org.example.interfaces.IComponent;
import org.example.model.PC;
import org.example.model.PCShop;
import org.example.menu.PCShopMenu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PC> computers = JsonComputerFile.readFromFile("src/main/resources/computers.json");
        List<IComponent> components = JsonComponentsFile.readFromFile("src/main/resources/components.json");

        PCShop pcShop = new PCShop(computers, components);

        PCShopMenu pcShopMenu = new PCShopMenu(pcShop);

        while (true) {
            pcShopMenu.displayMainMenu();
            int choice = getUserChoice();
            pcShopMenu.handleMainMenuChoice(choice);
        }
    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}