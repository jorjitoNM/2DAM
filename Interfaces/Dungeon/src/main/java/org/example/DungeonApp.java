package org.example;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DungeonApp {
    private XmlLoader loader;
    private Room currentRoom;
    private StringBuilder recorrido;

    private JFrame frame;
    private JTextArea descriptionArea;
    private JPanel buttonPanel;
    private JTextArea recorridoArea;
    private JTree xmlTree;

    public DungeonApp() {
        loader = new XmlLoader();
        recorrido = new StringBuilder("Recorrido: ");
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Mazmora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem startMenuItem = new JMenuItem("Start");

        loadMenuItem.addActionListener(e -> loadDungeon());

        startMenuItem.addActionListener(e -> startDungeon());

        fileMenu.add(loadMenuItem);
        fileMenu.add(startMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Área de descripción
        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Panel para los botones de las puertas
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1)); // Una columna, tantas filas como puertas

        // Área de recorrido
        recorridoArea = new JTextArea();
        recorridoArea.setEditable(false);
        recorridoArea.setLineWrap(true);
        recorridoArea.setWrapStyleWord(true);

        // Panel principal
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.EAST);
        rightPanel.add(new JScrollPane(recorridoArea), BorderLayout.SOUTH);

        // Panel izquierdo con el árbol XML
        xmlTree = new JTree();
        JScrollPane treeScroll = new JScrollPane(xmlTree);

        frame.add(treeScroll, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        frame.setSize(1280, 720);
        frame.setVisible(true);
    }

    private void loadDungeon() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            loader.loadDungeon(file.getAbsolutePath());
            populateXMLTree();
        }
    }

    private void populateXMLTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Mazmora");
        for (Room room : loader.getRooms().values()) {
            DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(room.getId() + ": " + room.getDescription());
            for (Door door : room.getDoors()) {
                roomNode.add(new DefaultMutableTreeNode(door.getName() + " -> " + door.getDestination()));
            }
            rootNode.add(roomNode);
        }
        xmlTree.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));
        xmlTree.setCellRenderer(new DefaultTreeCellRenderer());
    }

    private void startDungeon() {
        currentRoom = loader.getRooms().get("R0"); // Comienza en la habitación R0
        recorrido.setLength(0); // Resetear el recorrido
        recorrido.append("Recorrido: ");
        updateDescription();
        updateButtonPanel();
        updateRecorrido();
    }

    private void updateDescription() {
        descriptionArea.setText(currentRoom.getDescription());
    }

    private void updateButtonPanel() {
        buttonPanel.removeAll();
        for (Door door : currentRoom.getDoors()) {
            JButton button = new JButton(door.getName());
            button.addActionListener(e -> moveToRoom(door.getDestination()));
            buttonPanel.add(button);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void moveToRoom(String roomId) {
        recorrido.append(currentRoom.getDescription() + " -> ");
        currentRoom = loader.getRooms().get(roomId);
        updateDescription();
        updateButtonPanel();
        updateRecorrido();
    }

    private void updateRecorrido() {
        recorridoArea.setText(recorrido.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DungeonApp::new);
    }
}
