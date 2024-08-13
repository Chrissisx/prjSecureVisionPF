package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.RegistroDTO;
import Framework.PatException;

public class RegistroDAO extends SQLiteDataHelper implements IDAO<RegistroDTO> {

    @Override
    public boolean create(RegistroDTO entity) throws Exception {
        String query = "INSERT INTO Registro (Username, Password, Estado, FechaCreacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getUsername());
            pstmt.setString(2, entity.getPassword());
            pstmt.setString(3, entity.getEstado());  // Estado debe ser pasado como parámetro
            pstmt.setString(4, java.time.LocalDateTime.now().toString());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new PatException("Error al insertar datos: " + e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public List<RegistroDTO> readAll() throws Exception {
        List<RegistroDTO> lst = new ArrayList<>();
        String query = "SELECT IdRegistro, Username, Password, Estado, FechaCreacion, FechaMod FROM Registro WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                RegistroDTO s = new RegistroDTO(
                        rs.getInt("IdRegistro"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Estado"),
                        rs.getString("FechaCreacion"),
                        rs.getString("FechaMod"));
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new PatException("Error al leer datos: " + e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst;
    }

    @Override
    public boolean update(RegistroDTO entity) throws Exception {
        String query = "UPDATE Registro SET Username = ?, Password = ?, FechaMod = ? WHERE IdRegistro = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getUsername());
            pstmt.setString(2, entity.getPassword());
            pstmt.setString(3, java.time.LocalDateTime.now().toString());
            pstmt.setInt(4, entity.getIdRegistro());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new PatException("Error al actualizar datos: " + e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = "UPDATE Registro SET Estado = 'X' WHERE IdRegistro = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new PatException("Error al eliminar datos: " + e.getMessage(), getClass().getName(), "delete()");
        }
    }

    @Override
    public RegistroDTO readBy(Integer id) throws Exception {
        RegistroDTO oS = null;
        String query = "SELECT IdRegistro, Username, Password, Estado, FechaCreacion, FechaMod FROM Registro WHERE Estado = 'A' AND IdRegistro = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    oS = new RegistroDTO(
                            rs.getInt("IdRegistro"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("Estado"),
                            rs.getString("FechaCreacion"),
                            rs.getString("FechaMod"));
                }
            }
        } catch (SQLException e) {
            throw new PatException("Error al leer datos por ID: " + e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }
}

// package DataAccess;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;

// import DataAccess.DTO.PersonaDTO;
// import DataAccess.DTO.RegistroDTO;
// import Framework.PatException;

// public class RegistroDAO extends SQLiteDataHelper implements IDAO<RegistroDTO> {

//     @Override
//     public boolean create(RegistroDTO entity) throws Exception {
//          String query = " INSERT INTO Registro (IdRegistro ,Username ,Password ) VALUES (?,?,?)";
//             try {
//                 Connection        conn  = openConnection();
//                 PreparedStatement pstmt = conn.prepareStatement(query);
//                 pstmt.setInt(1, 1);   //
//                 pstmt.setString(2, entity.getUsername());
//                 pstmt.setString(3, entity.getPassword());
//                 pstmt.executeUpdate();
//                 return true;
//             } 
//             catch (SQLException e) {
//                 throw new PatException(e.getMessage(), getClass().getName(), "create()");
//             }
//     }

//     @Override
//     public List<RegistroDTO> readAll() throws Exception {
//         List <RegistroDTO> lst = new ArrayList<>();
//         String query =  " SELECT IdRegistro   "
//                        +" ,Username           "
//                        +" ,Password           "
//                        +" ,Estado             "
//                        +" ,FechaCreacion      "
//                        +" ,FechaModifica      "
//                        +" FROM    Registro    "
//                        +" WHERE   Estado ='A' ";  
//         try {
//             Connection conn = openConnection();         // conectar a DB     
//             Statement  stmt = conn.createStatement();   // CRUD : select * ...    
//             ResultSet  rs   = stmt.executeQuery(query);    // ejecutar la
//             while (rs.next()) {
//                 RegistroDTO s = new RegistroDTO( 
//                                          rs.getInt(1)     // IdRegistro            
//                                         ,rs.getString(2)  // Username         
//                                         ,rs.getString(3)  // Password     
//                                         ,rs.getString(4)  // Estado
//                                         ,rs.getString(5)  // FechaCreacion
//                                         ,rs.getString(6));// FechaModifica
//                 lst.add(s);
//             }
//         } 
//         catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
//         }
//         return lst; 
//     }
    
//     @Override
//     public boolean update(RegistroDTO entity) throws Exception {
//         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
//             LocalDateTime now = LocalDateTime.now();
//             String query = "UPDATE Registro SET Username = ?, Password = ?, FechaModifica = ? WHERE IdRegistro = ?";
//             try {
//                 Connection          conn = openConnection();
//                 PreparedStatement pstmt  = conn.prepareStatement(query);
//                 pstmt.setString(1, entity.getUsername());
//                 pstmt.setString(2, entity.getPassword());
//                 pstmt.setString(3, dtf.format(now).toString());
//                 pstmt.setInt(4, entity.getIdRegistro());
//                 pstmt.executeUpdate();
//                 return true;
//             } 
//             catch (SQLException e) {
//                 throw new PatException(e.getMessage(), getClass().getName(), "update()");
//             }
//     }

//     @Override
//     public boolean delete(int id) throws Exception {
//         String query = " UPDATE Registro SET Estado = ? WHERE IdRegistro = ?";
//         try {
//             Connection          conn = openConnection();
//             PreparedStatement  pstmt = conn.prepareStatement(query);
//             pstmt.setString(1, "X");
//             pstmt.setInt(1, id);
//             pstmt.executeUpdate();
//             return true;
//         } 
//         catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "delete()");
//         }
//     }

//     @Override
//     public RegistroDTO readBy(Integer id) throws Exception {
//         RegistroDTO oS = new RegistroDTO();
//         String query =" SELECT IdRegistro  " 
//                      +" ,Username          " 
//                      +" ,Estado            " 
//                      +" ,FechaCrea         " 
//                      +" ,FechaModifica     "
//                      +" FROM    Registro   "
//                      +" WHERE   Estado ='A' AND IdRegistro =   " + id.toString() ;
//         try {
//             Connection conn = openConnection();         // conectar a DB     
//             Statement  stmt = conn.createStatement();   // CRUD : select * ...    
//             ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
//             while (rs.next()) {
//                 oS = new RegistroDTO(rs.getInt(1)         // IdRegistro
//                                  ,rs.getString(2)        // Nombre    
//                                  ,rs.getString(3)        // Apellido           
//                                  ,rs.getString(4)        // Estado         
//                                  ,rs.getString(5)        // FechaCrea      
//                                  ,rs.getString(6));      // FechaModifica
//             }
//         } 
//         catch (SQLException e) {
//             throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
//         }
//         return oS;
//     }

// }
