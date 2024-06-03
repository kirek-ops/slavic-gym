package MMM.demo.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class UuidGenerator {
    private static final Logger log = LoggerFactory.getLogger(UuidGenerator.class);

    public static Integer generateUniqueID() throws Exception {
        Integer id = -1;

        Properties properties = loadProperties();

        // Retrieve user and password from properties
        String user = properties.getProperty("USER");
        String password = properties.getProperty("PASSWORD");

        String url = "jdbc:postgresql://localhost:5432/slavic_gym";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nextval('id_sequence')");
        
        if (rs.next()) {
            id = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return id;
    }

    private static Properties loadProperties() throws IOException {
        log.info("Loading properties");
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("config.properties");

        try (InputStream input = resource.getInputStream()) {
            properties.load(input);
        } catch (IOException e) {
            log.warn("Error loading config.properties file", e);
            throw e;
        }

        log.info("Properties loaded");
        log.info("properties: " + properties.toString());
        return properties;
    }
}