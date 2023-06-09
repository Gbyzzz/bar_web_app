package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class V2__Images_Adding extends BaseJavaMigration {

    private static final int FIRST_IMAGE_NAME = 1;
    private static final int LAST_IMAGE_NAME = 20;


    public void migrate(Context context) {
        FileInputStream fileInputStream = null;
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO images (name, filename, content_type, size, bytes) VALUES (?, ?, ?, ?, ?);")) {
            for(int i = FIRST_IMAGE_NAME; i<=LAST_IMAGE_NAME; i++){
                String filename = i + ".jpg";
                fileInputStream = new FileInputStream(V2__Images_Adding.class.getClassLoader().getResource("images").getPath() +"/" + filename);
                byte[] arr = fileInputStream.readAllBytes();
                int size = fileInputStream.available();
                statement.setString(1, "file");
                statement.setString(2, filename);
                statement.setString(3, "image/jpeg");
                statement.setInt(4, size);
                statement.setBytes(5, arr);
                statement.executeUpdate();


            }
//            try (ResultSet rows = statement.executeQuery("SELECT id FROM person ORDER BY id")) {
//                while (rows.next()) {
//                    int id = rows.getInt(1);
//                    String anonymizedName = "Anonymous" + id;
//                    try (Statement update = context.getConnection().createStatement()) {
//                        update.execute("UPDATE person SET name='" + anonymizedName + "' WHERE id=" + id);
//                    }
//                }
//            }
        } catch (SQLException | IOException e) {
        e.printStackTrace();
    } finally {
        // Close the resources
        try {
            if (fileInputStream != null) fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}