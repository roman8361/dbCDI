package ru.kravchenko.se.service;

import com.sun.istack.internal.NotNull;
import ru.kravchenko.se.App;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Roman Kravchenko
 */

@ApplicationScoped
public class PropertyService {

    @NotNull
    private final Properties properties = new Properties();

    public PropertyService() {
        try (InputStream resourceStream = App.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PropertyService(
            @NotNull final Class clazz,
            @NotNull final String filename) {
        try (InputStream resourceStream = clazz.getClassLoader().getResourceAsStream(filename)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJdbcPassword() {
        return properties.getProperty("password");
    }

    public String getJdbcUser() {
        return properties.getProperty("user");
    }

    public String getJdbcURL() {
        return properties.getProperty("url");
    }

    public String getJdbcDriver() {
        return properties.getProperty("driver");
    }

    public String getDialect() {
        return properties.getProperty("dialect");
    }

    public String getShowSQL() {
        return properties.getProperty("showSQL");
    }

    public String getHBM2DDL_AUTO() {
        return properties.getProperty("HBM2DDL_AUTO");
    }

    public String getSalt() {
        return properties.getProperty("salt");
    }

    public String getCycle() {
        return properties.getProperty("cycle");
    }

}
