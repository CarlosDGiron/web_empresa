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
public class Cliente extends Persona{
    private String nit,correo_electronico;
    private Conexion c;
    
    public Cliente(){}

    public Cliente(int id, String nombres, String apellidos,  String nit, boolean genero, String telefono, String correo_electronico, String fechaingreso) {
        super(id, genero, nombres, apellidos, telefono, fechaingreso);
        this.nit = nit;
        this.correo_electronico = correo_electronico;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    
    @Override
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.clientes(nombres,apellidos,nit,genero,telefono,correo_electronico,fechaingreso) VALUES(?,?,?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setBoolean(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFechaingreso());
            int ejecutar=parametro.executeUpdate();            
            c.cerrar_conexion();
            return ejecutar;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
     @Override
    public int modificar(){
        try {
            c= new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="UPDATE db_empresa.clientes SET nombres=?, apellidos=?, nit=?, genero=?, telefono=?, correo_electronico=?, fechaingreso=? where idCliente=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setBoolean(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFechaingreso());
            parametro.setInt(8,getId());
            int ejecutar=parametro.executeUpdate();
            c.cerrar_conexion();
            return ejecutar;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    @Override
    public int eliminar(){        
        try {
            c=new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="DELETE FROM db_empresa.clientes WHERE idCliente=?;";
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
    
    @Override
    public DefaultTableModel mostrar(){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Puesto p = new Puesto();
            ResultSet res;
            String encabezado []={"ID Cliente","Nombres","Apellidos","NIT","Genero","Telefono","Correo electrónico","Fecha de ingreso"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.clientes;");
            String datos[]=new String[8];
            while(res.next()){
                datos[0]=res.getString("idCliente");
                datos[1]=res.getString("nombres");
                datos[2]=res.getString("apellidos");
                datos[3]=res.getString("NIT");
                datos[4]=getStrGenero(res.getString("genero"));   
                datos[5]=res.getString("telefono");             
                datos[6]=res.getString("correo_electronico");
                datos[7]=res.getString("fechaingreso");
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
    
    public HashMap drop_cliente(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="SELECT idCliente AS id, CONCAT(nombres,\" \",apellidos) AS cliente FROM db_empresa.clientes;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("cliente"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return drop;
    }
      
    public String nombresPorId(int id){
        String x=null;  
        c=new Conexion();
        c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT CONCAT(nombres,\" \",apellidos) AS cliente FROM db_empresa.clientes WHERE idCliente="+id+";");
            res.next();
            x=res.getString("cliente");
        }catch(SQLException ex){
            System.out.println("Eror Des:"+ex.getMessage());
        }
        c.cerrar_conexion();
        return x;
    }
 }
