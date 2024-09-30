package org.example.demojavafx.ui;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.demojavafx.domain.modelo.Grupo;
import org.example.demojavafx.domain.modelo.Mensaje;
import org.example.demojavafx.domain.modelo.User;
import org.example.demojavafx.domain.servicios.ServiciosGrupo;
import org.example.demojavafx.domain.servicios.ServiciosMensaje;
import org.example.demojavafx.domain.servicios.ServiciosUser;

import java.util.ArrayList;

public class GruposController {
    private final Alert alertError;
    private final Alert alertInfo;
    private final ServiciosUser serviciosUser;
    private final ServiciosGrupo serviciosGrupo;
    private final ServiciosMensaje serviciosMensaje;
    @FXML
    private ListView<Mensaje> mensajesGrupo;
    @FXML
    private TextField txtContenidoMensaje;
    @FXML
    private Label txtGrupoSeleccionado;
    @FXML
    private ListView<Grupo> gruposUsuario;

    @FXML
    private TextField txtNombreGrupoAdd;
    @FXML
    private TextField txtPassGrupoAdd;
    @FXML
    private TextField txtNombreGrupoEntrar;
    @FXML
    private TextField txtPasswordGrupoEntrar;
    @FXML
    private TextField txtLoginUser;
    @FXML
    private TextField txtPasswordUser;
    @FXML
    private TextField txtRegistroUser;
    @FXML
    private TextField txtRegistroPassword;
    @FXML
    private Label txtUsuarioLogueado;

    private User userLogueado;
    private Grupo grupoSeleccionado;

    public GruposController() {
        this.alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("Info");
        alertInfo.setHeaderText("Info");
        this.alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Error");

        this.serviciosUser = new ServiciosUser();
        this.serviciosGrupo = new ServiciosGrupo();
        this.serviciosMensaje = new ServiciosMensaje();
    }


    public void initialize() {
        gruposUsuario.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Grupo>) c -> {
            Grupo grupo = gruposUsuario.getSelectionModel().getSelectedItem();
            if (grupo != null) {
                grupoSeleccionado = grupo;
                txtGrupoSeleccionado.setText(grupo.getNombre());
                refrescarMensajesGrupo();
            }

        });
    }

    private void mostrarInfo(String infoText) {
        alertInfo.setContentText(infoText);
        alertInfo.showAndWait();
    }

    private void mostratError(String errorText) {
        alertError.setContentText(errorText);
        alertError.showAndWait();
    }


    private void cargarGruposUsuario() {
        gruposUsuario.getItems().clear();
        gruposUsuario.getItems().addAll(serviciosGrupo.getGruposOfUser(userLogueado));
        mensajesGrupo.getItems().clear();
        grupoSeleccionado = null;
        txtGrupoSeleccionado.setText("");
    }

    @FXML
    private void loginUser(ActionEvent actionEvent) {
        User loginUser = new User(this.txtLoginUser.getText(), this.txtPasswordUser.getText());
        if (serviciosUser.loginUser(loginUser)) {
            userLogueado = loginUser;
            txtUsuarioLogueado.setText("Bienvenido " + loginUser.getUsername());
            cargarGruposUsuario();
        } else {
            mostratError("Usuario o contraseña incorrectos");
        }

    }

    @FXML
    private void registerUser(ActionEvent actionEvent) {
        User registerUser = new User(this.txtRegistroUser.getText(), this.txtRegistroPassword.getText());
        if (serviciosUser.registerUser(registerUser)) {
            mostrarInfo("Usuario " + registerUser.getUsername() + "registrado");
        } else {
            mostratError("Error al registrar usuario");
        }
    }

    @FXML
    private void addGrupo(ActionEvent actionEvent) {

        Grupo grupo = new Grupo(txtNombreGrupoAdd.getText(), txtPassGrupoAdd.getText(), new ArrayList<>());
        if (serviciosGrupo.registerGrupo(grupo)) {
            mostrarInfo("Grupo " + grupo.getNombre() + " creado");
        } else {
            mostratError("Error al crear grupo");
        }

    }

    @FXML
    private void entrarEnGrupo(ActionEvent actionEvent) {
        Grupo grupo = new Grupo(txtNombreGrupoEntrar.getText(), txtPasswordGrupoEntrar.getText(), new ArrayList<>());
        if (serviciosGrupo.entrarEnGrupo(grupo, userLogueado)) {
            mostrarInfo("Usuario " + userLogueado.getUsername() + " añadido al grupo " + grupo.getNombre());
            cargarGruposUsuario();
        } else {
            mostratError("Error al añadir usuario al grupo");
        }
    }

    public void addMensajeGrupo(ActionEvent actionEvent) {
        if (grupoSeleccionado != null) {
            Mensaje m = new Mensaje(txtContenidoMensaje.getText(), grupoSeleccionado);
            serviciosMensaje.addMensajeToGrupo(m);
            refrescarMensajesGrupo();

        } else {
            mostratError("Selecciona un grupo");
        }
    }

    public void refrescarMensajesGrupo()
    {
        if (grupoSeleccionado != null) {
            mensajesGrupo.getItems().clear();
            mensajesGrupo.getItems().addAll(serviciosMensaje.getMensajesOfGrupo(grupoSeleccionado.getNombre()));
        } else {
            mostratError("Selecciona un grupo");
        }
    }
}
