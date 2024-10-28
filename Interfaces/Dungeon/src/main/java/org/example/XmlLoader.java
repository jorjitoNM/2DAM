package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlLoader {
    private Map<String, Room> rooms = new HashMap<>();
    public void loadDungeon(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList roomList = doc.getElementsByTagName("room");

            for (int temp = 0; temp < roomList.getLength(); temp++) {
                Node roomNode = roomList.item(temp);

                if (roomNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element roomElement = (Element) roomNode;
                    String roomId = roomElement.getAttribute("id");
                    String description = roomElement.getElementsByTagName("description").item(0).getTextContent();

                    System.out.println("Room ID: " + roomId);
                    System.out.println("Description: " + description);

                    NodeList doorList = roomElement.getElementsByTagName("door");
                    for (int j = 0; j < doorList.getLength(); j++) {
                        Element doorElement = (Element) doorList.item(j);
                        String doorName = doorElement.getAttribute("name");
                        String doorDest = doorElement.getAttribute("dest");
                        System.out.println("Door: " + doorName + ", Destination: " + doorDest);
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String, Room> getRooms() {
        return rooms;
    }
}