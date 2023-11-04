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
public class Venta_detalle {
    private int idVenta_detalle, idVenta, idProducto, cantidad;
    private double precio_unitario;
    private Conexion c;

    public Venta_detalle() {
    }

    public Venta_detalle(int idVenta_detalle, int idVenta, int idProducto, int cantidad, double precio_unitario) {
        this.idVenta_detalle = idVenta_detalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getIdVenta_detalle() {
        return idVenta_detalle;
    }

    public void setIdVenta_detalle(int idVenta_detalle) {
        this.idVenta_detalle = idVenta_detalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    
    public int[] idDetallePorIdVenta(int idv_){
        int idetalle[];
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="SELECT COUNT(idVenta_detalle) AS id FROM db_empresa.ventas_detalle WHERE idVenta='"+String.valueOf(idv_)+" GROUP BY idVenta_detalle';";
            ResultSet count=c.conexionDB.createStatement().executeQuery(query);
            query="select idVenta_detalle as id from db_empresa.ventas_detalle where idVenta='"+String.valueOf(idv_)+"';";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            count.next();
            System.out.println(count.getInt("id"));
            idetalle = new int[count.getInt("id")];
            int i=0;
            while(consulta.next()){
                idetalle[i]= consulta.getInt("id");
                i++;
            }
        c.cerrar_conexion();
        return idetalle;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return null;
        }        
    }
    
    public int getCantidadProductos(int IdVenta_){
        c=new Conexion();
        c.abrir_conexion();
         int cant=0;
        try{
            ResultSet res;
            String query="Select COUNT(*) as cant from db_empresa.ventas_detalle WHERE idVenta="+String.valueOf(IdVenta_)+";";
            res=c.conexionDB.createStatement().executeQuery(query);
            res.next();
            cant =res.getInt("cant");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return cant;
    }
    
    public boolean existe (int idventa){
        try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="SELECT * from db_empresa.ventas_detalle where idVenta='"+String.valueOf(idventa)+"';";
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
            String query="INSERT INTO db_empresa.ventas_detalle(IdVenta, IdProducto, cantidad, precio_unitario) VALUES(?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecio_unitario());
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
            String query="UPDATE db_empresa.ventas_detalle SET idVenta=?, idProducto=?, cantidad=?, precio_unitario=? where idVenta_detalle=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecio_unitario());
            parametro.setInt(5,getIdVenta_detalle());
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
            String query="DELETE FROM db_empresa.ventas_detalle WHERE idVenta=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
            int ejecutar = parametro.executeUpdate();
            c.cerrar_conexion();
            return !existe(getIdVenta());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return !existe(getIdVenta());
        }
    }
    
    public DefaultTableModel mostrar(){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Producto p = new Producto();
            ResultSet res;
            String encabezado []={"ID Venta Detalle","ID Venta","Producto","Cantidad","Precio unitario","ID Producto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.ventas_detalle;");
            String datos[]=new String[6];
            while(res.next()){
                datos[0]=res.getString("idVenta_detalle");
                datos[1]=res.getString("idVenta");
                datos[5]=res.getString("idProducto");
                datos[3]=res.getString("cantidad");
                datos[4]=res.getString("precio_unitario");   
                datos[2]=p.getDes(datos[5]);
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
    
    public DefaultTableModel mostrarPorId(int idVenta_){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Producto p = new Producto();
            ResultSet res;
            String encabezado []={"ID Venta Detalle","ID Venta","Producto","Cantidad","Precio unitario","ID Producto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.ventas_detalle WHERE idVenta="+idVenta_+";");
            String datos[]=new String[8];
            while(res.next()){
                datos[0]=res.getString("idVenta_detalle");
                datos[1]=res.getString("IdVenta");
                datos[5]=res.getString("idProducto");
                datos[3]=res.getString("cantidad");
                datos[4]=res.getString("precio_unitario");   
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
