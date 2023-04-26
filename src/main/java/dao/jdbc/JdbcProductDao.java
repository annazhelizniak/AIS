package dao.jdbc;

import Entity.Product;
import dao.ProductDao;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductDao implements ProductDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcCheckDao.class);

    private static String GET_ALL = "SELECT * FROM `product` ORDER BY product_name";
    private static String GET_BY_ID = "SELECT * FROM `product` WHERE id_product=?";
    private static String CREATE = "INSERT INTO `product`"
            + " (category_number, product_name, characteristics) VALUES ( ?, ?, ?)";
    private static String UPDATE = "UPDATE `product`"
            + " SET category_number=?, product_name=? , characteristics=?" + " WHERE id_product=? ";
    private static String DELETE = "DELETE FROM `product` WHERE id_product=?";




    // table columns names
    private static String ID_PRODUCT = "id_product";
    private static String CATEGORY_NUMBER = "category_number";
    private static String PRODUCT_NAME = "product_name";

    private static String PRODUCER = "producer";
    private static String CHARACTERISTICS = "characteristics";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcProductDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcProductDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> getAll() {
        List<Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public Optional<Product> getById(Long id) {
        Optional<Product> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Product product) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, product.getCategory_number());
            query.setString(2, product.getProduct_name());
            query.setString(3, product.getProducer());
            query.setString(4, product.getCharacteristics());

            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                product.setId_product(keys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {

            query.setLong(1, product.getCategory_number());
            query.setString(2, product.getProduct_name());
            query.setString(3, product.getProducer());
            query.setString(4, product.getCharacteristics());

            query.setLong(5, product.getId_product());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao update SQL exception: " + product.getId_product(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
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

    protected static Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {

        return new Product.Builder().
                setId_product(resultSet.getLong(ID_PRODUCT)).
                setCategory_number(resultSet.getLong(CATEGORY_NUMBER))
                .setProduct_name(resultSet.getString(PRODUCT_NAME))
                .setProducer(resultSet.getString(PRODUCER))
                .setCharacteristics(resultSet.getString(CHARACTERISTICS))

                .build();
    }

    protected static Product extractProductGeneralInfoFromResultSet(ResultSet resultSet) throws SQLException {

        return new Product.Builder(). setId_product(resultSet.getLong(ID_PRODUCT))
                .setProduct_name(resultSet.getString(PRODUCT_NAME))
                .build();
    }


}
