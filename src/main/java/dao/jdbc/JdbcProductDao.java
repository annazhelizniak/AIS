package dao.jdbc;

import Entity.Customer_Card;
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

    private static String ALL_PRODUCTS_FROM_STORE_WITH_CHARACTERISTICS = "SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product`) AND characteristics=?";
    private static String ALL_PRODUCTS_PRODUCER = "SELECT * FROM `product` WHERE producer=?";
    private static String ALL_PRODUCTS_FROM_CATEGORY   = "SELECT * FROM `product` WHERE category=? ORDER BY product_name";
    private static String ALL_PRODUCTS_WITH_NAME  = "SELECT * FROM `product` WHERE product_name=?";


    private static String ALL_PRODUCTS_FROM_STORE_SORTED_BY_NAME = "SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product`) ORDER BY product_name";

    private static String ALL_PRODUCTS_WITH_CHARACTERISTICS = "SELECT * FROM `product` WHERE characteristics=?";
    private static String ALL_PRODUCTS_FROM_STORE_PRODUCER = "SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product`) AND producer=?";
    private static String ALL_PRODUCTS_FROM_STORE_FROM_CATEGORY   = "SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product`) AND category=?";
    private static String ALL_PRODUCTS_IN_CHECK = "SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product` WHERE upc IN "
            + "(SELECT upc FROM `upc` WHERE check_number IN "
            + "(SELECT check_number FROM `sale`)))";

    private static String ALL_PRODUCTS_IN_CHECK_PRODUCER ="SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product` WHERE upc IN "
            + "(SELECT upc FROM `upc` WHERE check_number IN "
            + "(SELECT check_number FROM `sale`))) AND producer = ?";

    private static String ALL_PRODUCTS_IN_CHECK_FROM_CATEGORY ="SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product` WHERE upc IN "
            + "(SELECT upc FROM `upc` WHERE check_number IN "
            + "(SELECT check_number FROM `sale`))) AND category_number=?";
    private static String ALL_PRODUCTS_IN_CHECK_WITH_CHARACTERISTICS ="SELECT * FROM `product` WHERE id_product IN "
            + "(SELECT id_product FROM `store_product` WHERE upc IN "
            + "(SELECT upc FROM `upc` WHERE check_number IN "
            + "(SELECT check_number FROM `sale`))) AND characteristics=?";

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
    public List<Product> aLL_PRODUCTS_WITH_NAME(String name){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_WITH_NAME)) {
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + name, e);
            throw new ServerException(e);
        }
        return customerCards;

    }

    @Override
    public List<Product> aLL_PRODUCTS_PRODUCER(String producer){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_PRODUCER)) {
            query.setString(1, producer);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + producer, e);
            throw new ServerException(e);
        }
        return customerCards;

    }
    @Override
    public List<Product> aLL_PRODUCTS_FROM_CATEGORY(String cat){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_FROM_CATEGORY)) {
            query.setString(1, cat);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + cat, e);
            throw new ServerException(e);
        }
        return customerCards;

    }

    @Override
    public List<Product> aLL_PRODUCTS_WITH_CHARACTERISTICS(String characteristics){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_WITH_CHARACTERISTICS)) {
            query.setString(1, characteristics);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + characteristics, e);
            throw new ServerException(e);
        }
        return customerCards;

    }






    @Override
    public List<Product> aLL_PRODUCTS_FROM_STORE_FROM_PRODUCER(String producer){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_FROM_STORE_PRODUCER)) {
            query.setString(1, producer);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + producer, e);
            throw new ServerException(e);
        }
        return customerCards;

    }


    @Override
    public List<Product> aLL_PRODUCTS_FROM_STORE_FROM_CATEGORY(String cat){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_FROM_STORE_FROM_CATEGORY)) {
            query.setString(1, cat);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + cat, e);
            throw new ServerException(e);
        }
        return customerCards;

    }
    @Override
    public List<Product> aLL_PRODUCTS_FROM_STORE_WITH_CHARACTERISTICS(String characteristics){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_FROM_STORE_WITH_CHARACTERISTICS)) {
            query.setString(1, characteristics);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + characteristics, e);
            throw new ServerException(e);
        }
        return customerCards;

    }

    @Override
    public List<Product> aLL_PRODUCTS_FROM_STORE_SORTED_BY_NAME(){
        List<Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_FROM_STORE_SORTED_BY_NAME)) {
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
    public List<Product> aLL_PRODUCTS_IN_CHECK_WITH_CHARACTERISTICS(String characteristics) {
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_IN_CHECK_WITH_CHARACTERISTICS)) {
            query.setString(1, characteristics);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + characteristics, e);
            throw new ServerException(e);
        }
        return customerCards;

    }
    @Override
    public List<Product> aLL_PRODUCTS_IN_CHECK(){
        List<Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_IN_CHECK)) {
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
    public List<Product> aLL_PRODUCTS_IN_CHECK_PRODUCER(String producer){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_IN_CHECK_PRODUCER)) {
            query.setString(1, producer);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + producer, e);
            throw new ServerException(e);
        }
        return customerCards;

    }

    @Override
    public List<Product> aLL_PRODUCTS_IN_CHECK_FROM_CATEGORY(String cat){
        List<Product> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(ALL_PRODUCTS_IN_CHECK_FROM_CATEGORY)) {
            query.setString(1, cat);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + cat, e);
            throw new ServerException(e);
        }
        return customerCards;

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
