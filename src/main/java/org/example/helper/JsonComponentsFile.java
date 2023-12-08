package org.example.helper;

import org.example.interfaces.IComponent;
import org.example.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.example.pc_enum.FormFactor;
import org.example.pc_enum.StorageType;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonComponentsFile {

    public static void writeToFile(String fileName, List<IComponent> components) {
        JSONArray jsonArray = new JSONArray();
        for (IComponent component : components) {
            jsonArray.put(componentsToJson(component));
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonArray.toString(4));  // Using 4 spaces for indentation
            System.out.println("Components data saved to JSON file");
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    public static List<IComponent> readFromFile(String fileName) {
        List<IComponent> components = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            StringBuilder jsonString = new StringBuilder();
            for (String line : lines) {
                jsonString.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                IComponent component = jsonToComponent(jsonObject);
                components.add(component);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return components;
    }

    private static JSONObject componentsToJson(IComponent component) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", component.getModel());
        jsonObject.put("price", component.getPrice());

        // Добавим специфичные поля для каждого типа компонента
        if (component instanceof Processor) {
            Processor processor = (Processor) component;
            jsonObject.put("performance", processor.getPerformance());
            jsonObject.put("type", "Processor");
        } else if (component instanceof Motherboard) {
            Motherboard motherboard = (Motherboard) component;
            jsonObject.put("socketType", motherboard.getFormFactor());
            jsonObject.put("type", "Motherboard");
        } else if (component instanceof GraphicsCard) {
            GraphicsCard graphicsCard = (GraphicsCard) component;
            jsonObject.put("videoMemory", graphicsCard.getVideoMemory());
            jsonObject.put("type", "GraphicsCard");
        } else if (component instanceof Memory) {
            Memory memory = (Memory) component;
            jsonObject.put("capacity", memory.getCapacity());
            jsonObject.put("speed", memory.getSpeed());
            jsonObject.put("type", "Memory");
        } else if (component instanceof Storage) {
            Storage storage = (Storage) component;
            jsonObject.put("type", storage.getType());
            jsonObject.put("capacity", storage.getCapacity());
            jsonObject.put("type", "Storage");
        } else if (component instanceof PowerSupply) {
            PowerSupply powerSupply = (PowerSupply) component;
            jsonObject.put("wattage", powerSupply.getWattage());
            jsonObject.put("type", "PowerSupply");
        } else if (component instanceof Case) {
            Case pcCase = (Case) component;
            jsonObject.put("formFactor", pcCase.getFormFactor());
            jsonObject.put("type", "Case");
        }

        return jsonObject;
    }

    private static IComponent jsonToComponent(JSONObject jsonObject) {

        String model = jsonObject.getString("model");
        double price = jsonObject.getDouble("price");

        if (jsonObject.has("processor")) {
            double performance = jsonObject.getDouble("performance");
            return new Processor(model, performance, price);
        } else if (jsonObject.has("motherboard")) {
            FormFactor socketType = FormFactor.valueOf(jsonObject.getString("socketType"));
            return new Motherboard(model, socketType, price);
        } else if (jsonObject.has("graphicsCard")) {
            int videoMemory = jsonObject.getInt("videoMemory");
            double performance = jsonObject.getDouble("performance");
            return new GraphicsCard(model, videoMemory, performance, price);
        } else if (jsonObject.has("memory")) {
            int capacity = jsonObject.getInt("capacity");
            double speed = jsonObject.getDouble("speed");
            return new Memory(model, capacity, speed, price);
        } else if (jsonObject.has("storage")) {
            StorageType type = StorageType.valueOf(jsonObject.getString("storageType"));
            int capacity = jsonObject.getInt("capacity2");
            return new Storage(model, type, capacity, price);
        } else if (jsonObject.has("powerSupply")) {
            int wattage = jsonObject.getInt("wattage");
            return new PowerSupply(model, wattage, price);
        } else if (jsonObject.has("case")) {
            FormFactor formFactor = FormFactor.valueOf(jsonObject.getString("formFactor"));
            return new Case(model, formFactor, price);
        } else {

        }

        return null;
    }
}
