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
public class Compra_detalle {
    private int idCompra_detalle, idCompra, idProducto, cantidad;
    private double precio_costo_unitario;
    private Conexion c;

    public Compra_detalle() {
    }

    public Compra_detalle(int idCompra_detalle, int idCompra, int idProducto, int cantidad, double precio_costo_unitario) {
        this.idCompra_detalle = idCompra_detalle;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public int getIdCompra_detalle() {
        return idCompra_detalle;
    }

    public void setIdCompra_detalle(int idCompra_detalle) {
        this.idCompra_detalle = idCompra_detalle;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public double getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(double precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.compras_detalle(IdCompra, IdProducto, cantidad, precio_costo_unitario) VALUES(?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecio_costo_unitario());
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
            String query="UPDATE db_empresa.compras_detalle SET idCompra=?, idProducto=?, cantidad=?, precio_costo_unitario=? where idCompra_detalle=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdCompra());
            parametro.setInt(2,getIdProducto());
            parametro.setInt(3,getCantidad());
            parametro.setDouble(4,getPrecio_costo_unitario());
            parametro.setInt(5,getIdCompra_detalle());
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
            String query="DELETE FROM db_empresa.compras_detalle WHERE idCompra=?;";
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
            Producto p = new Producto();
            ResultSet res;
            String encabezado []={"ID Compra Detalle","ID Compra","Producto","Cantidad","Precio costo unitario","ID Producto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.compras_detalle;");
            String datos[]=new String[6];
            while(res.next()){
                datos[0]=res.getString("idCompra_detalle");
                datos[1]=res.getString("idCompra");
                datos[5]=res.getString("idProducto");
                datos[3]=res.getString("cantidad");
                datos[4]=res.getString("precio_costo_unitario");   
                datos[2]=p.getDes(datos[5]);
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
    
    public DefaultTableModel mostrarPorId(int idCompra_){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Producto p = new Producto();
            ResultSet res;
            String encabezado []={"ID Compra Detalle","ID Compra","Producto","Cantidad","Precio costo unitario","ID Producto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.compras_detalle WHERE idCompra="+idCompra_+";");
            String datos[]=new String[8];
            while(res.next()){
                datos[0]=res.getString("idCompra_detalle");
                datos[1]=res.getString("IdCompra");
                datos[5]=res.getString("idProducto");
                datos[3]=res.getString("cantidad");
                datos[4]=res.getString("precio_costo_unitario");   
                datos[2]=p.getDes(datos[5]);
                model.addRow(datos); 
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
    
    public int getCantidadProductos(int IdCompra_){
        c=new Conexion();
        c.abrir_conexion();
         int cant=0;
        try{
            ResultSet res;
            String query="Select COUNT(*) as cant from db_empresa.compras_detalle WHERE idCompra="+String.valueOf(IdCompra_)+";";
            res=c.conexionDB.createStatement().executeQuery(query);
            res.next();
            cant =res.getInt("cant");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return cant;
    }
}
