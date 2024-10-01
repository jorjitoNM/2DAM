package org.example.appmensajessecretos.ui;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.message.Message;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.servicio.GroupService;
import org.example.appmensajessecretos.domain.servicio.MessageService;
import org.example.appmensajessecretos.utilities.Constantes;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.domain.servicio.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.appmensajessecretos.utilities.LogConstantes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class GruopController {

        private UserService userService;
    private MessageService messageService;
    private GroupService groupService;
    private Usuario usuario;



    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;
    @FXML
    private Label logInError;


    @FXML
    private TextField groupName;
    @FXML
    private TextField groupPassword;
    @FXML
    private Label joinGroupError;


    @FXML
    private TextField groupNameDelete;
    @FXML
    private TextField userNameDelete;
    @FXML
    private Label deleteError;

    @FXML
    private Label misChats;
    @FXML
    private ListView<String> myChats;


    @FXML
    private TextArea mensaje;
    @FXML
    private Label sendMessageError;

    @FXML
    private Label chatActivoLabel;
    @FXML
    private AnchorPane chatActivo;


    @FXML
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private Label createGroupError;


    public GruopController() {
        userService = new UserService();
        groupService = new GroupService();
        messageService = new MessageService();
        usuario = null;
    }

    public void iniciarSesion() {
        boolean succes = false;
        if (userName.getText().isEmpty() || userPassword.getText().isEmpty())
            logInError.setText(Constantes.RELLENE_CAMPOS);
        else if (!userService.findUser(new Usuario(userName.getText(), userPassword.getText()))) {
            Alert create = new Alert(Alert.AlertType.CONFIRMATION);
            create.setTitle(Constantes.CREACION_USUARIO);
            create.setHeaderText(Constantes.USUARIO_MISSING);
            create.showAndWait();
            if (create.getResult() == ButtonType.OK) {
                usuario = new Usuario(userName.getText(), userPassword.getText());
                userService.addUser(usuario);
                succes = true;
                log.info(LogConstantes.NUEVO_USUARIO);
            } else {
                log.info(LogConstantes.LOGIN_FAILED);
                logInError.setText(LogConstantes.LOGIN_FAILED);
            }
        } else
            succes = true;
        if (succes) {
            usuario = new Usuario(userName.getText(), userPassword.getText());
            actualizarUserInfo();
            log.info(LogConstantes.LOGGED_IN);
            logInError.setText(LogConstantes.LOGGED_IN);
        }
    }

    private boolean checkLogged() {
        if (usuario == null) {
            log.info(LogConstantes.NOT_LOGGED);
            joinGroupError.setText(Constantes.NOT_LOGGED);
            return false;
        } else
            return true;
    }

    public void createGroup() {
        if (checkLogged()) {
            if (createGroupName.getText().isEmpty() || createGroupPassword.getText().isEmpty())
                createGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText());
                if (!groupService.createGroup(grupo)) {
                    createGroupError.setText(Constantes.ERROR_CREATING_GROUP);
                    log.error(LogConstantes.ERROR_CREATING_GROUP);
                } else {
                    createGroupError.setText(Constantes.GROUP_CREATED);
                    log.info(Constantes.GROUP_CREATED);
                    actualizarUserInfo();
                }
            }
        }
    }

    public void joinGroup() {
        if (checkLogged()) {
            if (groupName.getText().isEmpty() || groupPassword.getText().isEmpty())
                joinGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText());
                if (!groupService.findGroup(grupo)) {
                    joinGroupError.setText(Constantes.GRUPO_NO_EXISTE);
                    log.error(Constantes.GRUPO_NO_EXISTE);
                } else if (!groupService.joinGroup(usuario, grupo)) {
                    joinGroupError.setText(Constantes.ERROR_JOINING_GROUP);
                    log.error(Constantes.ERROR_JOINING_GROUP);
                } else {
                    joinGroupError.setText(Constantes.JOINED_GROUP);
                    log.info(Constantes.JOINED_GROUP);
                    actualizarUserInfo();
                }
            }
        }
    }

    public void comfirmDelete() {
        if (checkLogged()) {
            if (userNameDelete.getText().isEmpty() || groupNameDelete.getText().isEmpty())
                deleteError.setText(Constantes.RELLENE_CAMPOS);
            else if (!groupService.findUser(userNameDelete.getText(), groupNameDelete.getText())) {
                deleteError.setText(Constantes.USER_NOT_FOUND);
                log.info(LogConstantes.USER_NOT_FOUND);
            } else {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle(Constantes.COMFIRMACION);
                confirmation.setHeaderText(Constantes.MENSAJE_ELIMINAR_USUARIO);
                confirmation.showAndWait();
                if (confirmation.getResult() == ButtonType.OK) {
                    confirmation.close();
                    if (!groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText())) {
                        log.info(LogConstantes.ERROR_DELETING_USER);
                        deleteError.setText(Constantes.ERROR_DELETING_USER);
                    } else {
                        deleteError.setText(Constantes.MEMBER_DELETED);
                        log.info(LogConstantes.MEMBER_DELETED);
                        actualizarUserInfo();
                    }
                }
            }
        }
    }
    public void sendMessage () {
        if (checkLogged()) {
            if (this.mensaje.getText().isEmpty() || !myChats.isFocused())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else if (!messageService.send(this.mensaje.getText(),usuario,myChats.getSelectionModel().getSelectedItem()))

        }
    }

    public void selectUser() {
        TextInputDialog selectUser = new TextInputDialog();
        selectUser.setTitle("Enviar mensaje a:");
        selectUser.setContentText("Introduzca el nombre del destinatario");
        Optional<String> respuesta = selectUser.showAndWait();
    }


    private void actualizarUserInfo() {
        ObservableList<Grupo> chats = FXCollections.observableList(groupService.getGroups(usuario));
        List<String> chatsString = new ArrayList<>();
        chats.forEach(c -> chatsString.add(c.toString()));
        ObservableList<String> chatsFormatted = FXCollections.observableArrayList(chatsString);
        myChats.setItems(chatsFormatted);
    }

    public void enviarMensaje() {
        /*if (!messageChecks())
            log.error(Constantes.LOG_MISSING_FIELDS);
        else{
            Mensaje mensaje = new Mensaje(this.mensaje.getText(), LocalDateTime.now(), usuario, new Grupo(myChats.getEditingIndex()));
        }*/
    }

    private boolean messageChecks() {
        return (myChats.getEditingIndex() != -1) && (!mensaje.getText().isEmpty());
    }
}