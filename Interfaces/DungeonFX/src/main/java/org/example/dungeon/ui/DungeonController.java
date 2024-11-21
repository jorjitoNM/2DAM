package org.example.dungeon.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.dungeon.domain.model.Room;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DungeonController extends JFrame {
    @FXML
    private TreeView<String> tree;
    @FXML
    private TextArea recorrido;
    @FXML
    private TextArea infoHabitacion;
    private Map<String, Room> rooms;
    private Room currentRoom;
    private final List<String> historial;

    public DungeonController() {
        historial = new ArrayList<>();
    }
    public void startDungeon(Map<String, Room> rooms) {
        this.rooms = rooms;
        currentRoom = rooms.get("R0");
        updateRoomDescription();
        updateDungeonTree(rooms);
    }

    public void loadDungeon(File file) {
        DungeonParser parser = new DungeonParser();
        rooms = parser.parseDungeon(file);
        startDungeon(rooms);
    }

    public void move(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String direction = button.getText();
        if (currentRoom != null) {
            String destinationId = currentRoom.getDestination(direction);
            if (destinationId != null) {
                currentRoom = rooms.get(destinationId);
                historial.add("Has viajado al " + direction + " -> " + currentRoom.getDescription() + "\n");
                recorrido.setText(historial.toString());
                updateRoomDescription();
            } else {
                historial.add("No hay una puerta al " + direction + "\n");
                recorrido.setText(historial.toString());
            }
        }
    }

    private void updateRoomDescription() {
        if (currentRoom != null) {
            infoHabitacion.setText(currentRoom.getDescription());
        }
    }

    @FXML
    private void startGame() {
        startDungeon(new DungeonParser().parseDungeon(new File("data/scene.xml")));
    }

    @FXML
    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Dungeon Files", "*.dungeon", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            loadDungeon(selectedFile);
        }
    }

    public void updateDungeonTree(Map<String, Room> rooms) {
        TreeItem<String> root = new TreeItem<>("Dungeon");
        root.setExpanded(true);
        for (Room room : rooms.values()) {
            TreeItem<String> roomNode = new TreeItem<>("Room " + room.getId() + ": " + room.getDescription());
            for (Map.Entry<String, String> door : room.getDoors().entrySet()) {
                roomNode.getChildren().add(new TreeItem<>(door.getKey() + " -> " + door.getValue()));
            }
            root.getChildren().add(roomNode);
        }
        tree.setRoot(root);
    }
}
