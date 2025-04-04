/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Rodrigo
 */
public class EmpleadoDTO {
    
    private int idEmpleado;
    private String nombre; 
    private String puesto;
    private double salario;
    private int rol_id;
    private String password_hash;
    private String correo;

    public EmpleadoDTO(int idEmpleado, String nombre, String puesto, double salario, int rol_id, String password_hash, String correo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.rol_id = rol_id;
        this.password_hash = password_hash;
        this.correo = correo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public int getRol_id() {
        return rol_id;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getCorreo() {
        return correo;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
