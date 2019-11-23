package modul6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PraktikumModul6
 */
public class dbconnections {
    //deklarasi variabel
    Connection c;
    Statement script;
    
    public dbconnections(){
        try{ 
 //sebagai class reference untuk conect ke database
 Class.forName("oracle.jdbc.driver.OracleDriver");
 c = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","modul6_kel46","1234");
 script = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

 //memberikan peringatan bila bila koneksi ke database berhasil
        System.out.println("Koneksi Sukses");
        }catch(SQLException | ClassNotFoundException ex){
        System.err.print(ex);
        }
    }
}
