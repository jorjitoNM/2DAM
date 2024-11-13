package org.example;

import javax.swing.*;
import java.io.File;
import java.util.Map;

public class DungeonController {
    private Map<String, Room> rooms;
    private JTextArea recorridoArea;
    private JTextArea descriptionArea;
    private Room currentRoom;
    private DungeonInterface dungeonInterface;

    public DungeonController(JTextArea recorridoArea, JTextArea descriptionArea, DungeonInterface dungeonInterface) {
        this.recorridoArea = recorridoArea;
        this.descriptionArea = descriptionArea;
        this.dungeonInterface = dungeonInterface;
    }

    public void startDungeon(Map<String, Room> rooms) {
        this.rooms = rooms;
        currentRoom = rooms.get("R0");
        updateRoomDescription();
        dungeonInterface.updateDungeonTree(rooms);
    }

    public void loadDungeon(File file) {
        DungeonParser parser = new DungeonParser();
        rooms = parser.parseDungeon(file);
        startDungeon(rooms);
    }

    public void move(String direction) {
        if (currentRoom != null) {
            String destinationId = currentRoom.getDestination(direction);
            if (destinationId != null) {
                currentRoom = rooms.get(destinationId);
                recorridoArea.append("Has viajado al " + direction + " -> " + currentRoom.getDescription() + "\n");
                updateRoomDescription();
            } else {
                recorridoArea.append("No hay una puerta al " + direction + "\n");
            }
        }
    }

    private void updateRoomDescription() {
        if (currentRoom != null) {
            descriptionArea.setText(currentRoom.getDescription());
        }
    }
}
