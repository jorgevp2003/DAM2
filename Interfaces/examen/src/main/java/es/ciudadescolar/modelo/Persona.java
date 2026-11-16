package es.ciudadescolar.modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 * Modelo (la M de MVC). Solo datos, sin nada de JavaFX.
 * Los getters son necesarios para que TableView (PropertyValueFactory) muestre las columnas.
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String departamento;
    private String jornada;
    private boolean newsletter;
    private String observaciones;

    public Persona(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public Persona(String nombre, String apellidos, String dni, LocalDate fechaNacimiento,
                   String telefono, String email, String departamento, String jornada,
                   boolean newsletter, String observaciones) {
        this(nombre, apellidos, dni);
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.departamento = departamento;
        this.jornada = jornada;
        this.newsletter = newsletter;
        this.observaciones = observaciones;
    }

    /** Edad calculada a partir de la fecha de nacimiento (0 si no hay fecha). */
    public int getEdad() {
        return fechaNacimiento == null ? 0 : Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getJornada() { return jornada; }
    public void setJornada(String jornada) { this.jornada = jornada; }

    public boolean isNewsletter() { return newsletter; }
    public void setNewsletter(boolean newsletter) { this.newsletter = newsletter; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return "Nombre: " + nombre
            + "\nApellidos: " + apellidos
            + "\nDNI: " + dni
            + "\nFecha nacimiento: " + fechaNacimiento + " (" + getEdad() + " años)"
            + "\nTeléfono: " + telefono
            + "\nEmail: " + email
            + "\nDepartamento: " + departamento
            + "\nJornada: " + jornada
            + "\nNewsletter: " + (newsletter ? "Sí" : "No")
            + "\nObservaciones: " + observaciones;
    }
}
