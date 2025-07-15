package com;
import java.sql.*;
import com.DBUtil;
public class signup extends Exception {
    String UserName;
    String Email;
    String Password;
    String User;

    public signup(String UserName, String Email, String Password, String User) {
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.User = User;
    }
    final String query = "insert into login values(?,?,?,?);";
    public boolean check() {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, UserName);
            ps.setString(2, Password);
            ps.setString(3, Email);
            ps.setString(4, User);
            int x = ps.executeUpdate();
            System.out.println("Inserted");
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}