package net.usachev.xmlwork;

import net.usachev.xmlwork.storage.SqlStorageImpl;
import net.usachev.xmlwork.storage.Storage;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    private Storage storage;

    private Properties appProps;

    public static AppConfig get() {
        return INSTANCE;
    }

    public Storage getStorage() {
        return storage;
    }

    private AppConfig() {
        try (InputStream dbProp = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            appProps = new Properties();
            appProps.load(dbProp);
            storage = new SqlStorageImpl(
                    appProps.getProperty("db.url"),
                    appProps.getProperty("db.user"),
                    appProps.getProperty("db.password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
