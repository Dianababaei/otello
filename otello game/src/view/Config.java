package view;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Config extends Properties {
    private static final String DEFAULT_ADDRESS = "config.properties";
    private static final Config MAIN_CONFIG = new Config(DEFAULT_ADDRESS);

    public static Config getConfig(String name) {
        if ("main".equals(name))
            return MAIN_CONFIG;
        return MAIN_CONFIG.getProperty(Config.class, name);
    }

    private Config(String address) {
        super();
        try {
            Reader fileReader = new FileReader(address);
            this.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <E> E getProperty(Class<E> c, String propertyName) {
        if (!containsKey(propertyName))
            return null;
        return getObject(c, getProperty(propertyName));
    }
    private <E> E getObject(Class<E> c, String value) {
        E e = null;
        try {
            Constructor<E> constructor = c.getDeclaredConstructor(String.class);
            e = constructor.newInstance(value);
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
        return e;
    }
}





