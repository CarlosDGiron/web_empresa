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
public class Venta {
    private int idVenta, nofactura,idCliente,idEmpleado;
    private String serie, fechafactura, fechaingreso;
    private Conexion c;

    public Venta() {
    }

    public Venta(int idVenta, int nofactura, String serie, String fechafactura, int idCliente, int idEmpleado, String fechaingreso) {
        this.idVenta = idVenta;
        this.nofactura = nofactura;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.serie = serie;
        this.fechafactura = fechafactura;
        this.fechaingreso = fechaingreso;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getNofactura() {
        return nofactura;
    }

    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(String fechafactura) {
        this.fechafactura = fechafactura;
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
            String query="INSERT INTO db_empresa.ventas(nofactura, serie, fechafactura, idCliente, fechaingreso) VALUES(?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNofactura());
            parametro.setString(2,getSerie());
            parametro.setString(3,getFechafactura());
            parametro.setInt(4,getIdCliente());
            parametro.setString(5,getFechaingreso());
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
            String query="UPDATE db_empresa.ventas SET nofactura=?, serie=?, fechafactura=?, idCliente=?, fechaingreso=? where idVentas=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNofactura());
            parametro.setString(2,getSerie());
            parametro.setString(3,getFechafactura());
            parametro.setInt(4,getIdCliente());
            parametro.setString(5,getFechaingreso());
            parametro.setInt(6,getIdVenta());
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
            String query="DELETE FROM db_empresa.ventas WHERE idVenta=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getIdVenta());
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
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.ventas;");
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
