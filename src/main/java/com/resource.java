package com;
import java.io.File;
import java.sql.*;
import com.*;

public class resource {
    public static void main(String[] args) {

        String query = "insert into subhash.resource values(?,?)";
        //MySQL connection creation
        Connection conn = DBUtil.getConnection();
        // Specify the directory path in which the files to be added are stored
        String directoryPath = "D:\\javawebpage\\BookWormsBurrow\\src\\main\\webapp\\books" ;

        // Create a File object for the specified directory
        File directory = new File(directoryPath);

        // Check if the specified path is a directory
        try{
            if (directory.isDirectory()) {
                // Get a list of all files in the directory
                File[] files = directory.listFiles();

                // Iterate through the files and display their names and paths
                if (files != null) {
                    for (File file : files) {
                        try {
                            PreparedStatement ps = conn.prepareStatement(query);
                            ps.setString(1, file.getName());
                            String i = "DownLoadFiles/".concat(file.getName());
                            ps.setString(2, i);
                            int x = ps.executeUpdate();
                            System.out.println("inserted    :"+file.getName());
                        }
                        catch(SQLIntegrityConstraintViolationException e){
                            System.out.println(file.getName() + ":  File Already exist.");
                        }
                    }
                    store s = new store(directoryPath);
                } else {
                    System.out.println("The directory is empty.");
                }
            } else {
                System.out.println("The specified path is not a directory.");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}


