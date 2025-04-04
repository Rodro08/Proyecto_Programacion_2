package DAO;

import Conexion.Conexion;
import DTO.HabitacionesDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionesDAO {

    
    public List<HabitacionesDTO> obtenerHabitaciones() {
        List<HabitacionesDTO> habitaciones = new ArrayList<>();
        
        
        String query = "SELECT idHabitacion, tipo, precio, capacidad FROM habitaciones";
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query); 
             ResultSet rs = stmt.executeQuery()) { 

            
            while (rs.next()) {
                int idHabitacion = rs.getInt("idHabitacion");
                String tipo = rs.getString("tipo");
                double precio = rs.getDouble("precio");
                int capacidad = rs.getInt("capacidad");

                
                HabitacionesDTO habitacion = new HabitacionesDTO(idHabitacion, tipo, precio, capacidad);
                
                
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
       
        return habitaciones;
    }
    public boolean agregarHabitacion(HabitacionesDTO habitacion) {
        String query = "INSERT INTO habitaciones (tipo, precio, capacidad) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setInt(3, habitacion.getCapacidad());

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean editarHabitacion(HabitacionesDTO habitacion) {
        String query = "UPDATE habitaciones SET tipo = ?, precio = ?, capacidad = ? WHERE idHabitacion = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setInt(3, habitacion.getCapacidad());
            stmt.setInt(4, habitacion.getIdHabitacion());

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarHabitacion(int idHabitacion) {
        String query = "DELETE FROM habitaciones WHERE idHabitacion = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idHabitacion);

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

