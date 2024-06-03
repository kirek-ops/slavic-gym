package MMM.demo.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UuidGenerator {
    public static Integer generateUniqueID() throws Exception {
        Integer id = -1;

        String url = "jdbc:postgresql://localhost:5432/slavic_gym";
        String user = "postgres";
        String password = "abcd1234";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nextval('id_sequence')");
        
        if (rs.next()) {
            id = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return id;
    }
}