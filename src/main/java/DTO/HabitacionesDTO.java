/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Rodrigo
 */
public class HabitacionesDTO {
    private int idHabitacion;
    private String tipo;
    private double precio;
    private int capacidad;

    // Constructor
    public HabitacionesDTO(int idHabitacion, String tipo, double precio, int capacidad) {
        this.idHabitacion = idHabitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
