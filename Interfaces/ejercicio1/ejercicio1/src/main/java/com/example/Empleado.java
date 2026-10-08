package com.example;
import java.time.LocalDate;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private String departamento;
    private String puesto;
    private String tipoContrato;
    private String salario;
    private String jornada;
    private LocalDate fechaIncorporacion;
    private String observaciones;

    public Empleado(String nombre, String apellidos, String dni,
        LocalDate fechaNacimiento, String telefono,
        String correo, String departamento, String puesto,
        String tipoContrato, String salario, String jornada,
        LocalDate fechaIncorporacion, String observaciones) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.departamento = departamento;
        this.puesto = puesto;
        this.tipoContrato = tipoContrato;
        this.salario = salario;
        this.jornada = jornada;
        this.fechaIncorporacion = fechaIncorporacion;
        this.observaciones = observaciones;
    }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getApellidos() {
            return apellidos;
        }
        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
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
        public String getTelefono() {
            return telefono;
        }
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        public String getCorreo() {
            return correo;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
        public String getDepartamento() {
            return departamento;
        }
        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }
        public String getPuesto() {
            return puesto;
        }
        public void setPuesto(String puesto) {
            this.puesto = puesto;
        }
        public String getTipoContrato() {
            return tipoContrato;
        }
        public void setTipoContrato(String tipoContrato) {
            this.tipoContrato = tipoContrato;
        }
        public String getSalario() {
            return salario;
        }
        public void setSalario(String salario) {
            this.salario = salario;
        }
        public String getJornada() {
            return jornada;
        }
        public void setJornada(String jornada) {
            this.jornada = jornada;
        }
        public LocalDate getFechaIncorporacion() {
            return fechaIncorporacion;
        }
        public void setFechaIncorporacion(LocalDate
        fechaIncorporacion) {
            this.fechaIncorporacion = fechaIncorporacion;
        }
        public String getObservaciones() {
            return observaciones;
        }
        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }


         
}