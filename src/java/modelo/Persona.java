/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author cana0
 */ 
abstract class Persona {
    private int id;
    private boolean genero;
    private String nombres, apellidos, telefono, fechaingreso;

    public Persona() {
    }

    public Persona(int id,boolean genero, String nombres, String apellidos,  String telefono, String fechaingreso) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.genero=genero;
        this.fechaingreso = fechaingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }
    
    public String getStrGenero(String gender){
        if(gender.equalsIgnoreCase("1")){
            return "M";
        }else{
            return "F";
        }
    }
    
    public boolean getBoolGenero(String gender){
        if(gender.equalsIgnoreCase("F")){
            return false;
        }else{
            return true;
        }
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    
    public int agregar(){
    return 3;
    }
    public int modificar(){
    return 3;
    }
    public int eliminar(){
    return 3;
    }
    public DefaultTableModel mostrar(){
    return null;
    }
    
}
