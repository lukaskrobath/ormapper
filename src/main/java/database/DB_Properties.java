package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DB_Properties {
    private static final Properties PROPS = new Properties();

    static {
        Path filepath = Paths.get(System.getProperty("user.dir"), "src", "main","java","res", "database.properties");
        try {
            PROPS.load(new FileInputStream(filepath.toFile()));
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }

    public static String getProperty(String key) {
        return PROPS.getProperty(key);
    }

}
