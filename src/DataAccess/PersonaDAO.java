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

import DataAccess.DTO.PersonaDTO;
import Framework.PatException;


public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO> {

    @Override
    public boolean create(PersonaDTO entity) throws Exception {
            String query = " INSERT INTO Persona (IdPersona ,Nombre ,Apellido ) VALUES (?,?,?)";
            try {
                Connection        conn  = openConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, 1);   //
                pstmt.setString(2, entity.getNombre());
                pstmt.setString(3, entity.getApellido());
                pstmt.executeUpdate();
                return true;
            } 
            catch (SQLException e) {
                throw new PatException(e.getMessage(), getClass().getName(), "create()");
            }
        }

    @Override
    public List<PersonaDTO> readAll() throws Exception {
        List <PersonaDTO> lst = new ArrayList<>();
        String query =  " SELECT IdPersona    "
                       +" ,IdRegistro         "
                       +" ,Nombre             "
                       +" ,Apellido           "
                       +" ,Estado             "
                       +" ,FechaCreacion      "
                       +" ,FechaModifica      "
                       +" FROM    Catalogo    "
                       +" WHERE   Estado ='A' ";  
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet  rs   = stmt.executeQuery(query);    // ejecutar la
            while (rs.next()) {
                PersonaDTO s = new PersonaDTO( 
                                         rs.getInt(1)     // IdPersona
                                        ,rs.getInt(2)     // IdRegistro             
                                        ,rs.getString(3)  // Nombre         
                                        ,rs.getString(4)  // Descripcion      
                                        ,rs.getString(5)  // Estado
                                        ,rs.getString(6)  // FechaCreacion
                                        ,rs.getString(7));// FechaModifica
                lst.add(s);
            }
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return lst; 
    }

    @Override
    public boolean update(PersonaDTO entity) throws Exception {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            String query = "UPDATE Persona SET Nombre = ?, Apellido = ?, FechaModifica = ? WHERE IdPersona = ?";
            try {
                Connection          conn = openConnection();
                PreparedStatement pstmt  = conn.prepareStatement(query);
                pstmt.setString(1, entity.getNombre());
                pstmt.setString(2, entity.getApellido());
                pstmt.setString(3, dtf.format(now).toString());
                pstmt.setInt(4, entity.getIdPersona());
                pstmt.executeUpdate();
                return true;
            } 
            catch (SQLException e) {
                throw new PatException(e.getMessage(), getClass().getName(), "update()");
            }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = " UPDATE Persona SET Estado = ? WHERE IdPersona = ?";
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
    public PersonaDTO readBy(Integer id) throws Exception {
        PersonaDTO oS = new PersonaDTO();
        String query =" SELECT IdPersona  " 
                     +" ,Nombre        " 
                     +" ,Estado        " 
                     +" ,FechaCrea     " 
                     +" ,FechaModifica "
                     +" FROM    Persona   "
                     +" WHERE   Estado ='A' AND IdPersona =   " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                oS = new PersonaDTO(rs.getInt(1)         // IdPersona
                                 ,rs.getInt(2)   
                                 ,rs.getString(3)        // Nombre    
                                 ,rs.getString(4)        // Apellido           
                                 ,rs.getString(5)        // Estado         
                                 ,rs.getString(6)        // FechaCrea      
                                 ,rs.getString(7));      // FechaModifica
            }
        } 
        catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return oS;
    }
}
