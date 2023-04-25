package dao.jdbc;

import dao.*;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger(JdbcDaoFactory.class);

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/restaurant");

        } catch (Exception e) {
            LOGGER.error("Can't load pool connection from Initial Context", e);
            throw new ServerException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            LOGGER.error("Can't get DB connection to the data source", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CheckDao createCheckDao() {
        try {
            return new JdbcCheckDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCheckDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CheckDao createCheckDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCheckDao(sqlConnection);
    }

    @Override
    public CategoryDao createCategoryDao() {
        try {
            return new JdbcCategoryDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCategoryDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CategoryDao createCategoryDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCategoryDao(sqlConnection);
    }

    @Override
    public Customer_CardDao createCustomer_CardDao() {
        try {
            return new JdbcCustomer_CardDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCustomer_CardDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public Customer_CardDao createCustomer_CardDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCustomer_CardDao(sqlConnection);
    }

    @Override
    public EmployeeDao createEmployeeDao() {
        try {
            return new JdbcEmployeeDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbEmployeeDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public EmployeeDao createEmployeeDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcEmployeeDao(sqlConnection);
    }

    @Override
    public ProductDao createProductDao() {
        try {
            return new JdbcProductDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcProductDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public ProductDao createProductDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcProductDao(sqlConnection);
    }

    @Override
    public SaleDao createSaleDao() {
        try {
            return new JdbcSaleDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcSaleDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public SaleDao createSaleDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSaleDao(sqlConnection);
    }

    @Override
    public Store_productDao createStore_productDao() {
        try {
            return new JdbcStore_productDao(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcStore_productDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public Store_productDao createStore_productDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcStore_productDao(sqlConnection);
    }
}

