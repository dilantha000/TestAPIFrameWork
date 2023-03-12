package step.definitions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

    public static String readDataFromPropertiesFile(String propName) {

        try (InputStream input = ReadDataFromPropertiesFile.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            return prop.getProperty(propName);

            //get the property value and print it out

        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
