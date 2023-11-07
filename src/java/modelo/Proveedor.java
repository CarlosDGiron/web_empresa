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
public class Proveedor {
    private int idProveedor;
    private String proveedor,nit,direccion,telefono;
    private Conexion c;

    public Proveedor(int idProveedor, String proveedor, String nit, String dirección, String telefono) {
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = dirección;
        this.telefono = telefono;
    }

    public Proveedor() {
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.proveedores(proveedor,nit,dirección,telefono) VALUES(?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
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
            String query="UPDATE db_empresa.proveedores SET proveedor=?, nit=?, direccion=?, telefono=? where idProveedor=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getProveedor());
            parametro.setString(2,getNit());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setInt(5,getIdProveedor());
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
            String query="DELETE FROM db_empresa.proveedores WHERE idProveedor=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdProveedor());
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
        c.abrir_conexion();
        try{
            Puesto p = new Puesto();
            ResultSet res;
            String encabezado []={"ID Proveedor","Proveedor","NIT","Dirección","Telefono"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.proveedores;");
            String datos[]=new String[8];
            while(res.next()){
                datos[0]=res.getString("idProveedor");
                datos[1]=res.getString("proveedor");
                datos[2]=res.getString("nit");
                datos[3]=res.getString("direccion");
                datos[4]=res.getString("telefono");   
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
    
    public HashMap drop_proveedor(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="select idProveedor as id, proveedor from db_empresa.proveedores;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("proveedor"));
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
            res=c.conexionDB.createStatement().executeQuery("Select idProveedor from db_empresa.proveedores where proveedor =\""+des+"\";");
            res.next();
            x=res.getString("idProveedor");
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
            res=c.conexionDB.createStatement().executeQuery("Select proveedor from db_empresa.proveedores where idProveedor ="+id+";");
            res.next();
            x=res.getString("proveedor");
        }catch(SQLException ex){
            System.out.println("Eror Des:"+ex.getMessage());
        }
        c.cerrar_conexion();
        return x;
    }
}
