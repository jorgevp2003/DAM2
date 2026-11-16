package com.ejemplo.controlador;

import com.ejemplo.modelo.Incidencia;
import com.ejemplo.vista.FormularioView;
import com.ejemplo.vista.ResumenView;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * CONTROLADOR de la ventana principal.
 *
 * <p>Responsabilidades:</p>
 * <ol>
 *   <li>Poner los eventos a los botones de {@link FormularioView}.</li>
 *   <li>Limpiar: vaciar los campos.</li>
 *   <li>Registrar: validar que los dos campos tienen datos.
 *       <ul>
 *         <li>Si falta algo: mensaje "Debe completar el formulario".</li>
 *         <li>Si está bien: crear la {@link Incidencia} y abrir la ventana
 *             secundaria MODAL con {@code showAndWait()}.</li>
 *       </ul></li>
 *   <li>Al cerrarse la secundaria, preguntar a su controlador si se aceptó
 *       y mostrar "Incidencia registrada correctamente".</li>
 * </ol>
 *
 * <p>Comunicación entre ventanas:</p>
 * <pre>
 *  Principal --(new Incidencia)--&gt; ResumenController (constructor)
 *  Principal &lt;--(isAceptada())---- ResumenController (tras showAndWait)
 * </pre>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class FormularioController {

    /** Vista que controla este controlador. */
    private final FormularioView vista;

    /** Ventana principal: será la "dueña" (owner) de la ventana modal. */
    private final Stage ventanaPrincipal;

    /**
     * Guarda la vista y le asocia los eventos.
     *
     * @param vista            vista del formulario
     * @param ventanaPrincipal ventana principal de la aplicación
     */
    public FormularioController(FormularioView vista, Stage ventanaPrincipal) {
        this.vista = vista;
        this.ventanaPrincipal = ventanaPrincipal;

        // Los eventos se ponen AQUÍ (controlador), no en la vista.
        // setOnAction lo hereda BotonAccion de Button.
        // "e -> metodo()" es una lambda: cuando se pulse, llama al método.
        vista.getBtnRegistrar().setOnAction(e -> registrarIncidencia());
        vista.getBtnLimpiar().setOnAction(e -> limpiar());
    }

    /**
     * Acción del botón "Registrar incidencia".
     * Valida el formulario y, si es correcto, abre el resumen.
     */
    private void registrarIncidencia() {
        // trim() quita espacios al principio y al final:
        // así "   " cuenta como vacío.
        String equipo = vista.getTxtEquipo().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();

        // --- Validación: los dos campos son obligatorios ---
        if (equipo.isEmpty() || descripcion.isEmpty()) {
            mostrarEstado("Debe completar el formulario (equipo e incidencia).", "#c62828");
            return; // salimos: no se abre la ventana secundaria
        }

        // --- Datos correctos: los empaquetamos en el MODELO ---
        Incidencia incidencia = new Incidencia(equipo, descripcion);

        // Abre la ventana modal. Esta línea NO continúa hasta que
        // la ventana secundaria se cierra (por showAndWait).
        boolean aceptada = abrirResumen(incidencia);

        // --- Resultado que nos devuelve la ventana secundaria ---
        if (aceptada) {
            mostrarEstado("Incidencia registrada correctamente.", "#2e7d32");
        } else {
            // Cancelar (o cerrar con la X): no se registra
            mostrarEstado("Registro cancelado.", "#616161");
        }
    }

    /**
     * Crea y abre la ventana secundaria como MODAL y espera a que se cierre.
     *
     * @param incidencia datos que se envían a la ventana secundaria
     * @return {@code true} si el usuario pulsó Aceptar; {@code false} si no
     */
    private boolean abrirResumen(Incidencia incidencia) {
        // 1. Nueva ventana (Stage) para el resumen
        Stage ventanaResumen = new Stage();

        // 2. MVC de la ventana secundaria: vista + controlador.
        //    Le pasamos la incidencia -> así RECIBE los datos de la principal.
        ResumenView resumenView = new ResumenView();
        ResumenController resumenController =
                new ResumenController(resumenView, ventanaResumen, incidencia);

        // 3. Configurar la ventana
        ventanaResumen.setTitle("Resumen de la incidencia");
        ventanaResumen.setScene(new Scene(resumenView, 380, 220));
        ventanaResumen.setResizable(false);

        // 4. Hacerla MODAL:
        //    - initOwner: queda "encima" de la principal y centrada respecto a ella.
        //    - APPLICATION_MODAL: bloquea TODAS las demás ventanas de la app
        //      hasta que se cierre (no se puede tocar la principal).
        //    (initOwner e initModality deben llamarse ANTES de mostrarla)
        ventanaResumen.initOwner(ventanaPrincipal);
        ventanaResumen.initModality(Modality.APPLICATION_MODAL);

        // 5. showAndWait(): la muestra y BLOQUEA este método hasta que se cierre.
        //    (Con show() el código seguiría sin esperar y no sabríamos el resultado.)
        ventanaResumen.showAndWait();

        // 6. Aquí la ventana ya está cerrada: preguntamos qué botón se pulsó
        return resumenController.isAceptada();
    }

    /**
     * Acción del botón "Limpiar": vacía los campos y el mensaje de estado.
     */
    private void limpiar() {
        vista.getTxtEquipo().clear();
        vista.getTxtDescripcion().clear();
        mostrarEstado("", "black");
        vista.getTxtEquipo().requestFocus(); // cursor de nuevo en el primer campo
    }

    /**
     * Muestra un mensaje en la etiqueta de estado con el color indicado.
     *
     * @param mensaje texto a mostrar
     * @param color   color CSS del texto (ej: "#c62828" rojo, "#2e7d32" verde)
     */
    private void mostrarEstado(String mensaje, String color) {
        vista.getLblEstado().setText(mensaje);
        vista.getLblEstado().setStyle("-fx-text-fill: " + color + ";");
    }
}
