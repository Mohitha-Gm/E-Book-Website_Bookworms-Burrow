package com;
import com.DBUtil;
import java.sql.*;
interface search{
    public String gett();
    public String gett(String key);
}
public class find implements search {
    String search;

    public find(String search) {

        this.search = search;
    }
    public String gett(){
        search = search.replaceAll("\\s", "");
        return search;
    }

    public String gett(String key){
        try {
            ResultSet rs;
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("select dir from resource where FileName like concat('%',?,'%')");
            ps.setString(1, key);
            rs = ps.executeQuery();

            if (rs.next()) { // Move the cursor to the first row
                return rs.getString("dir");
            } else {
                return "No result found for " + key;
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
