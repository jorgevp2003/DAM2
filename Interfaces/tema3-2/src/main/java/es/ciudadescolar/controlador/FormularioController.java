package es.ciudadescolar.controlador;

import es.ciudadescolar.model.Persona;
import es.ciudadescolar.vista.FormularioView;

public class FormularioController {
    private FormularioView vista;
    
    public FormularioController(FormularioView vista) {
        this.vista = vista;
        

        vista.getBtnGuardar().setOnAction(event -> guardarPersona());
        vista.getBtnLimpiar().setOnAction(event -> limpiarPersona());
        vista.getBtnLimpiar().setOnMouseEntered(event -> vista.getLblInfo().setText("Pulsar limpiar borrara todos los campos"));
        vista.getBtnLimpiar().setOnMouseExited(event -> vista.getLblInfo().setText(""));
        vista.getTxtNombre().textProperty().addListener((observable, anterior, nuevo) -> actualizarEstado());
        vista.getTxtApellidos().textProperty().addListener((observable,anterior,nuevo)-> actualizarEstado());
        vista.getTxtDni().textProperty().addListener((observable,anterior,nuevo)-> actualizarEstado());
        vista.getBtnGuardar().setDisable(true);
    }

    private void guardarPersona() {
        String nombre = vista.getTxtNombre().getTexto().trim();
        String apellidos = vista.getTxtApellidos().getTexto().trim();
        String dni = vista.getTxtDni().getTexto().trim();
        if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty()) {
        System.out.println("Debes completar todos los campos.");
        return;
    
    Persona persona = new Persona(nombre, apellidos, dni);
    System.out.println("Persona registrada correctamente.");
    System.out.println("Nombre: " + persona.getNombre());
    System.out.println("Apellidos: " + persona.getApellidos());
    System.out.println("DNI: " + persona.getDni());
    }


    private void limpiarPersona(){
        vista.getTxtNombre().setTexto("");
        vista.getTxtApellidos().setTexto("");
        vista.getTxtDni().setTexto("");
    }
    //Para actualizar y ver el estado en el momento de rellenar el formulario
    private void actualizarEstado() {
        String nombre = vista.getTxtNombre().getTexto().trim();
        String apellidos = vista.getTxtApellidos().getTexto().trim();
        String dni = vista.getTxtDni().getTexto().trim();
        StringBuilder pendientes = new StringBuilder();
        if (nombre.isEmpty()) 
        {
        pendientes.append("Nombre, ");
        }
        if (apellidos.isEmpty()) 
        {
        pendientes.append("Apellidos, ");
        }
        if (dni.isEmpty()) 
        {
        pendientes.append("DNI, ");
        }
        else if(!dniValido(dni))
        {
        pendientes.append("DNI no valido,");
        }

        boolean formularioCompleto = pendientes.length() == 0;
        vista.getBtnGuardar().setDisable(!formularioCompleto);

        if (formularioCompleto) 
            {
            vista.getLblEstado().setText("Formulario completo.");
        } 
        else 
        {
            pendientes.setLength(pendientes.length() - 2);
            vista.getLblEstado().setText("Pendiente de completar: " + pendientes);
        }
    }
    private boolean dniValido(String dni) 
    {
        dni = dni.trim().toUpperCase();
        if (!dni.matches("\\d{8}[A-Z]")) {
        return false;
    }
    int numero = Integer.parseInt(dni.substring(0, 8));
    char letraIntroducida = dni.charAt(8);
    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    char letraCorrecta = letras.charAt(numero % 23);
    return letraIntroducida == letraCorrecta;
    }
}