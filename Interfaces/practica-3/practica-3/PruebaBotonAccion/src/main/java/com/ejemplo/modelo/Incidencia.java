package com.ejemplo.modelo;

/**
 * MODELO: datos de una incidencia del taller informático.
 *
 * <p>Es una clase "POJO" (Plain Old Java Object): atributos privados,
 * constructor, getters y setters. No sabe nada de JavaFX ni de ventanas.
 * Sirve para pasar los datos de la ventana principal a la secundaria
 * en un solo objeto.</p>
 *
 * @author Jorge Vazquez Poto
 * @version 1.0
 */
public class Incidencia {

    /** Nombre o identificador del equipo (ej: "PC-AULA3-07"). */
    private String equipo;

    /** Descripción breve del problema. */
    private String descripcion;

    /**
     * Crea una incidencia con todos sus datos.
     *
     * @param equipo      nombre del equipo
     * @param descripcion descripción breve de la incidencia
     */
    public Incidencia(String equipo, String descripcion) {
        this.equipo = equipo;
        this.descripcion = descripcion;
    }

    /**
     * @return nombre del equipo
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * @param equipo nuevo nombre del equipo
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    /**
     * @return descripción de la incidencia
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion nueva descripción de la incidencia
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Representación en texto (útil para depurar con System.out.println).
     *
     * @return texto con los datos de la incidencia
     */
    @Override
    public String toString() {
        return "Incidencia [equipo=" + equipo + ", descripcion=" + descripcion + "]";
    }
}
