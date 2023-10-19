/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cana0
 */
public class Usuario {
    private String usuario;
    private int idEmpleado;
    private int[] idPermiso;
    private Conexion c;

    public Usuario() {
    }

    public Usuario(String usuario, int idEmpleado, int[] idPermiso) {
        this.usuario = usuario;
        this.idEmpleado = idEmpleado;
        this.idPermiso = idPermiso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int[] getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int[] idPermiso) {
        this.idPermiso = idPermiso;
    }
    public boolean isValid(String user, String pass){
        try{
            c= new Conexion();
            c.abrir_conexion();
            ResultSet res;
            String query="Select password from db_empresa.usuarios_web where usuario='"+user+"';";
            res=c.conexionDB.createStatement().executeQuery(query);
            res.next();
            return res.getString("password").equals(pass);
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        return false;
        }
        finally{
            c.cerrar_conexion();
        }
    }
    public String getNombre(String user){
        if(idValido(user)){
    try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="Select CONCAT(e.nombres,' ',e.apellidos) as nombre from db_empresa.empleados as e where e.idEmpleado = (SELECT idEmpleado from db_empresa.usuarios_web where db_empresa.usuarios_web.usuario='"+user+"´);";
        res=c.conexionDB.createStatement().executeQuery(query);
        res.next();
        return res.getString("nombre");
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        return usuario;
        }
        finally{
            c.cerrar_conexion();
        }
        }else return usuario;
    }
    public boolean idValido(String user){
        try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="SELECT idEmpleado from db_empresa.usuarios_web where db_empresa.usuarios_web.usuario='"+user+"';";
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
    public void cargarPermisos(){
        
    }
    public void cargarIdEmpleado(){
        
    }
}
