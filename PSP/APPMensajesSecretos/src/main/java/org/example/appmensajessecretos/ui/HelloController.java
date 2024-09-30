package org.example.appmensajessecretos.ui;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.example.appmensajessecretos.domain.servicio.GroupService;
import org.example.appmensajessecretos.domain.servicio.MessageService;
import org.example.appmensajessecretos.utilities.Constantes;
import org.example.appmensajessecretos.dao.Dao;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.domain.servicio.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.appmensajessecretos.utilities.LogConstantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Data
public class HelloController {

    private UserService userService;
    private MessageService messageService;
    private GroupService groupService;
    private Dao dao;
    private Usuario usuario;


    @FXML
    private Label enterUser;
    @FXML
    private TextField userName;
    @FXML
    private Label enterPassword;
    @FXML
    private TextField userPassword;
    @FXML
    private Button submitCredentials;
    @FXML
    private Label logInError;

    @FXML
    private Label enterGroupName;
    @FXML
    private TextField groupName;
    @FXML
    private Label enterGroupPassword;
    @FXML
    private TextField groupPassword;
    @FXML
    private Button joinGroup;
    @FXML
    private Label joinGroupError;

    @FXML
    private Label enterGroupNameDelete;
    @FXML
    private TextField groupNameDelete;
    @FXML
    private Label enterUserNameDelete;
    @FXML
    private TextField userNameDelete;
    @FXML
    private Label deleteError;
    @FXML
    private Button deleteUser;

    @FXML
    private Label misChats;
    @FXML
    private ChoiceBox chat;
    @FXML
    private ListView<String> myChats;

    @FXML
    private Label mensajeLabel;
    @FXML
    private TextArea mensaje;
    @FXML
    private Button envioGrupo;
    @FXML
    private Button envioPrivado;

    @FXML
    private Label chatActivoLabel;
    @FXML
    private AnchorPane chatActivo;

    @FXML
    private Label createGroupNameLabel;
    @FXML
    private TextField createGroupName;
    @FXML
    private Label createGroupPasswordLabel;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private Button createGroup;
    @FXML
    private Label createGroupError;


    public HelloController() {
        userService = new UserService();
        groupService = new GroupService();
        messageService = new MessageService();
        dao = new Dao();
        usuario = null;
    }

    public void iniciarSesion() {
        boolean succes = false;
        if (userName.getText().isEmpty() || userPassword.getText().isEmpty())
            logInError.setText(Constantes.RELLENE_CAMPOS);
        else if (!userService.findUser(dao, new Usuario(userName.getText(), userPassword.getText()))) {
            Alert create = new Alert(Alert.AlertType.CONFIRMATION);
            create.setTitle(Constantes.CREACION_USUARIO);
            create.setHeaderText(Constantes.USUARIO_MISSING);
            create.showAndWait();
            if (create.getResult() == ButtonType.OK) {
                usuario = new Usuario(userName.getText(), userPassword.getText());
                userService.addUser(dao, usuario);
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
        return usuario != null;
    }

    public void createGroup() {
        if (!checkLogged()) {
            log.info(LogConstantes.NOT_LOGGED);
            createGroupError.setText(Constantes.NOT_LOGGED);
        } else {
            if (createGroupName.getText().isEmpty() || createGroupPassword.getText().isEmpty())
                createGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText());
                if (!groupService.createGroup(dao, grupo)) {
                    createGroupError.setText(Constantes.ERROR_CREATING_GROUP);
                    log.error(LogConstantes.ERROR_CREATING_GROUP);
                } else {
                    createGroupError.setText(Constantes.GROUP_CREATED);
                    log.info(Constantes.GROUP_CREATED);
                    actualizarUserInfo();
                    //actualizarMensajes();
                }
            }
        }
    }

    public void joinGroup() {
        if (!checkLogged()) {
            log.info(LogConstantes.NOT_LOGGED);
            joinGroupError.setText(Constantes.NOT_LOGGED);
        } else {
            if (enterGroupName.getText().isEmpty() || enterGroupPassword.getText().isEmpty())
                joinGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText());
                if (!groupService.findGroup(dao, grupo)) {
                    joinGroupError.setText(Constantes.GRUPO_NO_EXISTE);
                    log.error(Constantes.GRUPO_NO_EXISTE);
                } else if (!groupService.joinGroup(dao, usuario, grupo)) {
                    joinGroupError.setText(Constantes.ERROR_JOINING_GROUP);
                    log.error(Constantes.ERROR_JOINING_GROUP);
                } else {
                    joinGroupError.setText(Constantes.JOINED_GROUP);
                    log.info(Constantes.JOINED_GROUP);
                    actualizarUserInfo();
                    //actualizarMensajes();
                }
            }
        }
    }

    public void comfirmDelete() {
        if (!checkLogged()) {
            log.info(LogConstantes.NOT_LOGGED);
            deleteError.setText(Constantes.NOT_LOGGED);
        } else if (userNameDelete.getText().isEmpty() || groupNameDelete.getText().isEmpty())
            deleteError.setText(Constantes.RELLENE_CAMPOS);
        else if (!groupService.findUser(dao, userNameDelete.getText(), groupNameDelete.getText())) {
            deleteError.setText(Constantes.USER_NOT_FOUND);
            log.info(LogConstantes.USER_NOT_FOUND);
        } else {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle(Constantes.COMFIRMACION);
            confirmation.setHeaderText(Constantes.MENSAJE_ELIMINAR_USUARIO);
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.OK) {
                confirmation.close();
                if (!groupService.deleteMember(dao, userNameDelete.getText(), groupNameDelete.getText())) {
                    log.info(LogConstantes.ERROR_DELETING_USER);
                    deleteError.setText(Constantes.ERROR_DELETING_USER);
                }
                else {
                    deleteError.setText(Constantes.MEMBER_DELETED);
                    log.info(LogConstantes.MEMBER_DELETED);
                    actualizarUserInfo();
                }
            }
        }
    }

    public void selectUser() {
        TextInputDialog selectUser = new TextInputDialog();
        selectUser.setTitle("Enviar mensaje a:");
        selectUser.setContentText("Introduzca el nombre del destinatario");
        Optional<String> respuesta = selectUser.showAndWait();
    }

    public void selectChat() {
        chat.getValue();
    }


    private void actualizarUserInfo() {
        ObservableList<Grupo> chats = FXCollections.observableList(groupService.getGroups(dao, usuario));
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