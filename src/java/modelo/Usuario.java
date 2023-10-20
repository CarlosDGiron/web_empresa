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
    private int idUsuario, idEmpleado;
    private Conexion c;

    public Usuario() {
        idEmpleado=0;
    }

    public Usuario(String usuario, int idUsuario, int idEmpleado) {
        this.usuario = usuario;
        this.idUsuario=idUsuario;
        this.idEmpleado = idEmpleado;
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
   
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public boolean esValido(String user, String pass){
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
        if(esEmpleado(user)){
    try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="Select CONCAT(e.nombres,' ',e.apellidos) as nombre from db_empresa.empleados as e where e.idEmpleado = (SELECT idEmpleado from db_empresa.usuarios_web where db_empresa.usuarios_web.usuario='"+user+"');";
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
    
    public boolean esEmpleado (String user){
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
    public void cargarIds(){
        try{
        c= new Conexion();
        c.abrir_conexion();
        ResultSet res;
        String query="SELECT idUsuario, idEmpleado from db_empresa.usuarios_web where db_empresa.usuarios_web.usuario='"+usuario+"';";
        res=c.conexionDB.createStatement().executeQuery(query);
        res.next();
        idEmpleado= res.getInt("idEmpleado");
        idUsuario= res.getInt("idUsuario");
        }catch(SQLException ex){
        System.out.println(ex.getMessage());
        }
        finally{
            c.cerrar_conexion();
        }
    }
    
    public boolean tienePermisoId(int idUsuario,int idPermiso){
        //vaslidar en DB si el usuario tiene permiso para un id de pagina
        try{
            c= new Conexion();
            c.abrir_conexion();
            ResultSet res;
            String query="SELECT idPermiso FROM db_empresa.usuarios_permisos WHERE idUsuario="+idUsuario+" AND idPermiso="+idPermiso+";";
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
}
