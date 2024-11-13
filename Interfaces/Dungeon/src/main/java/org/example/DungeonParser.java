package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DungeonParser {
    private Map<String, Room> rooms = new HashMap<>();

    public Map<String, Room> parseDungeon(File xmlFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList roomList = document.getElementsByTagName("room");

            for (int i = 0; i < roomList.getLength(); i++) {
                Element roomElement = (Element) roomList.item(i);
                String roomId = roomElement.getAttribute("id");
                String description = roomElement.getElementsByTagName("description").item(0).getTextContent();

                Room room = new Room(roomId, description);

                NodeList doorList = roomElement.getElementsByTagName("door");
                for (int j = 0; j < doorList.getLength(); j++) {
                    Element doorElement = (Element) doorList.item(j);
                    String direction = doorElement.getAttribute("name");
                    String destination = doorElement.getAttribute("dest");
                    room.addDoor(direction, destination);
                }
                rooms.put(roomId, room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
