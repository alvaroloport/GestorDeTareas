package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    public static Connection getConnection(){
        Connection conexion = null;
        String base = "gestortareas";
        String url = "jdbc:mysql://127.0.0.1:3306/" + base;
        String usuario = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch(Exception e){
            System.out.println("Error" + e);
        }

        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion = getConnection();
        if(conexion != null){
            System.out.printf("Conectado");
        }else{
            System.out.println("No conectado");
        }
    }

}
