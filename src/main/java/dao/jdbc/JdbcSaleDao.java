package dao.jdbc;

import Entity.Sale;
import dao.SaleDao;

import Entity.Customer_Card;
import Entity.Role;
import dao.CategoryDao;
import dao.Customer_CardDao;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSaleDao implements SaleDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCustomer_CardDao.class);

    private static String GET_ALL = "SELECT * FROM `sale`";
    private static String GET_BY_ID = "SELECT * FROM `sale` WHERE upc=? AND check_number=?";
    private static String CREATE = "INSERT INTO `sale`"
            + " (product_number, selling_price) VALUES (?, ?)";
    private static String UPDATE = "UPDATE `sale`"
            + " SET product_number=?, selling_price=?" + " WHERE upc=?  AND check_number=?";
    private static String DELETE = "DELETE FROM `sale` WHERE upc=? AND check_number=?";

    // table columns names
    private static String UPC = "upc";
    private static String CHECK_NUMBER = "check_number";
    private static String PRODUCT_NUMBER = "product_number";
    private static String SELLING_PRICE = "selling_price";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcSaleDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public JdbcSaleDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }
    @Override
    public List<Sale> getAll() {
        List<Sale> sales = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                sales.add(extractSaleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return sales;
    }

    @Override
    public Optional<Sale> getById(String id) {
        Optional<Sale> sale = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                sale = Optional.of(extractSaleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcSaleDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return sale;
    }



    @Override
    public void create(Sale sale) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setInt(1, sale.getProduct_number());
            query.setBigDecimal(2, sale.getSelling_price());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                sale.setUpc(keys.getString(1));
                sale.setCheck_number(keys.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Sale sale) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {

            query.setInt(1, sale.getProduct_number());
            query.setBigDecimal(2, sale.getSelling_price());
            query.setString(3, sale.getUpc());
            query.setString(4, sale.getCheck_number());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao update SQL exception: " + sale.getUpc() +" "+ sale.getCheck_number(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }



    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static Sale extractSaleFromResultSet(ResultSet resultSet) throws SQLException {

        return new Sale.Builder().setUpc(resultSet.getString(UPC)).setCheck_number(resultSet.getString(CHECK_NUMBER))
                .setProduct_number(resultSet.getInt(PRODUCT_NUMBER)).setSelling_price(resultSet.getBigDecimal(SELLING_PRICE))


                .build();
    }



}

