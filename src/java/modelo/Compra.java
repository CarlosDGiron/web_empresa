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
    
    public int eliminar(){        
        try {
            c=new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="DELETE FROM db_empresa.compras WHERE idCompra=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
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
            Proveedor p = new Proveedor();
            ResultSet res;
            String encabezado []={"ID Compra","No. Orden de compra","Proveedor","Fecha de orden","Fecha de ingreso", "ID Proveedor"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.compras;");
            String datos[]=new String[8];
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
