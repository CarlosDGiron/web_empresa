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
public class Producto {
    private int idProducto , idMarca,existencia;
    private String producto, descripcion,imagen,fechaingreso;
    private double precio_costo,precio_venta;
    private Conexion c;
    
    public Producto() {
    }

    public Producto(int idProducto, String producto,  int idMarca, String descripcion, String imagen, double precio_costo, double precio_venta, int existencia, String fechaingreso) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.existencia = existencia;
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaingreso = fechaingreso;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            //idProducto, producto,idMarca,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso
            String query="INSERT INTO db_empresa.productos(producto, idMarca, descripcion, imagen, precio_costo, precio_venta, existencia, fechaingreso) VALUES(?,?,?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getIdMarca());
            parametro.setString(3,getDescripcion());
            parametro.setString(4,getImagen());
            parametro.setDouble(5,getPrecio_costo());
            parametro.setDouble(6,getPrecio_venta());
            parametro.setInt(7,getExistencia());
            parametro.setString(8,getFechaingreso());
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
            String query="UPDATE db_empresa.productos SET producto=?, idMarca=?, descripcion=?, imagen=?, precio_costo=?, precio_venta=?, existencia=?, fechaingreso=? where idProducto=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getIdMarca());
            parametro.setString(3,getDescripcion());
            parametro.setString(4,getImagen());
            parametro.setDouble(5,getPrecio_costo());
            parametro.setDouble(6,getPrecio_venta());
            parametro.setInt(7,getExistencia());
            parametro.setString(8,getFechaingreso());
            parametro.setInt(9,getIdProducto());
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
            String query="DELETE FROM db_empresa.productos WHERE idProducto=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdProducto());
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
            Marca m = new Marca();
            ResultSet res;
            //idProducto,producto, idMarca, descripcion, imagen, precio_costo, precio_venta, existencia, fechaingreso
            String encabezado []={"ID Producto","Producto","Marca","Descripción","Imagen","Precio Costo","Precio Venta","Existencia","Fecha de ingreso","ID Marca"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.productos;");
            String datos[]=new String[10];
            while(res.next()){
                datos[0]=res.getString("idProducto");
                datos[1]=res.getString("producto");
                datos[9]=res.getString("idMarca");
                datos[3]=res.getString("descripcion");
                datos[4]=res.getString("imagen");
                datos[5]=res.getString("precio_costo");
                datos[6]=res.getString("precio_venta");                
                datos[7]=res.getString("existencia");
                datos[8]=res.getString("fechaingreso");
                datos[2]=m.getDes(datos[9]);
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
