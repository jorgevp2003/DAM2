package com.example;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class InformeEmpleados {
public static void main(String[] args) {
    String jdbcUrl = "jdbc:mysql://localhost:3306/practica_informes";
    String username = "root";
    String password = "root";// o tu contraseña
    try (
        Connection connection =
        DriverManager.getConnection(
            jdbcUrl, username, password
        );
        InputStream informe =
        InformeEmpleados.class.getResourceAsStream("/Blank_A4_1.jasper")
        ) {
        if (informe == null) {
            throw new RuntimeException("No se encuentra el informe.");
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(
            informe,
            new HashMap<>(),
            connection
        );
        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(
            jasperPrint,
        "empleados_informe.pdf"
            );
        System.out.println("Informe generado correctamente.");
    } catch (Exception e) {
        e.printStackTrace();
    }}}