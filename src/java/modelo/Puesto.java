/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    
    public Puesto(String puesto, int id) {
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
}
