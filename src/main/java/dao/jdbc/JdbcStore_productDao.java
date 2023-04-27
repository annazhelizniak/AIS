package dao.jdbc;

import Entity.Store_Product;
import dao.Store_productDao;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStore_productDao implements Store_productDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCheckDao.class);

    private static String GET_ALL = "SELECT * FROM `store_product` ORDER BY upc";
    private static String GET_BY_ID = "SELECT * FROM `store_product` WHERE upc=?";

    private static String GET_BY_ID_FOR_KASIR = "SELECT selling_price, products_number FROM `store_product` WHERE upc=?";
    private static String CREATE = "INSERT INTO `store_product`"
            + " (upc_prom, id_product, selling_price, products_number, promotional_product) VALUES (?,  ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `store_product`"
            + " SET upc_prom=?, id_product=? , selling_price=?, products_number=?, promotional_product=?" + " WHERE upc=? ";
    private static String DELETE = "DELETE FROM `store_product` WHERE upc=?";

    private static String ALL_PRODUCTS_FOR_SALE = "SELECT * FROM `product` WHERE product_number IN (SELECT product_number FROM 'store_product' WHERE promotional_product =true) ORDER BY product_name";
    private static String ALL_PRODUCTS_NOT_FOR_SALE = "SELECT * FROM `product` WHERE product_number IN (SELECT product_number FROM 'store_product' WHERE promotional_product =false) ORDER BY product_name";

    private static String ALL_PRODUCTS_FOR_SALE_SORT_BY_NUMBER = "SELECT * FROM `product` WHERE product_number IN (SELECT product_number FROM 'store_product' WHERE promotional_product =true) ORDER BY products_number";
    private static String ALL_PRODUCTS_NOT_FOR_SALE_BY_NUMBER = "SELECT * FROM `product` WHERE product_number IN (SELECT product_number FROM 'store_product' WHERE promotional_product =false) ORDER BY products_number";
    private static String ALL_PRODUCTS_FROM_STORE_SORTED_BY_NUMBER =   "SELECT product_name FROM 'product' INNER JOIN 'store_product' ON product.id_product = store_product.id_product ORDER BY products_number";


    // table columns names
            private static String UPC = "upc";
    private static String UPC_PROM = "upc_prom";
    private static String ID_PRODUCT = "id_product";
    private static String SELLING_PRICE = "selling_price";
    private static String PRODUCTS_NUMBER = "products_number";
    private static String PROMOTIONAL_PRODUCT = "promotional_product";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcStore_productDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcStore_productDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Store_Product> aLL_PRODUCTS_FOR_SALE_SORT_BY_NUMBER() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_FOR_SALE_SORT_BY_NUMBER)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }
    @Override
    public List<Store_Product> aLL_PRODUCTS_NOT_FOR_SALE_SORT_BY_NUMBER() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_NOT_FOR_SALE_BY_NUMBER)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public List<Store_Product> aLL_PRODUCTS_FROM_STORE_SORTED_BY_NUMBER() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_FROM_STORE_SORTED_BY_NUMBER)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public List<Store_Product> aLL_PRODUCTS_NOT_FOR_SALE() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_NOT_FOR_SALE)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }





    @Override
    public List<Store_Product> aLL_PRODUCTS_FOR_SALE() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(ALL_PRODUCTS_FOR_SALE)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }
    @Override
    public List<Store_Product> getAll() {
        List<Store_Product> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractStore_ProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }
    @Override
    public Optional<Store_Product> getByIdFOR_KASIR(String id) {
        Optional<Store_Product> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID_FOR_KASIR)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractStore_ProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }
    @Override
    public Optional<Store_Product> getById(String id) {
        Optional<Store_Product> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractStore_ProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Store_Product store_product) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {



            query.setString(1, store_product.getUpc_prom());
            query.setLong(2, store_product.getId_product());
            query.setBigDecimal(3, store_product.getSelling_price());
            query.setInt(4, store_product.getProducts_number());
            query.setBoolean(5, store_product.getPromotional_product());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                store_product.setUpc(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Store_Product store_product) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {


            query.setString(1, store_product.getUpc_prom());
            query.setLong(2, store_product.getId_product());
            query.setBigDecimal(3, store_product.getSelling_price());
            query.setInt(4, store_product.getProducts_number());
            query.setBoolean(5, store_product.getPromotional_product());

            query.setString(6, store_product.getUpc());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao update SQL exception: " + store_product.getUpc(), e);
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

    protected static Store_Product extractStore_ProductFromResultSet(ResultSet resultSet) throws SQLException {

        return new Store_Product.Builder().
                setUpc(resultSet.getString(UPC)).
                setUpc_prom(resultSet.getString(UPC_PROM))
                .setId_product(resultSet.getLong(ID_PRODUCT))
                .setSelling_price(resultSet.getBigDecimal(SELLING_PRICE))

                .setProducts_number(resultSet.getInt(PRODUCTS_NUMBER))
                .setPromotional_product(resultSet.getBoolean(PROMOTIONAL_PRODUCT))

                .build();
    }

    protected static Store_Product extractStore_ProductGeneralInfoFromResultSet(ResultSet resultSet) throws SQLException {

        return new Store_Product.Builder().
        setUpc(resultSet.getString(UPC))
                .setId_product(resultSet.getLong(ID_PRODUCT))
                .build();
    }


}