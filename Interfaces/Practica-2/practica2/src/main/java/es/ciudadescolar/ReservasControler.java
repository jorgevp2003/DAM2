package es.ciudadescolar;

import java.time.LocalDate;

import es.ciudadescolar.Model.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReservasControler {
    @FXML 
    private TextField txtNombre;
    @FXML
    private TextField txtApellido; 
    @FXML 
    private TextField txtDNI;
    @FXML 
    private DatePicker dateNacimiento;
    @FXML 
    private TextField txtTelefono;
    @FXML 
    private TextField txtEmail;
    @FXML
    private ComboBox txtInstalacion;
    @FXML 
    private ComboBox txtActividad;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private TextField txtHoraReserva;
    @FXML 
    private TextField txtDuracionReserva;
    @FXML
    private ComboBox txtUsuario;
    @FXML
    private TextField txtObservacion;
    @FXML
    private Button buttonCrear;

    @FXML 
    public void initialize(){
        txtInstalacion.getItems().addAll(
            "Pabellon",
            "Pista Tenis",
            "Pista Futboll 1",
            "Pista Futboll 2",
            "Pista Futboll 3",
            "Pista Padel 1",
            "pista Padel 2"
        );
        txtActividad.getItems().addAll(
            "Futbol",
            "Tenis",
            "Padel"
        );
        txtUsuario.getItems().addAll(
            "Normal",
            "Premiun",
            "VIP"
        );

        // Cada vez que cambia un campo obligatorio se vuelve a validar el formulario
        TextField[] textos = {txtNombre, txtApellido, txtDNI, txtTelefono, txtEmail, txtHoraReserva, txtDuracionReserva};
        for (TextField t : textos) {
            t.textProperty().addListener((obs, viejo, nuevo) -> validarCampos());
        }
        dateNacimiento.valueProperty().addListener((obs, viejo, nuevo) -> validarCampos());
        dateReserva.valueProperty().addListener((obs, viejo, nuevo) -> validarCampos());
        txtInstalacion.valueProperty().addListener((obs, viejo, nuevo) -> validarCampos());
        txtActividad.valueProperty().addListener((obs, viejo, nuevo) -> validarCampos());
        txtUsuario.valueProperty().addListener((obs, viejo, nuevo) -> validarCampos());

        validarCampos();
    }

    private static final String ESTILO_ERROR = "-fx-border-color: red; -fx-border-width: 2px;";

    // Pinta en rojo los campos sin responder y desactiva el boton si falta alguno
    private void validarCampos() {
        boolean completo = true;

        TextField[] textos = {txtNombre, txtApellido, txtDNI, txtTelefono, txtEmail, txtHoraReserva, txtDuracionReserva};
        for (TextField t : textos) {
            completo &= marcar(t, t.getText() != null && !t.getText().trim().isEmpty());
        }

        DatePicker[] fechas = {dateNacimiento, dateReserva};
        for (DatePicker d : fechas) {
            completo &= marcar(d, d.getValue() != null);
        }

        ComboBox<?>[] combos = {txtInstalacion, txtActividad, txtUsuario};
        for (ComboBox<?> c : combos) {
            completo &= marcar(c, c.getValue() != null);
        }

        buttonCrear.setDisable(!completo);
    }

    private boolean marcar(Control campo, boolean relleno) {
        campo.setStyle(relleno ? "" : ESTILO_ERROR);
        return relleno;
    }

    @FXML
    public void crearReserva() {
        boolean valido = true;
        String mensaje = "";

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim().toUpperCase();
        LocalDate fechaNacimiento = dateNacimiento.getValue();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String instalacion = (String) txtInstalacion.getValue();
        String actividad = (String) txtActividad.getValue();
        LocalDate fechaReserva = dateReserva.getValue();
        String duracionReserva = txtDuracionReserva.getText().trim();
        String tipoUsuario = (String) txtUsuario.getValue();
        String observacion = txtObservacion.getText() == null ? "" : txtObservacion.getText().trim();

        // Comprobar que no falta nada (el boton ya esta desactivado si falta algo, pero por si acaso)
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || fechaNacimiento == null
                || telefono.isEmpty() || email.isEmpty() || instalacion == null || actividad == null
                || fechaReserva == null || duracionReserva.isEmpty() || tipoUsuario == null) {
            valido = false;
            mensaje += "Todos los campos (menos observaciones) son obligatorios.\n";
        }

        // Comprobar DNI: 8 numeros + letra correcta
        if (dni.matches("\\d{8}[A-Z]")) {
            int num = Integer.parseInt(dni.substring(0, 8));
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            char letra = letras.charAt(num % 23);
            if (dni.charAt(8) != letra) {
                valido = false;
                mensaje += "La letra del DNI no es correcta.\n";
            }
        } else {
            valido = false;
            mensaje += "El DNI debe tener 8 numeros y una letra.\n";
        }

        // Fecha de nacimiento no puede ser futura
        if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
            valido = false;
            mensaje += "La fecha de nacimiento no puede ser futura.\n";
        }

        // Telefono: 9 digitos
        if (!telefono.matches("\\d{9}")) {
            valido = false;
            mensaje += "El telefono debe tener 9 numeros.\n";
        }

        // Correo: algo@algo.algo
        String[] dividido = email.split("@");
        if (dividido.length != 2 || dividido[0].isEmpty()) {
            valido = false;
            mensaje += "El correo debe tener un @.\n";
        } else {
            String[] dominio = dividido[1].split("\\.");
            if (dominio.length < 2 || dividido[1].startsWith(".") || dividido[1].endsWith(".")) {
                valido = false;
                mensaje += "El dominio del correo no es valido (ej: gmail.com).\n";
            }
        }

        // Fecha de reserva: hoy o posterior
        if (fechaReserva != null && fechaReserva.isBefore(LocalDate.now())) {
            valido = false;
            mensaje += "La fecha de reserva no puede ser anterior a hoy.\n";
        }

        // Hora de reserva: numero entre 0 y 23
        int horaReserva = -1;
        try {
            horaReserva = Integer.parseInt(txtHoraReserva.getText().trim());
            if (horaReserva < 0 || horaReserva > 23) {
                valido = false;
                mensaje += "La hora debe estar entre 0 y 23.\n";
            }
        } catch (NumberFormatException e) {
            valido = false;
            mensaje += "La hora debe ser un numero (0-23).\n";
        }

        // Duracion: numero positivo
        try {
            if (Integer.parseInt(duracionReserva) <= 0) {
                valido = false;
                mensaje += "La duracion debe ser mayor que 0.\n";
            }
        } catch (NumberFormatException e) {
            valido = false;
            mensaje += "La duracion debe ser un numero.\n";
        }

        if (valido) {
            Reserva reserva = new Reserva(nombre, apellido, dni, fechaNacimiento, email,
                    instalacion, actividad, fechaReserva, horaReserva, duracionReserva,
                    tipoUsuario, observacion);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva creada",
                    "Reserva de " + reserva.getNombre() + " " + reserva.getApellido()
                    + " en " + reserva.getInstalaccion() + " el " + reserva.getFechaReserva()
                    + " a las " + reserva.getHoraReserva() + ":00.");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Datos incorrectos", mensaje);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String texto) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(texto);
        alerta.showAndWait();
    }
}
