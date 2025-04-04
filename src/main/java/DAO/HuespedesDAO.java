/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import DTO.HuespedesDTO;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class HuespedesDAO {
    public List<HuespedesDTO> obtenerHuespedes() {
        
        List<HuespedesDTO> huespedes = new ArrayList<>();
        
        
        String query = "SELECT idHuesped, nombre, direccion, telefono FROM huespedes";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            
            while (rs.next()) {
               
                int idHuesped = rs.getInt("idHuesped");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                
                
                HuespedesDTO huesped = new HuespedesDTO(idHuesped, nombre, direccion, telefono);
                
               
                huespedes.add(huesped);
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
        
        return huespedes;
    }
    public boolean agregarHuesped(HuespedesDTO huesped) {
        String query = "INSERT INTO huespedes (nombre, direccion, telefono) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
           
            stmt.setString(1, huesped.getNombre());
            stmt.setString(2, huesped.getDireccion());
            stmt.setString(3, huesped.getTelefono());
            
           
            int filasAfectadas = stmt.executeUpdate();
            
           if(filasAfectadas > 0){
               return true;
           } 
           else{
               return false;
           }
            
           
        } catch (SQLException e) {
            e.printStackTrace();  
            return false;
        }
    }
    public boolean actualizarHuesped(HuespedesDTO huesped) {
        String query = "UPDATE huespedes SET nombre = ?, direccion = ?, telefono = ? WHERE idHuesped = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, huesped.getNombre());
            stmt.setString(2, huesped.getDireccion());
            stmt.setString(3, huesped.getTelefono());
            stmt.setInt(4, huesped.getIdHuesped());

           
            int filasAfectadas = stmt.executeUpdate();

           
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();  
            return false;
        }
    }
    public boolean eliminarHuesped(int idHuesped) {
    String query = "DELETE FROM huespedes WHERE idHuesped = ?";
    
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        
        stmt.setInt(1, idHuesped);
        
        
        int filasAfectadas = stmt.executeUpdate();
        
       
        if (filasAfectadas > 0) {
            return true;
        } else {
            return false;
        }
        
    } catch (SQLException e) {
        e.printStackTrace();  
        return false;
    }
}
}
