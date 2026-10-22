package es.ciudadescolar;

import java.sql.Date;

import java.time.*;
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
        
    }

    @FXML 
    public void crearReserva(){
        Boolean valido = true;
        String mensaje;
       
       String nombre = txtNombre.toString();
       String apellido = txtApellido.toString();
       String dni = txtDNI.toString();
       LocalDate fechaNacimiento = dateNacimiento.getValue();
       String telefono = txtTelefono.toString();
       String email = txtEmail.toString();
       String instalaccion = txtInstalacion.toString();
       String actividad = txtActividad.toString();
       LocalDate fechaReserva = dateReserva.getValue();
       int horaReserva = Integer.getInteger(txtHoraReserva.toString());
       String duracionReserva = txtDuracionReserva.toString();
       String tipoUsuario = txtInstalacion.toString();
       String observacion = txtObservacion.toString();

        //Comprobar no null;
        if(nombre==null||apellido==null||telefono==null||dni==null||instalaccion!=null||actividad!=null||fechaReserva!=null||
        duracionReserva!=null||instalaccion!=null){
                valido = false;
                mensaje += "Nombre,Apellido,DNI,Telefono,Instalaccion,Actividad,FechaReserva,DuracionReserva,Instalaccion no pueden ser Null. \n";
        }

        //Textos no vacios
        if(apellido.replace(" ","").equals("")){
            valido = false;
            mensaje += ""
        }
        if(dni.replace(" ","").equals("")){
            valido = false;
        }
        if(telefono.replace(" ","").equals("")){
            valido = false;
        }
        if(instalaccion.replace(" ","").equals("")){
            valido = false;
        }
        if(actividad.replace(" ","").equals("")){
            valido = false;
        }
        if(duracionReserva.replace(" ","").equals("")){
            valido = false;
        }
        if(tipoUsuario.replace(" ","").equals("")){
            valido = false;
        }

        //Comprobar DNI
        if(dni.length()==9){
            int num = 0;
            for(int i = 0;i<8;i++){
                num += dni.charAt(i);
            }
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            char letra = letras.charAt(num/23);

            if(dni.charAt(9) != letra)
                valido = false;
        
        } else {
            valido = false;
        }
        
        //Telefono valido

        if(telefono.length() == 9){
            try{
                Integer.getInteger(telefono);
            } catch(NumberFormatException e) {
                valido = false;
            }
        } else{
            valido = false;
        }
        
        //Correo valido 

        if(email.contains("@")){

            String[] dividido = email.split("@");

            if(dividido.length==2&& dividido[2].contains(".")){

                String[] dividido2 = dividido[2].split(".");

                if (dividido2.length>2) 
                    
                    valido = false;
            }
        } else {
            valido = false;
        }
        
        

    }    
}
