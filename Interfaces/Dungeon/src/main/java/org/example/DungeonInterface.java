package org.example;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class DungeonInterface extends JFrame {
    private DungeonController controller;
    private JTree tree;  // Árbol para mostrar habitaciones y puertas
    private DefaultTreeModel treeModel;

    public DungeonInterface() {
        setTitle("Dungeon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem startOption = new JMenuItem("Start");
        JMenuItem loadOption = new JMenuItem("Load");

        startOption.addActionListener(e -> startGame());
        loadOption.addActionListener(e -> loadGame());

        optionsMenu.add(startOption);
        optionsMenu.add(loadOption);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);

        // Árbol de navegación inicial
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("dungeon");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        JScrollPane treeScroll = new JScrollPane(tree);

        // Panel de botones cardinales y áreas de texto
        JPanel cardinalButtonsPanel = new JPanel(new BorderLayout());

        JButton northButton = new JButton("Norte");
        JButton southButton = new JButton("Sur");
        JButton eastButton = new JButton("Este");
        JButton westButton = new JButton("Oeste");

        cardinalButtonsPanel.add(northButton, BorderLayout.NORTH);
        cardinalButtonsPanel.add(southButton, BorderLayout.SOUTH);
        cardinalButtonsPanel.add(eastButton, BorderLayout.EAST);
        cardinalButtonsPanel.add(westButton, BorderLayout.WEST);

        JTextArea descriptionArea = new JTextArea("Descripción de la celda actual...");
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        cardinalButtonsPanel.add(descriptionArea, BorderLayout.CENTER);

        JTextArea recorridoArea = new JTextArea("Recorrido:\n");
        recorridoArea.setEditable(false);
        recorridoArea.setLineWrap(true);
        recorridoArea.setWrapStyleWord(true);
        JScrollPane recorridoScroll = new JScrollPane(recorridoArea);

        JPanel dungeonContentPanel = new JPanel(new BorderLayout());
        dungeonContentPanel.add(cardinalButtonsPanel, BorderLayout.CENTER);
        dungeonContentPanel.add(recorridoScroll, BorderLayout.SOUTH);

        JPanel dungeonPanel = new JPanel(new BorderLayout());
        dungeonPanel.add(dungeonContentPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, dungeonPanel);
        splitPane.setDividerLocation(200);
        add(splitPane);

        // Controlador de la mazmorra
        controller = new DungeonController(recorridoArea, descriptionArea, this);

        northButton.addActionListener(e -> controller.move("Norte"));
        southButton.addActionListener(e -> controller.move("Sur"));
        eastButton.addActionListener(e -> controller.move("Este"));
        westButton.addActionListener(e -> controller.move("Oeste"));
    }

    private void startGame() {
        controller.startDungeon(new DungeonParser().parseDungeon(new File("data/scene.xml")));
    }

    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            controller.loadDungeon(selectedFile);
        }
    }

    // Método para actualizar el árbol de habitaciones y puertas
    public void updateDungeonTree(Map<String, Room> rooms) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("dungeon");
        for (Room room : rooms.values()) {
            DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode("Room " + room.getId() + ": " + room.getDescription());
            for (Map.Entry<String, String> door : room.getDoors().entrySet()) {
                roomNode.add(new DefaultMutableTreeNode(door.getKey() + " -> " + door.getValue()));
            }
            root.add(roomNode);
        }
        treeModel.setRoot(root);
        treeModel.reload();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DungeonInterface frame = new DungeonInterface();
            frame.setVisible(true);
        });
    }
}
