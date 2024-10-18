package org.example.appmensajessecretos.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Mensaje;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.domain.servicio.GroupService;
import org.example.appmensajessecretos.domain.servicio.MessageService;
import org.example.appmensajessecretos.domain.servicio.UserService;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class GroupController {

    private final UserService userService;
    private final MessageService messageService;
    private final GroupService groupService;
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
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private Label createGroupError;


    @FXML
    private ListView<Grupo> myChats;


    @FXML
    private TextArea mensaje;
    @FXML
    private Label sendMessageError;


    @FXML
    private ListView<String> groupChats;


    @FXML
    private ListView<String> privateChats;


    @FXML
    private ListView<Usuario> usuarios;


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
        else {
            usuario = userService.addUser(new Usuario(userName.getText(), userPassword.getText()));
            if (usuario == null) {
                if (alertComfirmation(Constantes.USUARIO_MISSING)) {
                    succes = true;
                } else {
                    logInError.setText(Constantes.LOGIN_FAILED);
                }
            } else if (usuario.getName().equals(Constantes.CONTRASEÑA_INCORRECTA)) {
                logInError.setText(Constantes.CONTRASEÑA_INCORRECTA);
            } else
                succes = true;
        }
        if (succes) {
            usuario = new Usuario(userName.getText(), userPassword.getText());
            actualizarUserInfo();
            logInError.setText(Constantes.LOGGED_IN);
            loadUsers();
        }
    }

    private boolean checkLogged() {
        if (usuario == null) {
            logInError.setText(Constantes.NOT_LOGGED);
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
                if (!groupService.findGroup(grupo)) { //se puede quitar al usar Either
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
            else if (!groupService.findUser(userNameDelete.getText(), groupNameDelete.getText())) { //se puede quitar con el Either
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
            if (myChats.getSelectionModel().isEmpty() || mensaje.getText().isBlank())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else if (!messageService.sendGroupMessages(mensaje.getText(), usuario, myChats.getSelectionModel().getSelectedItem()))
                sendMessageError.setText(Constantes.ERROR_SENDING_MESSAGE);
            else
                loadUserGroupChats();
        }
    }

    public void sendMessage() {
        if (checkLogged()) {
            if (mensaje.getText().isBlank() || usuarios.getSelectionModel().isEmpty())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else {
                if (!messageService.sendMessage(new Mensaje(mensaje.getText(), LocalDateTime.now(), usuario, new ArrayList<>(usuarios.getSelectionModel().getSelectedItems()))))
                    sendMessageError.setText(Constantes.ERROR_SENDING_MESSAGE);
                else
                    loadUserChats();
            }
        }
    }

    private void actualizarUserInfo() {
        myChats.getItems().clear();
        myChats.getItems().addAll(groupService.getGroups(usuario));
    }

    public void loadUsers() {
        usuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        usuarios.getItems().clear();
        usuarios.getItems().addAll(userService.loadUsers(usuario));
    }

    private boolean alertComfirmation(String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(Constantes.COMFIRMACION);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        confirmation.close();
        return confirmation.getResult().equals(ButtonType.OK);
    }

    public void loadUserGroupChats() {
        groupChats.getItems().clear();
        messageService.getGroupMessages(myChats.getSelectionModel().getSelectedItem()).forEach(m ->
                groupChats.getItems().add(m.toString())
        );
    }

    private void loadUserChats() {
        privateChats.getItems().clear();
        ArrayList<Usuario> selectedUsers = new ArrayList<>(usuarios.getSelectionModel().getSelectedItems());
        messageService.getMessages(usuario,selectedUsers).forEach(m ->
                privateChats.getItems().add(m.toString())
        );
    }

    public void loadGroupChats() {
        if (!myChats.getSelectionModel().isEmpty())
            loadUserGroupChats();
    }

    public void loadPrivateChats() {
        if (!usuarios.getSelectionModel().isEmpty())
            loadUserChats();
    }
}