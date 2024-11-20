module org.example.dungeonfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.dungeonfx to javafx.fxml;
    exports org.example.dungeonfx;
}