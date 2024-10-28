package org.example;

import java.util.ArrayList;
import java.util.List;

class Room {
    private String id;
    private String description;
    private List<Door> doors;

    public Room(String id, String description) {
        this.id = id;
        this.description = description;
        this.doors = new ArrayList<>();
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Door> getDoors() {
        return doors;
    }
}
