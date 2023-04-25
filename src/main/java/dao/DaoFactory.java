package dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.exception.ServerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;

    public abstract DaoConnection getConnection();

    // class level dao
    public abstract UserDao createUserDao();
    // business level dao
    public abstract UserDao createUserDao(DaoConnection connection);

    public abstract CategoryDao createCategoryDao();
    public abstract CategoryDao createCategoryDao(DaoConnection connection);

    public abstract DishDao createDishDao();
    public abstract DishDao createDishDao(DaoConnection connection);

    public abstract OrderDao createOrderDao();
    public abstract OrderDao createOrderDao(DaoConnection connection);
    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                LOGGER.error("Can't load inputStream db config file to properties object", e);
                throw new ServerException(e);
            }
        }
        return daoFactory;
    }
}
