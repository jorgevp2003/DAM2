package es.ciudadescolar.controlador;

import java.util.List;

import es.ciudadescolar.modelo.Persona;
import es.ciudadescolar.util.Alertas;
import es.ciudadescolar.util.Validador;
import es.ciudadescolar.vista.FormularioView;

/**
 * Controlador (la C de MVC) para FormularioView.
 * Recibe la vista, le engancha los eventos y trabaja con el modelo (Persona).
 */
public class FormularioController {

    private final FormularioView vista;
    private final List<Persona> personas;

    public FormularioController(FormularioView vista, List<Persona> personas) {
        this.vista = vista;
        this.personas = personas;

        // Eventos de botones
        vista.getBtnGuardar().setOnAction(e -> guardarPersona());
        vista.getBtnLimpiar().setOnAction(e -> limpiar());

        // Eventos de ratón (entrar / salir)
        vista.getBtnLimpiar().setOnMouseEntered(e -> vista.getLblInfo().setText("Limpiar borrará todos los campos"));
        vista.getBtnLimpiar().setOnMouseExited(e -> vista.getLblInfo().setText(""));

        // Listeners: cada cambio de texto recalcula el estado
        vista.getTxtNombre().textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        vista.getTxtApellidos().textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());
        vista.getTxtDni().textProperty().addListener((obs, ant, nuevo) -> actualizarEstado());

        actualizarEstado();
    }

    private void actualizarEstado() {
        String nombre = vista.getTxtNombre().getTexto().trim();
        String apellidos = vista.getTxtApellidos().getTexto().trim();
        String dni = vista.getTxtDni().getTexto().trim();

        StringBuilder pendientes = new StringBuilder();
        if (nombre.isEmpty()) pendientes.append("Nombre, ");
        else if (nombre.length() < 3) pendientes.append("Nombre (mín. 3 letras), ");

        if (apellidos.isEmpty()) pendientes.append("Apellidos, ");
        else if (apellidos.length() < 3) pendientes.append("Apellidos (mín. 3 letras), ");

        if (dni.isEmpty()) pendientes.append("DNI, ");
        else if (!Validador.esDniValido(dni)) pendientes.append("DNI no válido, ");

        boolean completo = pendientes.length() == 0;
        vista.getBtnGuardar().setActivo(completo);

        if (completo) {
            vista.getLblEstado().setText("Formulario completo.");
        } else {
            pendientes.setLength(pendientes.length() - 2); // quita la última ", "
            vista.getLblEstado().setText("Pendiente de completar: " + pendientes);
        }
    }

    private void guardarPersona() {
        Persona persona = new Persona(
            vista.getTxtNombre().getTexto().trim(),
            vista.getTxtApellidos().getTexto().trim(),
            vista.getTxtDni().getTexto().trim().toUpperCase());
        personas.add(persona);
        Alertas.info("Guardado", "Persona registrada", persona.getNombre() + " " + persona.getApellidos());
        limpiar();
    }

    private void limpiar() {
        vista.getTxtNombre().setTexto("");
        vista.getTxtApellidos().setTexto("");
        vista.getTxtDni().setTexto("");
    }
}
