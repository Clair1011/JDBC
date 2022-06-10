import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static Connection c;
    public static void main(String[] args)throws SQLException, ClassNotFoundException {
        initalizeDB();

        String name = JOptionPane.showInputDialog("Find a video by name: ");
        String sql_statement = "select * from video where videoName = ? ;";
        PreparedStatement pps = c.prepareStatement(sql_statement); // 避免 SQL injection
        pps.setString(1, name);
        ResultSet rs = pps.executeQuery();

        // videoDB name : Hally Potter / Avengers

        if (rs.next()) {
            int title = Integer.parseInt((rs.getString("videoId")));
            String vname = rs.getString("videoName");
            int price = Integer.parseInt(rs.getString("price")) ;
            JOptionPane.showMessageDialog(null, new Video(title, vname, price));
        }
        else {
            JOptionPane.showMessageDialog(null, "Video not found...");
        }

        closeDB();
    }

    private static void initalizeDB() throws SQLException {
        c = DriverManager.getConnection("jdbc:mydb_location", "db_username","db_password"); 
        if ( c != null) {
            System.out.println("Connecting to the database!");
        } else {
            System.out.println("Cannot connect to the database.");
        }

    }
    private static void closeDB() throws SQLException {
        c.close();
    }
}
