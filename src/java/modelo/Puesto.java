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
public class Puesto {
    private String puesto;
    private int id;
    private Conexion c;
   
    public Puesto(){
    
    }
    
    public Puesto(int id, String puesto){
        this.puesto = puesto;
        this.id = id;
    }
  
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public HashMap drop_puesto(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="select idPuesto as id, puesto from db_empresa.puestos;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("puesto"));
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
            res=c.conexionDB.createStatement().executeQuery("Select idPuesto from db_empresa.puestos where puesto =\""+des+"\";");
            res.next();
            x=res.getString("idPuesto");
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
            res=c.conexionDB.createStatement().executeQuery("Select puesto from db_empresa.puestos where idPuesto ="+id+";");
            res.next();
            x=res.getString("puesto");
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
            String query="INSERT INTO db_empresa.puestos(puesto) VALUES(?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getPuesto());
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
            String query="UPDATE db_empresa.puestos SET puesto=? where idPuesto=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getPuesto());
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
            String query="DELETE FROM db_empresa.puestos WHERE idPuesto=?;";
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
            String encabezado []={"ID Puesto","Puesto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.puestos;");
            String datos[]=new String[2];
            while(res.next()){
                datos[0]=res.getString("idPuesto");
                datos[1]=res.getString("puesto");
                model.addRow(datos);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
