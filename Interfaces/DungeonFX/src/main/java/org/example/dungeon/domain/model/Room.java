package org.example.dungeon.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String id;
    private String description;
    private Map<String, String> doors = new HashMap<>();

    public Room(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void addDoor(String direction, String destination) {
        doors.put(direction, destination);
    }

    public String getDestination(String direction) {
        return doors.get(direction);
    }

    public Map<String, String> getDoors() {
        return doors;
    }
}
