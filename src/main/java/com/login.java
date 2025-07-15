package com;
import java.sql.*;
import com.DBUtil;
public class login extends Exception{
    String lEmail;
    String lPassword;
    public login(String lEmail, String lPassword) {
        this.lEmail = lEmail;
        this.lPassword = lPassword;
    }
    final String query = "select * from subhash.login where Email = ?";
    public boolean check() {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lEmail);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println();
                return resultSet.getString(3).equalsIgnoreCase(lPassword);
                }
            else {
                System.out.println("Email does not exist in the database.");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
