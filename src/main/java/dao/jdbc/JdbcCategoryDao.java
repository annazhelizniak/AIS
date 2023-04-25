package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDao;
import Entity.Category;
import exceptions.ServerException;

public class JdbcCategoryDao implements CategoryDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcCategoryDao.class);

    private static String GET_ALL = "SELECT * FROM `category` ORDER BY category_name";
    private static String GET_BY_ID = "SELECT * FROM `category` WHERE category_number=?";
    private static String CREATE = "INSERT INTO `category` (category_name) VALUES (?)";
    private static String UPDATE = "UPDATE `category` SET category_name=? WHERE category_number=?";
    private static String DELETE = "DELETE FROM `category` WHERE category_number=?";
    private static String SEARCH_CATEGORY_BY_NAME = "SELECT * FROM `category` WHERE LOWER(category_name) LIKE CONCAT('%', LOWER(?), '%')";

    // table columns names
    private static String ID = "category_number";
    private static String NAME = "category_name";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCategoryDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCategoryDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public Optional<Category> getById(Long id) {
        Optional<Category> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractCategoryFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Category category) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, category.getCategory_name());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                category.setCategory_number(keys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Category category) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, category.getCategory_name());
            query.setLong(2, category.getCategory_number());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao update SQL exception: " + category.getCategory_number(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public List<Category> searchCategoriesByName(String categoryName) {
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SEARCH_CATEGORY_BY_NAME)) {
            query.setString(1, categoryName);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchCategoryByName SQL exception: " + categoryName, e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcCategoryDao Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return new Category.Builder().setCategory_number(resultSet.getLong(ID)).setCategory_name(resultSet.getString(NAME)).build();
    }

}
