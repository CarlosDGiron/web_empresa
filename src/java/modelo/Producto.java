/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author cana0
 */
public class Producto {
    private int idProducto , idMarca,existencia;
    private String producto, descripcion,imagen,fechaingreso,marca;
    private double precio_costo,precio_venta;
    private Conexion c;
    private Marca m;
    
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
        m=new Marca();
        this.marca=m.getDes(String.valueOf(idMarca));
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
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
    
     public int getMaxId(){
         int x;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("SELECT MAX(idProducto) as id FROM db_empresa.productos;");
            res.next();
            x=res.getInt("id");
            c.cerrar_conexion();
            return x;
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
            return 0;
        }
    }
    
    public HashMap drop_productoprecio(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="select idProducto as id, precio_venta from db_empresa.productos;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("precio_venta"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return drop;
    }
    
    public HashMap drop_producto(){
        HashMap<String,String> drop=new HashMap();
        c=new Conexion();
        c.abrir_conexion();
        try{
            String query="select idProducto as id, producto from db_empresa.productos;";
            ResultSet consulta=c.conexionDB.createStatement().executeQuery(query);
            while(consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("producto"));
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return drop;
    }
    
    public int maxVenta(int id){
        int x=0;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("Select existencia from db_empresa.productos where idProducto="+id+";");
            res.next();
            x=res.getInt("existencia");
        }catch(SQLException ex){
            System.out.println("Eror Id:"+ex.getMessage());
        }
        c.cerrar_conexion();
        return x;
    }    
    
    public String getId(String des){
         String x=null;
         c=new Conexion();
         c.abrir_conexion();
        try{
            ResultSet res;
            res=c.conexionDB.createStatement().executeQuery("Select idProducto from db_empresa.productos where productos =\""+des+"\";");
            res.next();
            x=res.getString("idProducto");
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
            res=c.conexionDB.createStatement().executeQuery("Select producto from db_empresa.productos where idProducto ="+id+";");
            res.next();
            x=res.getString("producto");
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
            //idProducto, producto,idMarca,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso
            String query="INSERT INTO db_empresa.productos(producto, idMarca, descripcion, imagen, precio_costo, precio_venta, existencia, fechaingreso) VALUES(?,?,?,?,?,?,?,?);";
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
    
    public  String jsonReporte() {
        DefaultTableModel model;
        model=this.mostrar();
        JsonObject json = new JsonObject();
        for (int row = 0; row < model.getRowCount(); row++) {
            JsonObject rowObject = new JsonObject();

            // Recorrer las columnas y agregar cada valor al objeto JSON de fila
            for (int col = 0; col < model.getColumnCount(); col++) {
                String columnName = model.getColumnName(col);
                Object cellValue = model.getValueAt(row, col);
                rowObject.addProperty(columnName, cellValue.toString());
            }
            // Agregar el objeto de fila al JSON principal
            json.add("row" + (row + 1), rowObject);
        }
        System.out.println(json);
        return json.toString();

        /* Usar Gson para escribir el JSON en un archivo
        try (FileWriter writer = new FileWriter("C:\\Productos.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(json, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }*/
    }    
}
