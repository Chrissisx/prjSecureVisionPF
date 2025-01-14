// package DataAccess;

//     import java.sql.Connection;
//     import java.sql.DriverManager;
//     import java.sql.SQLException;
    
//     public abstract class SQLiteDataHelper {
//         private static String DBPathConnection = "jdbc:sqlite:DataBase//Secure.sqlite"; 
//         private static Connection conn = null;
//         // protected SQLiteDataHelper(){}
        
//         protected static synchronized Connection openConnection() throws Exception{
//             try {
//                 if(conn == null)
//                     conn = DriverManager.getConnection(DBPathConnection);
//             } catch (SQLException e) {
//                  throw e;   //new Exception(e,"SQLiteDataHelper","Fallo la coneccion a la base de datos");
//             } 
//             return conn;
//         }
    
//         protected static void closeConnection() throws Exception{
//             try {
//                 if (conn != null)
//                     conn.close();
//             } catch (Exception e) {
//                 throw e;    //new Exception(e,"SQLiteDataHelper", "Fallo la conección con la base de datos");
//             }
//         }
//     }
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {
    private static final String DBPathConnection = "jdbc:sqlite:DataBase/Secure.sqlite";
    
    protected Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DBPathConnection);
    }
    
    protected void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}

