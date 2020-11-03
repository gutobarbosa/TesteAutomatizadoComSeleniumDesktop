package Prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Prop {
    public static Properties getProp() throws IOException{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\augus\\OneDrive\\Área de Trabalho\\config.properties\\config.properties.txt");
        props.load(file);
        return props;
    }
}
