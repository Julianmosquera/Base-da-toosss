/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author julian
 */
public class Metodos {

    static Connection con = null;
    static Statement s = null;
    public static String db = "base1";
    public static String url = "jdbc:mysql://localhost/"+db;
    public static String user = "root";
    public static String pass = "";

    public static void Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println("error al cargar el driver");
        } catch (SQLException ex) {
            System.out.println("error al conectarse a la base");
        }
    }

    public static void borrar() {
        try {
            s = con.createStatement();
            String nombre = JOptionPane.showInputDialog("nombre del alumno que vas a borrar");
            s.execute("delete from Alumnos where Nombre='" + nombre+"';");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void Modificar() {
        try {
            s = con.createStatement();
            String nom = JOptionPane.showInputDialog("Nombre del registro que quieres modificar");
            String ape = JOptionPane.showInputDialog("Apellido nuevo");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad nueva"));
            
            
            s.execute("update Alumnos set Edad=" + edad + ",Apellidos='" + ape + "'where Nombre='" + nom + "';");

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public static void insertar(int edad, String nombre, String apellidos) {
        try {
            
            s = con.createStatement();

            s.executeUpdate("insert into Alumnos values('" + nombre + "','" + apellidos + "'," + edad + ");");
            JOptionPane.showMessageDialog(null,"Insercion realizada");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void visualizar() {
        try {
            s = con.createStatement();
            ResultSet r = s.executeQuery("select * from Alumnos");

            while (r.next()) {
                //System.out.println(r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
                JOptionPane.showMessageDialog(null,r.getString("Nombre") + ", " + r.getString("Apellidos") + " " + r.getString("Edad"));
            }
            r.close();

        } catch (Exception e) {
            System.out.println("ERROR ---> " + e);
        }
    }

}
