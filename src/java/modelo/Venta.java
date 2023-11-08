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
    private Empleado empleado;
    private Cliente cliente;
    
    public Venta() {
        empleado=new Empleado();
        cliente=new Cliente();
    }

    public Venta(int idVenta, int nofactura, String serie, String fechafactura, int idCliente, int idEmpleado, String fechaingreso) {
        this.idVenta = idVenta;
        this.nofactura = nofactura;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.serie = serie;
        this.fechafactura = fechafactura;
        this.fechaingreso = fechaingreso;
        empleado=new Empleado();
        cliente=new Cliente();
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
    
    public int maxIdVenta(){
         int x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT MAX(idVenta) as id FROM db_empresa.ventas;");
            res.next();
            x=res.getInt("id");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return 0;
        }
    }
    
    public String facturaPorId(int id){
         String x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT nofactura FROM db_empresa.ventas WHERE idVenta="+String.valueOf(id)+";");
            res.next();
            x=res.getString("nofactura");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return null;
        }
    }
    
    public int[] listaIdVenta(){
        int idetalle[];
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="SELECT COUNT(idVenta) AS id FROM db_empresa.ventas;";
            ResultSet count=c.conexionDB.createStatement().executeQuery(query);
            query="SELECT idVenta AS id FROM db_empresa.ventas;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            count.next();
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
        
    public char seriePorId(int id){
         char x=' ';
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT serie FROM db_empresa.ventas WHERE idVenta="+String.valueOf(id)+";");
            res.next();
            x=res.getString("serie").charAt(0);
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return x;
        }
    }
      public String fechafacturaPorId(int id){
         String x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT fechafactura FROM db_empresa.ventas WHERE idVenta="+String.valueOf(id)+";");
            res.next();
            x=res.getString("fechafactura");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return null;
        }
    }
      
    public int idClientePorId(int id){
         int x=0;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT idCliente FROM db_empresa.ventas WHERE idVenta="+String.valueOf(id)+";");
            res.next();
            x=res.getInt("idCliente");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return x;
        }
    }  
    
    public int idEmpleadoPorId(int id){
         int x=0;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT idEmpleado FROM db_empresa.ventas WHERE idVenta="+String.valueOf(id)+";");
            res.next();
            x=res.getInt("idEmpleado");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return x;
        }
    }  
      
    public boolean existe (int idventa){
        try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="SELECT * from db_empresa.ventas where idVenta='"+String.valueOf(idventa)+"';";
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
            String query="INSERT INTO db_empresa.ventas(nofactura, serie, fechafactura, idCliente, idEmpleado, fechaingreso) VALUES(?,?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNofactura());
            parametro.setString(2,getSerie());
            parametro.setString(3,getFechafactura());
            parametro.setInt(4,getIdCliente());
            parametro.setInt(5,getIdEmpleado());
            parametro.setString(6,getFechaingreso());
            int ejecutar=parametro.executeUpdate();
            if(ejecutar>0){
                idVenta=maxIdVenta();                
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
            String query="UPDATE db_empresa.ventas SET nofactura=?, serie=?, fechafactura=?, idCliente=?, idEmpleado=?, fechaingreso=? where idVenta=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setInt(1,getNofactura());
            parametro.setString(2,getSerie());
            parametro.setString(3,getFechafactura());
            parametro.setInt(4,getIdCliente());
            parametro.setInt(5,getIdEmpleado());
            parametro.setString(6,getFechaingreso());
            parametro.setInt(7,getIdVenta());
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
            String query="DELETE FROM db_empresa.ventas WHERE idVenta=?;";
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
            ResultSet res;
            String encabezado []={"ID Venta","No. Factura","Proveedor","Serie","Fecha de la factura", "ID Cliente","ID Empleado","Fecha ingreso"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.ventas;");
            String datos[]=new String[9];
            while(res.next()){
                datos[0]=res.getString("idVenta");
                datos[1]=res.getString("nofactura");
                datos[2]=res.getString("serie");
                datos[3]=res.getString("fechafactura");
                datos[7]=res.getString("idCliente");   
                datos[8]=res.getString("idEmpleado");
                datos[4]=cliente.nombresPorId(Integer.parseInt(datos[7]));
                datos[5]=empleado.nombresPorId(Integer.parseInt(datos[8]));
                datos[6]=res.getString("fechaingreso");                
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
