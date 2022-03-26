package io.muzoo.ssc.webapp.config;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    public static ConfigProperties load(){
        String configFilename = "config.properties";
        try(FileInputStream fin = new FileInputStream(configFilename)){
            Properties prop = new Properties();
            prop.load(fin);
            String driverClassName = prop.getProperty("database.driverClassName");
            String connectionUrl = prop.getProperty("database.connectionUrl");
            String username = prop.getProperty("database.username");
            String password = prop.getProperty("database.password");

            return new ConfigProperties.ConfigPropertiesBuilder()
                    .databaseDriverClassName(driverClassName)
                    .databaseConnectionUrl(connectionUrl)
                    .databaseUsername(username)
                    .databasePassword(password)
                    .build();
        }
        catch (IOException e){
            System.out.println("Exception: " + e);
            return null;
        }
    }
}
