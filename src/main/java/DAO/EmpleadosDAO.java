/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.Conexion;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
/**
 *
 * @author Rodrigo
 */
public class EmpleadosDAO {
     
    public boolean login(String correo, String password) {
        
        String query = "SELECT password_hash FROM empleados WHERE correo = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");

               
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener");
            return false; 
        }
    }

    


}
