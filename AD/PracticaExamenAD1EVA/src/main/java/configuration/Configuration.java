package configuration;

import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
@Data
public class Configuration {

    private Path pathFactionsXML;
    private String dbUrl;
    private String userName;
    private String password;
    private String dbDriver;
    @Inject
    public Configuration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("properties"));
            this.pathFactionsXML = Paths.get(p.getProperty("pathFactionsXML"));
            this.dbUrl = p.getProperty("dbUrl");
            this.userName = p.getProperty("userName");
            this.password = p.getProperty("password");
            this.dbDriver = p.getProperty("dbDriver");
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
