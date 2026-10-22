package com.example;

import java.sql.Date;

import java.time.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmpleadoController {
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
    private ComboBox comboDepartamentos;
    @FXML 
    private TextField txtPuesto;
    @FXML 
    private ComboBox comboContrato;
    @FXML 
    private TextField txtSalario;
    @FXML 
    private ComboBox radioJornada;
    @FXML 
    private DatePicker dateIncorporacion;
    @FXML 
    private TextField txtObservaciones;
    @FXML 
    private Button buttonGuardar;

    @FXML
    public void initialize(){
        comboDepartamentos.getItems().addAll(
            "Administracion",
            "Informatica",
            "Recursos Humanos",
            "Marketing",
            "Ventas",
            "Produccion"
        );
 
        comboContrato.getItems().addAll(
            "Indefinido",
            "Temporal",
            "Prácticas",
            "Formacion"
        );
    }

    @FXML 
    private void comprobarDepartamento() {
        String departamento = (String) comboDepartamentos.getValue();
        System.out.println("Departamento seleccionado: " + departamento);
    }
    /* 
    @FXML 
    private void guardarEmpleado(){
        System.out.println("Boton Guardar Pulsado");
    }
    */

    @FXML
    private void guardarEmpleado() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellido.getText();
        String dni = txtDNI.getText();
        String departamento = (String) comboDepartamentos.getValue();
        String puesto = txtPuesto.getText();
        String tipoContrato = (String)comboContrato.getValue();
        String salario = txtSalario.getText();
        String jornada = radioJornada.isSelected() ? "Completa" : "No seleccionada";
        LocalDate fechaIncorporacion = dateIncorporacion.getValue();
        LocalDate fechaNacimiento = dateNacimiento.getValue();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
       
        if(nombre!=null&&apellidos!=null&&dni!=null&&fechaIncorporacion!=null&&fechaNacimiento!=null&&telefono!=null&&email!=null&&departamento!=null&&puesto!=null&&tipoContrato!=null&&salario!=null&&jornada!=null&&fechaIncorporacion!=null){

            if(esDniValido(dni)){
                
                if(fechaNacimiento.plusYears(16).isBefore(LocalDate.now())){

                    if(telefono.length()==9){

                        if(esEmailValido(email)){
                            Empleado empleado = new Empleado(
                            nombre.trim(),
                            apellidos.trim(),
                            dni.trim(),
                            fechaNacimiento,
                            telefono.trim(),
                            email.trim(),
                            departamento,
                            puesto.trim(),
                            tipoContrato,
                            salario,
                            jornada.trim(),
                            fechaIncorporacion,
                            null
                            );


                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Empleado guardado");
                            alerta.setHeaderText("Datos del empleado");
                            alerta.setContentText(
                            "Nombre: " + empleado.getNombre() +
                            "\nApellidos: " + empleado.getApellidos() +
                            "\nDNI: " + empleado.getDni() +
                            "\nFecha de nacimiento: " + empleado.getFechaNacimiento() +
                            "\nTeléfono: " + empleado.getTelefono() +
                            "\nCorreo: " + empleado.getCorreo() +
                            "\nDepartamento: " + empleado.getDepartamento() +
                            "\nPuesto: " + empleado.getPuesto() +
                            "\nTipo de contrato: " + empleado.getTipoContrato() +
                            "\nSalario: " + empleado.getSalario() +
                            "\nJornada: " + empleado.getJornada() +
                            "\nFecha de incorporación: " + empleado.getFechaIncorporacion() +
                            "\nObservaciones: " + empleado.getObservaciones()
                            );
                            alerta.showAndWait();

                            System.out.println("Nombre: " + empleado.getNombre());
                            System.out.println("Apellidos: " + empleado.getApellidos());
                            System.out.println("DNI: " + empleado.getDni());
                        //Email valido
                        } else {
                            Alert popup = new Alert(Alert.AlertType.INFORMATION);

                            popup.setTitle("Error");
                            popup.setHeaderText("Email no valido");
                            popup.setContentText("Un Email tiene que contener solo una @ y 1 punto");

                            popup.showAndWait();
                        }
                        
                    //Telefono valido    
                    }else {
                        Alert popup = new Alert(Alert.AlertType.INFORMATION);

                        popup.setTitle("Error");
                        popup.setHeaderText("Telefono no valido");
                        popup.setContentText("Introduce un telefono valido");

                        popup.showAndWait();
                    }
                 //Fecha de nacimiento valida 16 años o mas    
                 }else {
                        Alert popup = new Alert(Alert.AlertType.INFORMATION);

                        popup.setTitle("Error");
                        popup.setHeaderText("Fecha no valida");
                        popup.setContentText("El usuario tiene que ser mayor de 16 años");

                        popup.showAndWait();
                 }
            //Segundo if DNI valido
            } else {
                Alert popup = new Alert(Alert.AlertType.INFORMATION);

                popup.setTitle("Error");
                popup.setHeaderText("DNI no valido");
                popup.setContentText("Porfavor introduce un DNI valido");

                popup.showAndWait();
           
            

            // Primer if no null
            } }else {
            Alert popup = new Alert(Alert.AlertType.INFORMATION);

            popup.setTitle("Error");
            popup.setHeaderText("Uno o mas parametros estan vacios");
            popup.setContentText("Solo comentarios puede estar vacio");

            popup.showAndWait();
        }
        

       
    }

    public static boolean esDniValido(String dni) {

    if (dni == null || !dni.matches("\\d{8}[A-Za-z]")) {
        return false;
    }

    int numero = Integer.parseInt(dni.substring(0, 8));

    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

    char letraCorrecta = letras.charAt(numero % 23);

    char letraDni = Character.toUpperCase(dni.charAt(8));

    return letraDni == letraCorrecta;
}

    public boolean esEmailValido(String email){
        int numArroba = 0;
        int punt = 0;

        for(int i =0;i<email.length();i++){
            if(email.charAt(i) == '@'){
                numArroba++;
            } else if (email.charAt(i) == '.'){
                punt++;
            }
        }
        if(numArroba==1 && punt==1){
            return true;
        } else {
            return false;
        }
    }
}
