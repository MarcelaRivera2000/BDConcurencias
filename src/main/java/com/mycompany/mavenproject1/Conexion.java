/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author merv
 */
public class Conexion {

    public Connection con;

    public Connection getConexion() {
        try {
            String ConexionURL = "jdbc:mysql://localhost:3306/DBConcu?";
            String user = "root";
            String pass = "123456789";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(ConexionURL, user, pass);
            JOptionPane.showMessageDialog(null, "conexion Exitosa");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void Insertar(String user,String name, String fecha, long tam, String tipo ,String pathUser,String pathServer) {
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM DBConcu.registroArchivos WHERE nombre = ? AND userr= ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, user);
            rs = pstmt.executeQuery();
            
      if (rs.next()) {
        sql = "UPDATE DBConcu.registroArchivos SET fechaIngreso = ?, tipo = ?, tamanio = ?, pathUser = ?, pathServer = ? WHERE userr = ? AND nombre = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, fecha);
        pstmt.setString(2, tipo);
        pstmt.setLong(3, tam);
        pstmt.setString(4, pathUser);
        pstmt.setString(5, pathServer);
        pstmt.setString(6, user);
        pstmt.setString(7, name);

        int filasActualizadas = pstmt.executeUpdate();
        System.out.println("Se han actualizado " + filasActualizadas + " filas en la tabla archivos.");
          
      } else {
       sql = "INSERT INTO DBConcu.registroArchivos(userr,nombre,fechaIngreso,tipo,tamanio,pathUser,pathServer) values (?,?,?,?,?,?,?)";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, user);
            sentencia.setString(2, name);
            sentencia.setString(3, fecha);
            sentencia.setString(4, tipo);
            sentencia.setLong(5, tam);
            sentencia.setString(6, pathUser);
            sentencia.setString(7, pathServer);
            sentencia.executeUpdate();
            con.close();
      }
            JOptionPane.showMessageDialog(null, "Ingreso Exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrar(String name, String user){
        try {
        String query = "DELETE FROM DBConcu.registroArchivos WHERE nombre = ? AND userr= ?";
        PreparedStatement sentencia = con.prepareStatement(query);
        sentencia.setString(1, name);
        sentencia.setString(2, user);
        sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

}


/*
CHANGE MASTER TO MASTER_HOST='125.564.12.1', MASTER_PORT=3306,
#    MASTER_USER='joe', MASTER_PASSWORD='secret';
public void leer() {
        String query = "select * from concu_bd.registroArchivos";
        Statement st;
        
        System.out.println("llega aqui");
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            String userData = "";
            System.out.println("llega aqui 2 "+rs);
            while (rs.next()) {
                userData = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4) + " : " + rs.getLong(5);
                System.out.println(userData);
            }

            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }           
*/
