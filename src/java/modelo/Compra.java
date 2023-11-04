/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cana0
 */
public class Compra {
    private int idCompra, no_orden_compra, idProveedor;
    private String fecha_orden, fechaingreso;
    private Conexion c;
    
    public Compra() {
    }

    public Compra(int idCompra, int no_orden_compra, int idProveedor, String fecha_orden, String fechaingreso) {
        this.idCompra = idCompra;
        this.no_orden_compra = no_orden_compra;
        this.idProveedor = idProveedor;
        this.fecha_orden = fecha_orden;
        this.fechaingreso = fechaingreso;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(int no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    //SELECT MAX(idCompra) FROM db_empresa.compras;
    public int maxIdCompra(){
         int x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT MAX(idCompra) as id FROM db_empresa.compras;");
            res.next();
            x=res.getInt("id");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return 0;
        }
    }
    
    public String ordenPorId(int id){
         String x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT no_orden_compra FROM db_empresa.compras WHERE idCompra="+String.valueOf(id)+";");
            res.next();
            x=res.getString("no_orden_compra");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return null;
        }
    }
    
    public boolean existe (int idcompra){
        try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="SELECT * from db_empresa.compras where idCompra='"+String.valueOf(idcompra)+"';";
        res=c.conexionDB.createStatement().executeQuery(query);
        return res.next();
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        return false;
        }
        finally{
            c.cerrar_conexion();
        }        
    }
    
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.compras(no_orden_compra, idProveedor, fecha_orden, fechaingreso) VALUES(?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNo_orden_compra());
            parametro.setInt(2,getIdProveedor());
            parametro.setString(3,getFecha_orden());
            parametro.setString(4,getFechaingreso());
            int ejecutar=parametro.executeUpdate();
            if(ejecutar>0){
                idCompra=maxIdCompra();
                c.cerrar_conexion();
                return 1;
            }else{
                return 0;
            }
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
            String query="UPDATE db_empresa.compras SET no_orden_compra=?, idProveedor=?, fecha_orden=?, fechaingreso=? where idCompra=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNo_orden_compra());
            parametro.setInt(2,getIdProveedor());
            parametro.setString(3,getFecha_orden());
            parametro.setString(4,getFechaingreso());
            parametro.setInt(5,getIdCompra());
            int ejecutar=parametro.executeUpdate();
            c.cerrar_conexion();
            return ejecutar;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    public boolean eliminar(){        
        try {
            c=new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="DELETE FROM db_empresa.compras WHERE idCompra=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
            int ejecutar = parametro.executeUpdate();
            c.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
        }
        return !existe(getIdCompra());
    }
    
    public DefaultTableModel mostrar(){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Proveedor p = new Proveedor();
            ResultSet res;
            String encabezado []={"ID Compra","No. Orden de compra","Proveedor","Fecha de orden","Fecha de ingreso", "ID Proveedor"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.compras;");
            String datos[]=new String[6];
            while(res.next()){
                datos[0]=res.getString("idCompra");
                datos[1]=res.getString("no_orden_compra");
                datos[5]=res.getString("idProveedor");
                datos[3]=res.getString("fecha_orden");
                datos[4]=res.getString("fechaingreso");   
                datos[2]=p.getDes(datos[5]);
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
