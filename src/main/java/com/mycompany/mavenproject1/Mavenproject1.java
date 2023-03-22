/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author merv
 */
public class Mavenproject1 {

    public static Conexion c = new Conexion();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int resp = 0;
        System.out.println("CRUD EJEMPLO \n1. actualiza archivo existente o Agregar nuevo registro\n2. Eliminar registro");
        resp = sc.nextInt();

        if (resp == 1) {
            String fechaHoraIngreso = "", tipoArchivo = "";
            long tamanoBytes;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccione un archivo");

            int seleccion = fileChooser.showOpenDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {

                java.io.File archivo = fileChooser.getSelectedFile(); //archivo elegido
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                fechaHoraIngreso = ahora.format(formato);

                tamanoBytes = archivo.length();
                tipoArchivo = URLConnection.guessContentTypeFromName(archivo.getName());

                System.out.println("Archivo seleccionado: " + archivo.getName() + "\n"
                        + "fecha de ingreso: " + fechaHoraIngreso + "\n"
                        + "tamanoBytes: " + tamanoBytes + "\n"
                        + "tipo Archivo: " + tipoArchivo);

                c.getConexion();
                c.Insertar("merv", archivo.getName(), fechaHoraIngreso, tamanoBytes, tipoArchivo, archivo.getPath(), "nose");
            } else {
                System.out.println("No se seleccionó ningún archivo.");
            }

        } else if (resp == 2) {
            String fechaHoraIngreso = "", tipoArchivo = "";
            long tamanoBytes;
            java.io.File archivo =null;
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setDialogTitle("Seleccione un archivo");

            int seleccion = fileChooser.showOpenDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {

                archivo = fileChooser.getSelectedFile(); //archivo elegido
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                fechaHoraIngreso = ahora.format(formato);

                tamanoBytes = archivo.length();
                tipoArchivo = URLConnection.guessContentTypeFromName(archivo.getName());

                System.out.println("Archivo seleccionado: " + archivo.getName() + "\n"
                        + "fecha de ingreso: " + fechaHoraIngreso + "\n"
                        + "tamanoBytes: " + tamanoBytes + "\n"
                        + "tipo Archivo: " + tipoArchivo);

                c.getConexion();
                c.borrar(archivo.getName(), "merv");

            }else {
                System.out.println("No se seleccionó ningún archivo.");
            }
        }
    }
}
