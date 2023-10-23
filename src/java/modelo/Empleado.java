/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author cana0
 */
public class Empleado extends Persona {
    private int puesto;
    private String dpi,direccion,fecha_nacimiento,fecha_inicio_labores;
    private Conexion c;
    
    public Empleado() {}

    public Empleado(int id, String nombres, String apellidos, String direccion, String telefono, String dpi, boolean genero, String fecha_nacimiento,int puesto, String fechaingreso,String fecha_inicio_labores) {
        super(id, genero, nombres, apellidos, telefono, fechaingreso);
        this.puesto = puesto;
        this.dpi = dpi;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_inicio_labores = fecha_inicio_labores;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    
    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_inicio_labores() {
        return fecha_inicio_labores;
    }

    public void setFecha_inicio_labores(String fecha_inicio_labores) {
        this.fecha_inicio_labores = fecha_inicio_labores;
    }
    
    @Override
    public int agregar(){
        try{
            PreparedStatement parametro;
            c=new Conexion();
            c.abrir_conexion();
            String query="INSERT INTO db_empresa.empleados(nombres,apellidos,direccion,telefono,DPI,genero,fecha_nacimiento,idPuesto,fecha_inicio_labores,fechaingreso) VALUES(?,?,?,?,?,?,?,?,?,?);";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getDpi());
            parametro.setBoolean(6,getGenero());
            parametro.setString(7,getFecha_nacimiento());
            parametro.setInt(8,getPuesto());
            parametro.setString(9,getFecha_inicio_labores());
            parametro.setString(10,getFechaingreso());
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
            String query="UPDATE db_empresa.empleados SET nombres=?, apellidos=?, direccion=?, telefono=?, DPI=?, genero=?, fecha_nacimiento=?, idPuesto=?, fecha_inicio_labores=?, fechaingreso=? where idEmpleado=?;";
            parametro=(PreparedStatement) c.conexionDB.prepareStatement(query);
            parametro.setString(1,getNombres());
            parametro.setString(2,getApellidos());
            parametro.setString(3,getDireccion());
            parametro.setString(4,getTelefono());
            parametro.setString(5,getDpi());
            parametro.setBoolean(6,getGenero());
            parametro.setString(7,getFechaingreso());
            parametro.setInt(8,getPuesto());
            parametro.setString(9,getFecha_inicio_labores());
            parametro.setString(10,getFechaingreso());
            parametro.setInt(11,getId());
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
            String query="DELETE FROM db_empresa.empleados WHERE idEmpleado=?;";
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
