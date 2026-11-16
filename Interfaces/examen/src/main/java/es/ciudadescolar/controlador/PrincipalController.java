package es.ciudadescolar.controlador;

import java.time.LocalDate;

import es.ciudadescolar.componentes.BotonAccion;
import es.ciudadescolar.modelo.Persona;
import es.ciudadescolar.util.Alertas;
import es.ciudadescolar.util.Validador;
import es.ciudadescolar.vista.FormularioView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador de principal.fxml.
 * Cada @FXML se enlaza con el fx:id del mismo nombre en el FXML.
 * Cada método @FXML se enlaza con onAction="#nombreMetodo".
 */
public class PrincipalController {

    // ---------- Campos del formulario ----------
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtDni;
    @FXML private DatePicker dateNacimiento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> comboDepartamento;
    @FXML private ToggleGroup grupoJornada;
    @FXML private RadioButton radioCompleta;
    @FXML private RadioButton radioParcial;
    @FXML private CheckBox checkNewsletter;
    @FXML private TextArea txtObservaciones;

    // ---------- Tabla ----------
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, String> colNombre;
    @FXML private TableColumn<Persona, String> colApellidos;
    @FXML private TableColumn<Persona, String> colDni;
    @FXML private TableColumn<Persona, Integer> colEdad;
    @FXML private TableColumn<Persona, String> colDepartamento;

    // ---------- Botones / estado ----------
    @FXML private BotonAccion btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnEliminar;
    @FXML private Label lblEstado;

    /** Lista observable: si se añade/quita algo, la tabla se actualiza sola. */
    private final ObservableList<Persona> personas = FXCollections.observableArrayList();

    /** Se ejecuta automáticamente después de cargar el FXML. */
    @FXML
    public void initialize() {
        comboDepartamento.getItems().addAll(
            "Administración", "Informática", "Recursos Humanos", "Marketing", "Ventas", "Producción");

        // Columnas -> getters de Persona (getNombre, getApellidos, getEdad...)
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        tablaPersonas.setItems(personas);

        // Eliminar solo activo si hay una fila seleccionada (binding)
        btnEliminar.disableProperty().bind(tablaPersonas.getSelectionModel().selectedItemProperty().isNull());

        // Doble clic en una fila -> ver detalle
        tablaPersonas.setOnMouseClicked(e -> {
            Persona p = tablaPersonas.getSelectionModel().getSelectedItem();
            if (e.getClickCount() == 2 && p != null) {
                Alertas.info("Detalle", p.getNombre() + " " + p.getApellidos(), p.toString());
            }
        });

        // Validación en vivo: cada cambio actualiza la barra de estado y el botón Guardar
        txtNombre.textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        txtApellidos.textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        txtDni.textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        txtTelefono.textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        txtEmail.textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        dateNacimiento.valueProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        comboDepartamento.valueProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        grupoJornada.selectedToggleProperty().addListener((obs, ant, nuevo) -> actualizarEstado());

        actualizarEstado();
    }

    /** Devuelve los errores del formulario ("" si todo es correcto). */
    private String comprobarErrores() {
        StringBuilder errores = new StringBuilder();
        if (!Validador.longitudMinima(txtNombre.getText(), 2)) errores.append("Nombre (mín. 2 letras)\n");
        if (!Validador.longitudMinima(txtApellidos.getText(), 2)) errores.append("Apellidos (mín. 2 letras)\n");
        if (!Validador.esDniValido(txtDni.getText())) errores.append("DNI no válido\n");
        if (!Validador.esMayorDe(dateNacimiento.getValue(), 16)) errores.append("Debe tener 16 años o más\n");
        if (!Validador.esTelefonoValido(txtTelefono.getText())) errores.append("Teléfono (9 dígitos)\n");
        if (!Validador.esEmailValido(txtEmail.getText())) errores.append("Email no válido\n");
        if (comboDepartamento.getValue() == null) errores.append("Elige un departamento\n");
        if (grupoJornada.getSelectedToggle() == null) errores.append("Elige una jornada\n");
        return errores.toString();
    }

    private void actualizarEstado() {
        marcar(txtNombre, Validador.longitudMinima(txtNombre.getText(), 2));
        marcar(txtApellidos, Validador.longitudMinima(txtApellidos.getText(), 2));
        marcar(txtDni, Validador.esDniValido(txtDni.getText()));
        marcar(txtTelefono, Validador.esTelefonoValido(txtTelefono.getText()));
        marcar(txtEmail, Validador.esEmailValido(txtEmail.getText()));

        String errores = comprobarErrores();
        btnGuardar.setActivo(errores.isEmpty());
        lblEstado.setText(errores.isEmpty()
            ? "Formulario completo. Puedes guardar."
            : "Pendiente: " + errores.trim().replace("\n", ", "));
    }

    /** Borde rojo solo si el campo tiene algo escrito y es incorrecto. */
    private void marcar(TextField campo, boolean valido) {
        boolean vacio = campo.getText() == null || campo.getText().isEmpty();
        campo.setStyle(vacio || valido ? "" : "-fx-border-color: red;");
    }

    @FXML
    private void cambiarDepartamento() {
        System.out.println("Departamento seleccionado: " + comboDepartamento.getValue());
    }

    @FXML
    private void guardar() {
        String errores = comprobarErrores();
        if (!errores.isEmpty()) {
            Alertas.error("Error", "Revisa el formulario", errores);
            return;
        }

        String dni = txtDni.getText().trim().toUpperCase();
        boolean repetido = personas.stream().anyMatch(p -> p.getDni().equals(dni));
        if (repetido) {
            Alertas.aviso("Aviso", "DNI repetido", "Ya existe una persona con el DNI " + dni);
            return;
        }

        RadioButton jornada = (RadioButton) grupoJornada.getSelectedToggle();
        LocalDate fecha = dateNacimiento.getValue();
        Persona persona = new Persona(
            txtNombre.getText().trim(),
            txtApellidos.getText().trim(),
            dni,
            fecha,
            txtTelefono.getText().trim(),
            txtEmail.getText().trim(),
            comboDepartamento.getValue(),
            jornada.getText(),
            checkNewsletter.isSelected(),
            txtObservaciones.getText().trim());

        personas.add(persona);
        Alertas.info("Guardado", "Persona guardada correctamente", persona.toString());
        System.out.println(persona);
        limpiar();
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtApellidos.clear();
        txtDni.clear();
        dateNacimiento.setValue(null);
        txtTelefono.clear();
        txtEmail.clear();
        comboDepartamento.getSelectionModel().clearSelection();
        comboDepartamento.setValue(null);
        grupoJornada.selectToggle(null);
        checkNewsletter.setSelected(false);
        txtObservaciones.clear();
        txtNombre.requestFocus();
    }

    @FXML
    private void eliminar() {
        Persona p = tablaPersonas.getSelectionModel().getSelectedItem();
        if (p != null && Alertas.confirmar("Eliminar", "¿Eliminar a " + p.getNombre() + " " + p.getApellidos() + "?")) {
            personas.remove(p);
        }
    }

    /** Abre una segunda ventana (modal) con la vista hecha por código + su controlador. */
    @FXML
    private void abrirFormularioMvc() {
        FormularioView vista = new FormularioView();
        new FormularioController(vista, personas);   // comparte la misma lista -> aparece en la tabla

        Stage ventana = new Stage();
        ventana.setTitle("Formulario MVC (vista creada por código)");
        ventana.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(vista, 450, 380);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
        ventana.setScene(scene);
        ventana.showAndWait();
    }
}
