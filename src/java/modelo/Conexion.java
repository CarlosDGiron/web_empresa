/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cana0
 */
public class Conexion {
    public Connection conexionDB;
    private final String puerto="3306";
    private final String db="db_empresa_old";
    //jdbc:mysql://localhost:3306/?user=usr_empresa
    //jdbc:mysql://localhost:%s/%s?serverTimezone=UTC
    private final String urlConexion=String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC", puerto,db);
    private final String usuario="usr_empresa";
    private final String pass="Empres@123";
    private final String jdbc="com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
        try{            
            Class.forName(jdbc);
            conexionDB=DriverManager.getConnection(urlConexion, usuario, pass);
                        System.out.println("Conexion exitosa.");

        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Eror:"+ex.getMessage());
        }
    }
    public void ejecutar_script(String script){
        try{
            conexionDB.prepareStatement(script);
            conexionDB.beginRequest();
        }catch(SQLException ex){
            System.out.println("Eror:"+ex.getMessage());
        }
    }
    
    public void cerrar_conexion(){
        try{
            conexionDB.close();
            System.out.println("Conexion cerrada.");
        }catch(SQLException ex){
            System.out.println("Eror:"+ex.getMessage());
        }
    }
}