module org.example.dungeonfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.dungeon to javafx.fxml;
    exports org.example.dungeon;
    exports org.example.dungeon.ui;
    opens org.example.dungeon.ui to javafx.fxml;
}