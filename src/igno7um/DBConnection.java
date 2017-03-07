package igno7um;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

// Connects to database and runs specified query and returns value
public class DBConnection {

    private String DBName = "jdbc:db2://hma2qdb1:60008/qdb2";
    private String DBUser = "q";
    private String DBPass = "q";
    private String DBQuery = "select SUM(lot_size) as \"COUNT\" from qdb.gal212tbx where process_location = 'LL' and send_status = 0";

    // Constructor to call if above values need to change
    DBConnection(String name, String user, String pass){
        DBName = name;
        DBUser = user;
        DBPass = pass;
    }

    public void setDBQuery(String s){
        DBQuery = s;
    }

    //returns result or -x for failure.
    // -90001 Driver load exception
    // -90002 SQL Exception
    // -90003 DB Connection failed
    // -1 No results found (query ran fine)
    public int runQuery(){
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassPath for driver exception");
            e.printStackTrace();
            return -90001;
        }
        System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean found = false;
        try {
            conn = DriverManager.getConnection(DBName, DBUser, DBPass);
            if (conn != null) {
                System.out.println("DB2 Database Connected");
            } else {
                System.out.println("DB2 connection Failed");
                return -90003;
            }
            pstmt = conn.prepareStatement(DBQuery);
            rset = pstmt.executeQuery();
            if (rset != null) {
                while (rset.next()) {
                    found = true;
                    System.out.println("Count " + rset.getString("COUNT"));
                    int x = rset.getInt("COUNT");
                    return x;
                }
            }
            if (found == false) {
                System.out.println("No Results");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
            return -90002;
        }

        return -2;
    }

}
