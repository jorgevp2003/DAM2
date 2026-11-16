package es.ciudadescolar;

/**
 * Lanzador que NO extiende Application.
 * Sirve para ejecutar con el botón "Run" de VS Code / IntelliJ sin el error
 * "JavaFX runtime components are missing".
 */
public class Lanzador {
    public static void main(String[] args) {
        Main.main(args);
    }
}
