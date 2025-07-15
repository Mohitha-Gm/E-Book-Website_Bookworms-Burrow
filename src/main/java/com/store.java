package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class store {
    static String pat;
    public store(String pat) throws SQLException {
        this.pat = pat;
        saveBinaryFilesInDatabase();
    }
    private static void saveBinaryFilesInDatabase() throws SQLException {
        String SQL="INSERT INTO subhash.binstorefile(FileName,FileSizeInKB,FileExtension,FileContent) VALUES(?,?,?,?)";
        Path dir = Paths.get(pat);
        try {
            Stream<Path> list = Files.list(dir);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/subhash","root","Subhash@7432");
            PreparedStatement prs = connection.prepareStatement(SQL);
            List<Path> pathList = list.collect(Collectors.toList());
            System.out.println("Following files are saved in database..");
            for (Path path : pathList) {
                System.out.println(path.getFileName());
                File file = path.toFile();
                String fileName = file.getName();
                long fileLength = file.length();
                long fileLengthInKb=fileLength/1024;

                prs.setString(1, fileName);
                prs.setLong(2, fileLengthInKb);

                prs.setString(3, fileName.substring(fileName.lastIndexOf(".")+1));

                FileInputStream fis = new FileInputStream(file);
                prs.setBinaryStream(4, fis, fileLength);
                prs.addBatch();
            }
            System.out.println("----------------------------------------");
            int[] executeBatch = prs.executeBatch();
            for (int i : executeBatch) {
                System.out.println(i);
            }
        } catch (Exception e ) {
            System.out.println(e);
        }
    }
}