package connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants {

    private static final Properties prop = initProps();

    private static Properties initProps() {

        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/data.properties")) {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public final static String AUTHORITY = "https://login.microsoftonline.com/common/";
    public final static String CLIENT_ID = prop.getProperty("client.id");
    public final static String USER_NAME = prop.getProperty("user.name");
    public final static String USER_PASS = prop.getProperty("user.pass");

}
