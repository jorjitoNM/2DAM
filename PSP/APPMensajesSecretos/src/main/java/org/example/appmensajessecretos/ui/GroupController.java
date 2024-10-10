package org.example.appmensajessecretos.ui;

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
import org.example.appmensajessecretos.utilities.LogConstantes;
import org.springframework.stereotype.Component;

@Component
public class GroupController {

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
    private ListView<Grupo> myChats;


    @FXML
    private TextArea mensaje;
    @FXML
    private Label sendMessageError;


    @FXML
    private ListView<String> mensajesGrupo;


    @FXML
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private Label createGroupError;


    public GroupController(UserService userService, MessageService messageService, GroupService groupService) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupService = groupService;
        usuario = null;
    }

    public void iniciarSesion() {
        boolean succes = false;
        if (userName.getText().isEmpty() || userPassword.getText().isEmpty())
            logInError.setText(Constantes.RELLENE_CAMPOS);
        else if (!userService.findUser(new Usuario(userName.getText(), userPassword.getText()))) {
            if (alertComfirmation(Constantes.USUARIO_MISSING)) {
                usuario = new Usuario(userName.getText(), userPassword.getText());
                userService.addUser(usuario);
                succes = true;
            } else {
                logInError.setText(LogConstantes.LOGIN_FAILED);
            }
        } else
            succes = true;
        if (succes) {
            usuario = new Usuario(userName.getText(), userPassword.getText());
            actualizarUserInfo();
            logInError.setText(LogConstantes.LOGGED_IN);
        }
    }

    private boolean checkLogged() {
        if (usuario == null) {
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
                } else {
                    createGroupError.setText(Constantes.GROUP_CREATED);
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
                } else if (!groupService.joinGroup(usuario, grupo)) {
                    joinGroupError.setText(Constantes.ERROR_JOINING_GROUP);
                } else {
                    joinGroupError.setText(Constantes.JOINED_GROUP);
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
            } else {
                if (alertComfirmation(Constantes.MENSAJE_ELIMINAR_USUARIO)) {
                    if (!groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText())) {
                        deleteError.setText(Constantes.ERROR_DELETING_USER);
                    } else {
                        deleteError.setText(Constantes.MEMBER_DELETED);
                        actualizarUserInfo();
                    }
                }
            }
        }
    }
    public void sendGroupMessage() {
        if (checkLogged()) {
            if (mensaje.getText().isEmpty() || myChats.getEditingIndex() < 0)
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else if (!messageService.sendGroupMessages(mensaje.getText(),usuario,myChats.getSelectionModel().getSelectedItem()))
                sendMessageError.setText(Constantes.ERROR_SENDING_MESSAGE);
            else
                loadUserChats();
        }
    }
    public void sendMessage () {
        if (checkLogged()) {
            if (mensaje.getText().isEmpty())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
        }
    }

    private void actualizarUserInfo() {
        myChats.getItems().clear();
        ObservableList<Grupo> chats = FXCollections.observableList(groupService.getGroups(usuario));
        myChats.getItems().addAll(chats);
    }

    private boolean alertComfirmation (String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(Constantes.COMFIRMACION);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        confirmation.close();
        return confirmation.getResult().equals(ButtonType.OK);
    }

    public void loadUserChats() {
        if (checkLogged()) {
            messageService.getGroupMessages(myChats.getSelectionModel().getSelectedItem()).forEach(m ->
                    mensajesGrupo.getItems().add(m.toString())
            );
        }
    }
}