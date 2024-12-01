package org.example.appmensajessecretos.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.service.GroupService;
import org.example.appmensajessecretos.domain.service.MessageService;
import org.example.appmensajessecretos.domain.service.UserService;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

@Component
public class GroupController {

    private final UserService userService;
    private final MessageService messageService;
    private final GroupService groupService;
    private Usuario user;


    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;


    @FXML
    private TextField groupName;
    @FXML
    private TextField groupPassword;


    @FXML
    private TextField groupNameDelete;
    @FXML
    private TextField userNameDelete;


    @FXML
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private CheckBox isPrivate;


    @FXML
    private ListView<Grupo> myChats;


    @FXML
    private TextArea message;


    @FXML
    private ListView<String> chat;


    @FXML
    private ListView<Usuario> usuarios;


    public GroupController(UserService userService, MessageService messageService, GroupService groupService) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupService = groupService;
        user = null;
    }


    public void logIn() {
        userName.setCursor(Cursor.WAIT);
        userService.logIn(new Usuario(userName.getText(), userPassword.getText()))
                .thenAccept(result -> result.peek(ok -> {
                            user = ok;
                            updateUserInfo();
                            loadUsers();
                            Platform.runLater(() -> showInfo(Constantes.LOGGED_IN));
                        })
                        .peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }

    private void createUser() {
        if (confirmationAlert(Constantes.USUARIO_MISSING)) {
            userName.setCursor(Cursor.WAIT);
            userService.addUser(new Usuario(userName.getText(), userPassword.getText()))
                    .thenAccept(result -> result.peek(ok -> logIn())
                            .peekLeft(error -> Platform.runLater(() -> showError(error))));
            userName.setCursor(Cursor.DEFAULT);
        }
    }

    public void showInfo(String info) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(Constantes.INFO);
        infoAlert.setHeaderText(Constantes.ACTION_COMPLETED);
        infoAlert.setContentText(info);
        infoAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        infoAlert.showAndWait();
    }


    private void showError(Error error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(Constantes.ERROR);
        errorAlert.setHeaderText(Constantes.ERROR);
        errorAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        String errorMessage = "";
        switch (error) {
            case DataBaseError e -> {
                switch (e) {
                    case DataBaseError.ACTION_FAILED -> errorMessage = Constantes.DATABASE_FAILED;
                    case DataBaseError.ERROR_IN_FETCH -> errorMessage = Constantes.DATABASE_CONECCTION_FAILED;
                    case DataBaseError.ERROR_READING_FILE -> errorMessage = Constantes.ERROR_READING_FILE;
                }
            }
            case ServiceError e -> {
                switch (e) {
                    case GROUP_NOT_FOUND -> errorMessage = Constantes.GRUPO_NO_EXISTE;
                    case NOT_IN_GROUP -> errorMessage = Constantes.USER_NOT_IN_GROUP;
                    case NOT_IN_GROUPS -> errorMessage = Constantes.USER_NOT_IN_GROUPS;
                    case USER_NOT_FOUND -> createUser();
                    case GROUP_ALREADY_EXISTS -> errorMessage = Constantes.GROUP_ALREADY_EXIST;
                    case ERROR_SENDING_MESSAGE -> errorMessage = Constantes.ERROR_SENDING_MESSAGE;
                    case ERROR_JOINING_GROUP -> errorMessage = Constantes.ERROR_JOINING_GROUP;
                    case USER_ALREADY_EXIST -> errorMessage = Constantes.USER_ALREADY_EXISTS;
                    case ERROR_ENCRYPTING -> errorMessage = Constantes.ERROR_ENCRYPTING;
                    case ERROR_DECRYPTING -> errorMessage = Constantes.ERROR_DECRYPTING;
                    case ALREADY_IN_GROUP -> errorMessage = Constantes.ALREADY_IN_GROUP;
                    case ERROR_GENERATING_KEYS -> errorMessage = Constantes.ERROR_GENERATING_KEYS;
                    case ERROR_COMPLETING_TASK -> errorMessage = Constantes.ERROR_COMPLETING_TASK;
                    case GROUP_HAS_NO_MESSAGES -> errorMessage = Constantes.GROUP_HAS_NO_MESSAGES;
                    case ERROR_GETTING_PUBLIC_KEY -> errorMessage = Constantes.ERROR_GETTING_PUBLIC_KEY;
                    case ERROR_GETTING_PRIVATE_KEY -> errorMessage = Constantes.ERROR_GETTING_PRIVATE_KEY;
                }
            }
            case DataInputError e -> {
                switch (e) {
                    case INCORRECT_PASSWORD -> errorMessage = Constantes.CONTRASEÑA_INCORRECTA;
                    case GROUP_IS_PRIVATE -> errorMessage = Constantes.GROUP_IS_PRIVATE;
                    case NOT_LOGGED -> errorMessage = Constantes.NOT_LOGGED;
                    case EMPTY_FIELDS -> errorMessage = Constantes.RELLENE_CAMPOS;
                }
            }
            default -> errorMessage = Constantes.UNEXPECTED_ERROR;
        }
        errorAlert.setContentText(errorMessage);
        errorAlert.showAndWait();
    }


    public void createGroup() {
        Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText(), isPrivate.isSelected());
        userName.setCursor(Cursor.WAIT);
        groupService.createGroup(grupo, user)
                .thenAccept(result -> result.peek(ok -> {
                    updateUserInfo();
                    Platform.runLater(() -> showInfo(Constantes.GROUP_CREATED));
                }).peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    public void joinGroup() {
        Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText(), isPrivate.isSelected());
        userName.setCursor(Cursor.WAIT);
        groupService.joinGroup(user, grupo)
                .thenAccept(result -> result.peek(ok -> {
                    userService.saveGroupPassword(user, grupo)
                            .thenAccept(result2 ->
                                    result2.peek(dbUser -> user = dbUser)
                                            .peekLeft(error -> Platform.runLater(() -> showError(error))));
                    updateUserInfo();
                    Platform.runLater(() -> showInfo(Constantes.JOINED_GROUP));
                }).peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    public void confirmDelete() {
        if (confirmationAlert(Constantes.MENSAJE_ELIMINAR_USUARIO)) {
            userName.setCursor(Cursor.WAIT);
            groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText(), user)
                    .thenAccept(result ->
                            result.peek(ok -> {
                                        updateUserInfo();
                                        Platform.runLater(() -> showInfo(Constantes.MEMBER_DELETED));
                                    })
                                    .peekLeft(error -> Platform.runLater(() -> showError(error))));
            userName.setCursor(Cursor.DEFAULT);
        }
    }


    public void sendMessage() {
        Grupo g = myChats.getSelectionModel().getSelectedItem();
        userName.setCursor(Cursor.WAIT);
        if (Boolean.TRUE.equals(g.getIsPrivate()))
            messageService.sendPrivateMessage(message.getText(), user, g)
                    .thenAccept(result -> result.peek(ok -> Platform.runLater(() -> {
                                loadChatMessages();
                                showInfo(Constantes.MESSAGE_SENT);
                            }))
                            .peekLeft(error -> Platform.runLater(() -> showError(error))));
        else
            messageService.sendMessage(message.getText(), user, g)
                    .thenAccept(result -> result.peek(ok -> Platform.runLater(() -> {
                                loadChatMessages();
                                showInfo(Constantes.MESSAGE_SENT);
                            }))
                            .peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    public void inviteUser() {
        userName.setCursor(Cursor.WAIT);
        groupService.inviteUser(myChats.getSelectionModel().getSelectedItem(), usuarios.getSelectionModel().getSelectedItems(), user)
                .thenAccept(result ->
                        result.peek(ok -> Platform.runLater(() -> showInfo(Constantes.USER_INVITED)))
                                .peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    private void updateUserInfo() {
        myChats.getItems().clear();
        chat.getItems().clear();
        userName.setCursor(Cursor.WAIT);
        groupService.getGroups(user)
                .thenAccept(result ->
                        result.peek(ok -> {
                                    loadGroups();
                                    Platform.runLater(() -> myChats.getItems().addAll(ok));
                                })
                                .peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    public void loadUsers() {
        usuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        usuarios.getItems().clear();
        userName.setCursor(Cursor.WAIT);
        userService.loadUsers(user)
                .thenAccept(result ->
                        result.peek(ok -> Platform.runLater(() -> usuarios.getItems().addAll(ok)))
                                .peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }


    private boolean confirmationAlert(String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(Constantes.COMFIRMACION);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        confirmation.close();
        return confirmation.getResult().equals(ButtonType.OK);
    }


    public void loadGroups() {
        chat.getItems().clear();
        if (!myChats.getSelectionModel().isEmpty())
            loadChatMessages();
    }

    public void loadChatMessages() {
        Grupo g = myChats.getSelectionModel().getSelectedItem();
        userName.setCursor(Cursor.WAIT);
        if (Boolean.TRUE.equals(g.getIsPrivate()))
            messageService.getPrivateMessages(user, g)
                    .thenAccept(result ->
                            result.peek(ok ->
                                    Platform.runLater(() -> ok.forEach(m -> chat.getItems().add(m.toString()))
                                    )).peekLeft(error -> Platform.runLater(() -> showError(error))));
        else
            messageService.getMessages(myChats.getSelectionModel().getSelectedItem(), user)
                    .thenAccept(result ->
                            result.peek(ok ->
                                    Platform.runLater(() -> ok.forEach(m -> chat.getItems().add(m.toString()))
                                    )).peekLeft(error -> Platform.runLater(() -> showError(error))));
        userName.setCursor(Cursor.DEFAULT);
    }
}