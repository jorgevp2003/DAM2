package es.ciudadescolar.Model;

import java.time.LocalDate;

public class Reserva {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String email;
    private String instalaccion;
    private String actividad;
    private LocalDate fechaReserva;
    private int horaReserva;
    private String duracionReserva;
    private String tipoUsuario;
    private String observacion;

    public Reserva(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String email,
            String instalaccion, String actividad, LocalDate fechaReserva, int horaReserva, String duracionReserva,
            String tipoUsuario, String observacion) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
            this.email = email;
            this.instalaccion = instalaccion;
            this.actividad = actividad;
            this.fechaReserva = fechaReserva;
            this.horaReserva = horaReserva;
            this.duracionReserva = duracionReserva;
            this.tipoUsuario = tipoUsuario;
            this.observacion = observacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstalaccion() {
        return instalaccion;
    }

    public void setInstalaccion(String instalaccion) {
        this.instalaccion = instalaccion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(int horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getDuracionReserva() {
        return duracionReserva;
    }

    public void setDuracionReserva(String duracionReserva) {
        this.duracionReserva = duracionReserva;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}

