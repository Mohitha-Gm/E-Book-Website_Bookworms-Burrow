package com;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBUtil;
public class downloadnshow{
    public static void main(String[] args) {
        try {
            downloadBinaryFilesFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void downloadBinaryFilesFromDatabase() throws SQLException {
        String SQL = "SELECT * FROM subhash.binstorefile";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("Following files are downloaded from the database..");
            while (rs.next()) {
                int fileId = rs.getInt("FileId");
                String fileName = rs.getString("FileName");
                long fileSizeInKb = rs.getLong("FileSizeInKB");
                String fileExtension = rs.getString("FileExtension");
                System.out.println("File Id:" + fileId);
                System.out.println("File Name:" + fileName);
                System.out.println("File Size In KB:" + fileSizeInKb);
                System.out.println("File Extension:" + fileExtension);

                Blob blob = rs.getBlob("FileContent");
                InputStream inputStream = blob.getBinaryStream();

                // Ensure directory exists
                Path directoryPath = Paths.get("src/main/webapp/DownLoadFiles");
                try {
                    Files.createDirectories(directoryPath);
                } catch (IOException ex) {
                    System.out.println("Failed to create directory: " + ex.getMessage());
                }

                // Write the InputStream to a file
                try (FileOutputStream outputStream = new FileOutputStream("src/main/webapp/DownLoadFiles/" + fileName)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Downloaded: " + fileName);
                } catch (IOException ex) {
                    System.out.println("Failed to write file: " + ex.getMessage());
                } finally {
                    inputStream.close(); // close InputStream after usage
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }
}
