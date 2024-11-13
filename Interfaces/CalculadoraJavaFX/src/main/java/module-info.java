module org.example.calculadorajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.calculadorajavafx to javafx.fxml;
    exports org.example.calculadorajavafx;
}