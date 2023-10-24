/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cana0
 */
public class Marca {
    private int id;
    private String marca;
    private Conexion c;

    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public Marca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public HashMap drop_puesto(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="select idMarca as id, marca from db_empresa.marcas;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return drop;
    }
     public String getId(String des){
         String x=null;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("Select idMarca from db_empresa.marcas where marca =\""+des+"\";");
            res.next();
            x=res.getString("idMarca");
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
        }
        c.cerrar_conexion();
        return x;
    }
    
    public String getDes(String id){
        String x=null;  
        c=new Conexion();
        c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("Select marca from db_empresa.marcas where idMarca ="+id+";");
            res.next();
            x=res.getString("marca");
        }catch(SQLException ex){
            System.out.println("Eror Des:"+ex.getMessage());
        }
        c.cerrar_conexion();
        return x;
    }
    
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.marcas(marca) VALUES(?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getMarca());
            int ejecutar=parametro.executeUpdate();            
            c.cerrar_conexion();
            return ejecutar;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    public int modificar(){
        try {
            c= new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="UPDATE db_empresa.marcas SET marca=? where idMarca=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getMarca());
            parametro.setInt(2,getId());
            int ejecutar=parametro.executeUpdate();
            c.cerrar_conexion();
            return ejecutar;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    public int eliminar(){        
        try {
            c=new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="DELETE FROM db_empresa.marcas WHERE idMarca=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getId());
            int ejecutar = parametro.executeUpdate();
            c.cerrar_conexion();
            return ejecutar;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    public DefaultTableModel mostrar(){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        try{
            c.abrir_conexion();
            ResultSet res;
            String encabezado []={"ID Marca","Marca"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.marcas;");
            String datos[]=new String[2];
            while(res.next()){
                datos[0]=res.getString("idMarca");
                datos[1]=res.getString("marca");
                model.addRow(datos);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
