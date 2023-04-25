
package dao.jdbc;

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

    public class JdbcCustomer_CardDao implements Customer_CardDao {
        private static final Logger LOGGER = LogManager.getLogger(JdbcCustomer_CardDao.class);

        private static String GET_ALL = "SELECT * FROM `customer_card` ORDER BY cust_surname";
        private static String GET_BY_ID = "SELECT * FROM `customer_card` WHERE card_number=?";
        private static String CREATE = "INSERT INTO `customer_card`"
                + " (cust_name, cust_surname, city, street, zipcode, phone_number, percent, cust_patronymic) VALUES (?, ?, ?,?, ?, ?, ?, ?)";
        private static String UPDATE = "UPDATE `customer_card`"
                + " SET cust_name=?, cust_surname=? , city=?, street=?, zipcode=?, phone_number=?, percent=?, cust_patronymic=?" + " WHERE card_number=? ";
        private static String DELETE = "DELETE FROM `customer_card` WHERE card_number=?";
        private static String SEARCH_USERS_BY_SURNAME = "SELECT * FROM `customer_card` WHERE LOWER(cust_surname) LIKE CONCAT('%', LOWER(?), '%')";

        private static String SEARCH_BEST_WAITERS_PER_PERIOD = "SELECT * FROM `customer_card` WHERE card_number IN "
                + "(SELECT card_number FROM `order`"
                + " WHERE `date` BETWEEN ? AND ?"
                + " GROUP BY card_number"
                + " HAVING COUNT(id_order)=(SELECT MAX(orders_number)"
                + " FROM (SELECT COUNT(id_order) AS orders_number"
                + " FROM `order` WHERE `date` BETWEEN ? AND ?"
                + " GROUP BY card_number) AS `orders_counter`))";

        // table columns names
        private static String CARD_NUMBER = "card_number";
        private static String CUST_SURNAME = "cust_surname";
        private static String CUST_NAME = "cust_name";
        private static String CUST_PATRONYMIC = "cust_patronymic";
        private static String PHONE_NUMBER = "phone_number";
        private static String CITY = "city";
        private static String STREET = "street";
        private static String ZIPCODE = "zipcode";
        private static String PERCENT = "percent";


        private Connection connection;
        private boolean connectionShouldBeClosed;

        public JdbcCustomer_CardDao(Connection connection) {
            this.connection = connection;
            connectionShouldBeClosed = false;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        public JdbcCustomer_CardDao(Connection connection, boolean connectionShouldBeClosed) {
            this.connection = connection;
            this.connectionShouldBeClosed = connectionShouldBeClosed;
        }
        @Override
        public List<Customer_Card> getAll() {
            List<Customer_Card> customerCards = new ArrayList<>();

            try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
                while (resultSet.next()) {
                    customerCards.add(extractUserFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao getAll SQL exception", e);
                throw new ServerException(e);
            }
            return customerCards;
        }

        @Override
        public Optional<Customer_Card> getById(String id) {
            Optional<Customer_Card> customerCard = Optional.empty();
            try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
                query.setString(1, id);
                ResultSet resultSet = query.executeQuery();
                while (resultSet.next()) {
                    customerCard = Optional.of(extractUserFromResultSet(resultSet));
                }

            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao getById SQL exception: " + id, e);
                throw new ServerException(e);
            }
            return customerCard;
        }



        @Override
        public void create(Customer_Card customerCard) {
            try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

                query.setString(1, customerCard.getCust_surname());
                query.setString(2, customerCard.getCust_name());
                query.setString(3, customerCard.getCust_patronymic());
                query.setString(4, customerCard.getPhone_number());
                query.setString(5, customerCard.getCity());
                query.setString(6, customerCard.getStreet());
                query.setString(7, customerCard.getZip_code());
                query.setInt(8, customerCard.getPercent());
                query.executeUpdate();

                ResultSet keys = query.getGeneratedKeys();
                if (keys.next()) {
                    customerCard.setCard_number(keys.getString(1));
                }
            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao create SQL exception", e);
                throw new ServerException(e);
            }
        }

        @Override
        public void update(Customer_Card customerCard) {
            try (PreparedStatement query = connection.prepareStatement(UPDATE)) {

                query.setString(1, customerCard.getCust_surname());
                query.setString(2, customerCard.getCust_name());
                query.setString(3, customerCard.getCust_patronymic());
                query.setString(4, customerCard.getPhone_number());
                query.setString(5, customerCard.getCity());
                query.setString(6, customerCard.getStreet());
                query.setString(7, customerCard.getZip_code());
                query.setInt(8, customerCard.getPercent());
                query.setString(9, customerCard.getCard_number());
                query.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao update SQL exception: " + customerCard.getCard_number(), e);
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
        public List<Customer_Card> searchCustomersByName(String surname) {
            List<Customer_Card> customerCards = new ArrayList<>();

            try (PreparedStatement query = connection.prepareStatement(SEARCH_USERS_BY_SURNAME)) {
                query.setString(1, surname);
                ResultSet resultSet = query.executeQuery();
                while (resultSet.next()) {
                    customerCards.add(extractUserFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + surname, e);
                throw new ServerException(e);
            }
            return customerCards;
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

        protected static Customer_Card extractUserFromResultSet(ResultSet resultSet) throws SQLException {

            return new Customer_Card.Builder().setCard_number(resultSet.getString(CARD_NUMBER)).setCust_name(resultSet.getString(CUST_NAME))
                    .setCust_surname(resultSet.getString(CUST_SURNAME)).setCust_patronymic(resultSet.getString(CUST_PATRONYMIC))
                    .setPhone_number(resultSet.getString(PHONE_NUMBER))
                    .setCity(resultSet.getString(CITY))
                    .setStreet(resultSet.getString(STREET))
                    .setZip_code(resultSet.getString(ZIPCODE))
                    .setPercent(resultSet.getInt(PERCENT))

                    .build();
        }

        protected static Customer_Card extractUserGeneralInfoFromResultSet(ResultSet resultSet) throws SQLException {

            return new Customer_Card.Builder().setCard_number(resultSet.getString(CARD_NUMBER)).setCust_name(resultSet.getString(CUST_NAME))
                    .setCust_surname(resultSet.getString(CUST_SURNAME)).build();
        }


    }
