package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.GrabacionesDTO;
import DataAccess.DTO.PersonaDTO;
import Framework.PatException;

public class GrabacionesDAO extends SQLiteDataHelper implements IDAO<GrabacionesDTO>{

    @Override
    public boolean create(GrabacionesDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " INSERT INTO Grabaciones (IdGrabaciones ,Grabacion ,fechaCreacion ) VALUES (?,?,?)";
            try {
                Connection        conn  = openConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, 1);   //
                pstmt.setString(2, entity.getGrabacion());
                pstmt.setString(3, dtf.format(now).toString());
                pstmt.executeUpdate();
                return true;
            } 
            catch (SQLException e) {
                throw new PatException(e.getMessage(), getClass().getName(), "create()");
            }
        }

    @Override
    public List<GrabacionesDTO> readAll() throws Exception {
        List <GrabacionesDTO> lst = new ArrayList<>();
        String query =  " SELECT IdGrabaciones   "
                       +" ,Grabacion             "
                       +" ,Estado                "
                       +" ,FechaCreacion         "
                       +" FROM    Grabaciones    "
                       +" WHERE   Estado ='A'    ";  
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet  rs   = stmt.executeQuery(query);    // ejecutar la
            while (rs.next()) {
                GrabacionesDTO s = new GrabacionesDTO( 
                                         rs.getInt(1)     
                                        ,rs.getString(2) 
                                        ,rs.getString(3)        
                                        ,rs.getString(4) 
                                        ,rs.getString(5));
                lst.add(s);
            }
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst; 
    }

    @Override
    public boolean update(GrabacionesDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Grabaciones SET Grabacion = ?, FechaCreacion = ? WHERE IdGrabaciones = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getGrabacion());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdGrabaciones());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
}

    @Override
    public boolean delete(int id) throws Exception {
        String query = " UPDATE Grabaciones SET Estado = ? WHERE IdGrabaciones = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    @Override
    public GrabacionesDTO readBy(Integer id) throws Exception {
        GrabacionesDTO oS = new GrabacionesDTO();
        String query =" SELECT IdGrabaciones  " 
                     +" ,Grabacion        " 
                     +" ,Estado        " 
                     +" ,FechaCrea     " 
                     +" FROM    Persona   "
                     +" WHERE   Estado ='A' AND IdPersona =   " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                oS = new GrabacionesDTO(rs.getInt(1)         // IdPersona
                                 ,rs.getString(2)        // Nombre    
                                 ,rs.getString(3)        // Apellido           
                                 ,rs.getString(4)        // Estado         
                                 ,rs.getString(5));      // FechaModifica
            }
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }
}