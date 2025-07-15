package com;
import java.sql.*;
import com.DBUtil;
public class feedback extends Exception {
    String username1;
    String FEmail;
    String Subject;
    String Message;

    public feedback(String username1, String FEmail, String Subject, String Message ) {
        this.username1 = username1;
        this.FEmail = FEmail;
        this.Subject = Subject;
        this.Message = Message;
    }
    public boolean check() {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into feedback values(?,?,?,?);");
            ps.setString(1, FEmail);
            ps.setString(2, username1);
            ps.setString(3, Subject);
            ps.setString(4, Message);
            int x = ps.executeUpdate();
            System.out.println("FeedBack Submitted");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}