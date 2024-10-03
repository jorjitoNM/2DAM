package org.example.appmensajessecretos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Log4j2
@Component
public class GroupApplication implements ApplicationListener<DIJavafx.StageReadyEvent> {
    private final FXMLLoader fxmlLoader;

    public GroupApplication(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }


    @Override
    public void onApplicationEvent(DIJavafx.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream("hello-view.fxml"));
            stage.setScene(new Scene(fxmlParent));
            stage.show();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
