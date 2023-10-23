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
public class Cliente extends Persona{
    private String nit,correo_electronico;
    private Conexion c;
    
    public Cliente(){}

    public Cliente(int id, String nombres, String apellidos,  String nit, boolean genero, String telefono, String correo_electronico, String fechaingreso) {
        super(id, genero, nombres, apellidos, telefono, fechaingreso);
        this.nit = nit;
        this.correo_electronico = correo_electronico;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    
    @Override
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.clientes(nombres,apellidos,nit,genero,telefono,correo_electronico,fechaingreso) VALUES(?,?,?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setBoolean(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFechaingreso());
            int ejecutar=parametro.executeUpdate();            
            c.cerrar_conexion();
            return ejecutar;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
     @Override
    public int modificar(){
        try {
            c= new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="UPDATE db_empresa.clientes SET nombres=?, apellidos=?, nit=?, genero=?, telefono=?, correo_electronico=?, fechaingreso=? where idCliente=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getNit());
            parametro.setBoolean(4,getGenero());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFechaingreso());
            parametro.setInt(8,getId());
            int ejecutar=parametro.executeUpdate();
            c.cerrar_conexion();
            return ejecutar;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            c.cerrar_conexion();
            return 0;
        }
    }
    
    @Override
    public int eliminar(){        
        try {
            c=new Conexion();
            c.abrir_conexion();
            PreparedStatement parametro;
            String query="DELETE FROM db_empresa.clientes WHERE idCliente=?;";
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
    
    @Override
    public DefaultTableModel mostrar(){
        DefaultTableModel model=new DefaultTableModel();
        c=new Conexion();
        c.abrir_conexion();
        try{
            Puesto p = new Puesto();
            ResultSet res;
            String encabezado []={"ID Empleado","Nombres","Apellidos","Dirección","Teléfono","DPI","Genero","Fecha de nacimiento","Puesto","Fecha de inicio de labores","Fecha de ingreso","ID Puesto"};
            model.setColumnIdentifiers(encabezado);
            res=c.conexionDB.createStatement().executeQuery("Select * from db_empresa.empleados;");
            String datos[]=new String[12];
            while(res.next()){
                datos[0]=res.getString("idEmpleado");
                datos[1]=res.getString("nombres");
                datos[2]=res.getString("apellidos");
                datos[3]=res.getString("direccion");
                datos[4]=res.getString("telefono");
                datos[5]=res.getString("DPI");
                datos[6]=getStrGenero(res.getString("genero"));                
                datos[7]=res.getString("fecha_nacimiento");
                datos[11]=res.getString("idPuesto");
                datos[8]=p.getDes(datos[11]);
                datos[9]=res.getString("fecha_inicio_labores");
                datos[10]=res.getString("fechaingreso");
                model.addRow(datos);             
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        c.cerrar_conexion();
        return model;
    }
}
