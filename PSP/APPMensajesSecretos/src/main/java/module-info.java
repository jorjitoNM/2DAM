module org.example.appmensajessecretos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;


    opens org.example.appmensajessecretos to javafx.fxml;
    exports org.example.appmensajessecretos;
    exports org.example.appmensajessecretos.ui;
    opens org.example.appmensajessecretos.ui to javafx.fxml;
}