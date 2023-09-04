package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class V2__Images_Adding extends BaseJavaMigration {

    private static final int FIRST_IMAGE_NAME = 1;
    private static final int LAST_IMAGE_NAME = 20;


    public void migrate(Context context) {
        try (PreparedStatement statement = context.getConnection()
                .prepareStatement("INSERT INTO images (name, filename, content_type, size, bytes)" +
                        " VALUES (?, ?, ?, ?, ?);")) {
            for (int i = FIRST_IMAGE_NAME; i <= LAST_IMAGE_NAME; i++) {
                String filename = i + ".jpg";

                InputStream inputStream = V2__Images_Adding.class.getClassLoader().getResourceAsStream("images/" + filename);

                if (inputStream != null) {
                    byte[] arr = inputStream.readAllBytes();
                    int size = arr.length;

                    statement.setString(1, "file");
                    statement.setString(2, filename);
                    statement.setString(3, "image/jpeg");
                    statement.setInt(4, size);
                    statement.setBytes(5, arr);
                    statement.executeUpdate();

                    inputStream.close();
                } else {
                    // Handle the case where the resource was not found
                    System.err.println("Resource not found: images/" + filename);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}