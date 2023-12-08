package org.example.helper;

import org.example.interfaces.IComponent;
import org.example.model.*;
import org.example.pc_enum.FormFactor;
import org.example.pc_enum.StorageType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonComputerFile {

    public static void writeToFile(String fileName, List<PC> computers) {
        JSONArray jsonArray = new JSONArray();
        for (PC computer : computers) {
            jsonArray.put(computerToJson(computer));
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonArray.toString(4));  // Using 4 spaces for indentation
            System.out.println("Computers data saved to JSON file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<PC> readFromFile(String fileName) {
        List<PC> computers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PC pc = jsonToPC(jsonObject);
                computers.add(pc);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return computers;
    }

    private static JSONObject computerToJson(PC computer) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("processor", componentToJson(computer.getProcessor()));
        jsonObject.put("motherboard", componentToJson(computer.getMotherboard()));
        jsonObject.put("graphicsCard", componentToJson(computer.getGraphicsCard()));
        jsonObject.put("memory", componentToJson(computer.getMemory()));
        jsonObject.put("storage", componentToJson(computer.getStorage()));
        jsonObject.put("powerSupply", componentToJson(computer.getPowerSupply()));
        jsonObject.put("pcCase", componentToJson(computer.getCase()));
        JSONArray otherComponentsArray = new JSONArray();
        for (IComponent IComponent : computer.getOtherComponents()) {
            otherComponentsArray.put(componentToJson( IComponent));
        }
        jsonObject.put("otherComponents", otherComponentsArray);
        return jsonObject;
    }

    private static JSONObject componentToJson(IComponent component) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", component.getModel());
        jsonObject.put("price", component.getPrice());

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
    private static PC jsonToPC(JSONObject jsonObject){
        Processor processor = jsonToProcessor(jsonObject.getJSONObject("processor"));
        Motherboard motherboard = jsonToMotherboard(jsonObject.getJSONObject("motherboard"));
        GraphicsCard graphicsCard = jsonToGraphicsCard(jsonObject.getJSONObject("graphicsCard"));
        Memory memory = jsonToMemory(jsonObject.getJSONObject("memory"));
        Storage storage = jsonToStorage(jsonObject.getJSONObject("storage"));
        PowerSupply powerSupply = jsonToPowerSupply(jsonObject.getJSONObject("powerSupply"));
        Case pcCase = jsonToCase(jsonObject.getJSONObject("pcCase"));

        return new PC(processor, motherboard, graphicsCard, memory, storage, powerSupply, pcCase, null);
    }


    private static Processor jsonToProcessor(JSONObject processor) {
        String model = processor.getString("model");
        double price = processor.getDouble("price");
        double performance = processor.getDouble("performance");
        return new Processor(model, performance, price);
    }

    private static Motherboard jsonToMotherboard(JSONObject motherboard) {
        String model = motherboard.getString("model");
        double price = motherboard.getDouble("price");
        FormFactor formFactor = FormFactor.valueOf(motherboard.getString("formFactor"));
        return new Motherboard(model, formFactor, price);
    }

    private static GraphicsCard jsonToGraphicsCard(JSONObject graphicsCard) {
        String model = graphicsCard.getString("model");
        double price = graphicsCard.getDouble("price");
        int videoMemory = graphicsCard.getInt("videoMemory");
        double performance = graphicsCard.getDouble("performance");
        return new GraphicsCard(model, videoMemory, performance, price);
    }

    private static Memory jsonToMemory(JSONObject memory) {
        String model = memory.getString("model");
        double price = memory.getDouble("price");
        int capacity = memory.getInt("capacity");
        double speed = memory.getDouble("speed");
        return new Memory(model, capacity, speed, price);
    }

    private static Storage jsonToStorage(JSONObject storage) {
        String model = storage.getString("model");
        double price = storage.getDouble("price");
        StorageType type = StorageType.valueOf(storage.getString("type"));
        int capacity = storage.getInt("capacity2");
        return new Storage(model, type, capacity, price);
    }

    private static PowerSupply jsonToPowerSupply(JSONObject powerSupply) {
        String model = powerSupply.getString("model");
        double price = powerSupply.getDouble("price");
        int wattage = powerSupply.getInt("wattage");
        return new PowerSupply(model, wattage, price);
    }

    private static Case jsonToCase(JSONObject pcCase) {
        String model = pcCase.getString("model");
        double price = pcCase.getDouble("price");
        FormFactor formFactor = FormFactor.valueOf(pcCase.getString("formFactor"));
        return new Case(model, formFactor, price);
    }
}