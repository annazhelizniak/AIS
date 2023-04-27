package dao.jdbc;

import Entity.Check;
import Entity.Customer_Card;
import Entity.Product;
import dao.Customer_CardDao;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import dao.CheckDao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCheckDao implements CheckDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcCheckDao.class);

    private static String GET_ALL = "SELECT * FROM `check` ORDER BY print_data";
    private static String GET_BY_ID = "SELECT * FROM `check` WHERE check_number=?";
    private static String CREATE = "INSERT INTO `check`"
            + " (id_employee, card_number, print_data, sum_total, vat) VALUES (?,  ?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `check`"
            + " SET id_employee=?, card_number=? , print_data=?, sum_total=?, vat=?" + " WHERE check_number=? ";
    private static String DELETE = "DELETE FROM `check` WHERE check_number=?";

    private static String CHECKS_OF_KASIR_FOR_PERIOD = "SELECT * FROM `check` WHERE id_employee=? AND print_data BETWEEN ? AND ? ORDER BY print_data";
    private static String CHECKS_FOR_PERIOD = "SELECT * FROM `check` WHERE print_data BETWEEN ? AND ?RDER BY print_data";
    private static String CHECKS_FOR_TODAY = "SELECT * FROM `check` WHERE print_data=CURDATE()";
    private static String SUM_CHECKS_OF_KASIR_FOR_PERIOD = "SELECT SUM(sum_total) FROM `check` WHERE id_employee=? AND print_data BETWEEN ? AND ?";
    private static String SUM_CHECKS_FOR_PERIOD = "SELECT SUM(sum_total) FROM `check`  WHERE print_data BETWEEN ? AND ?";
    private static String AMOUNT_OF_PRODUCT_SAILED_IN_PERIOD = "SELECT product_name"
            + " FROM `product` JOIN `store_product` USING(id_product) JOIN 'sale' USING(upc) JOIN 'check' USING(check_number)"
            + " WHERE id_product=? AND print_data BETWEEN ? AND ?";


    // table columns names
    private static String CHECK_NUMBER = "check_number";
    private static String ID_EMPLOYEE = "id_employee";
    private static String CARD_NUMBER = "card_number";
    private static String PRINT_DATA = "print_data";
    private static String SUM_TOTAL = "sum_total";
    private static String VAT = "vat";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCheckDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public JdbcCheckDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BigDecimal aMOUNT_OF_PRODUCT_SAILED_IN_PERIOD(String product, LocalDateTime start, LocalDateTime end){    BigDecimal res=BigDecimal.ZERO;
        try (PreparedStatement query = connection.prepareStatement(AMOUNT_OF_PRODUCT_SAILED_IN_PERIOD)) {
            query.setString(1, product);
            query.setTimestamp(2, Timestamp.valueOf(start));
            query.setTimestamp(3, Timestamp.valueOf(end));

            ResultSet resultSet = query.executeQuery();
            res= (BigDecimal) resultSet;
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + product, e);
            throw new ServerException(e);
        }
        return res;

    }

    @Override
    public BigDecimal sUM_CHECKS_FOR_PERIOD(LocalDateTime start, LocalDateTime end){
     BigDecimal res=BigDecimal.ZERO;
        try (PreparedStatement query = connection.prepareStatement(SUM_CHECKS_FOR_PERIOD)) {

            query.setTimestamp(1, Timestamp.valueOf(start));
            query.setTimestamp(2, Timestamp.valueOf(end));

            ResultSet resultSet = query.executeQuery();
           res= (BigDecimal) resultSet;
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + start+end, e);
            throw new ServerException(e);
        }
        return res;

    }

    @Override
    public BigDecimal sUM_CHECKS_OF_KASIR_FOR_PERIOD(String kasir_id, LocalDateTime start, LocalDateTime end){    BigDecimal res=BigDecimal.ZERO;
        try (PreparedStatement query = connection.prepareStatement(SUM_CHECKS_OF_KASIR_FOR_PERIOD)) {
            query.setString(1, kasir_id);
            query.setTimestamp(2, Timestamp.valueOf(start));
            query.setTimestamp(3, Timestamp.valueOf(end));

            ResultSet resultSet = query.executeQuery();
            res= (BigDecimal) resultSet;
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + start+end, e);
            throw new ServerException(e);
        }
        return res;

    }




    @Override
    public List<Check> cHECKS_FOR_TODAY( ){
        List<Check> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(CHECKS_FOR_TODAY)) {
            while (resultSet.next()) {
                categories.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;

    }

    @Override
    public List<Check> cHECKS_FOR_PERIOD( LocalDateTime start, LocalDateTime end){
        List<Check> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(CHECKS_FOR_PERIOD)) {

            query.setTimestamp(1, Timestamp.valueOf(start));
            query.setTimestamp(2, Timestamp.valueOf(end));

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + start+end, e);
            throw new ServerException(e);
        }
        return customerCards;

    }

    @Override
    public List<Check> cHECKS_OF_KASIR_FOR_PERIOD(String kasir_id, LocalDateTime start, LocalDateTime end){
        List<Check> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(CHECKS_OF_KASIR_FOR_PERIOD)) {
            query.setString(1, kasir_id);
            query.setTimestamp(2, Timestamp.valueOf(start));
            query.setTimestamp(3, Timestamp.valueOf(end));

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + kasir_id, e);
            throw new ServerException(e);
        }
        return customerCards;

    }




    @Override
    public List<Check> getAll() {
        List<Check> categories = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractCheckFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

   @Override
    public Optional<Check> getById(String id) {
        Optional<Check> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractCheckFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(Check check) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, check.getId_employee());
            query.setString(2, check.getCard_number());
            query.setTimestamp(3,Timestamp.valueOf( check.getPrint_date()));
            query.setBigDecimal(4, check.getSum_total());
            query.setBigDecimal(5, check.getVat());

            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                check.setCheck_number(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Check check) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {


            query.setString(1, check.getId_employee());
            query.setString(2, check.getCard_number());
            query.setTimestamp(3,Timestamp.valueOf( check.getPrint_date()));
            query.setBigDecimal(4, check.getSum_total());
            query.setBigDecimal(5, check.getVat());
            query.setString(6, check.getCheck_number());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao update SQL exception: " + check.getCheck_number(), e);
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

    protected static Check extractCheckFromResultSet(ResultSet resultSet) throws SQLException {

        return new Check.Builder().
                setCheck_number(resultSet.getString(CHECK_NUMBER)).
                setId_employee(resultSet.getString(ID_EMPLOYEE))
                .setCard_number(resultSet.getString(CARD_NUMBER))
                .setPrint_date(resultSet.getTimestamp(PRINT_DATA).toLocalDateTime())

                .setSum_total(resultSet.getBigDecimal(SUM_TOTAL))
                .setVat(resultSet.getBigDecimal(VAT))

                .build();
    }

    protected static Check extractCheckGeneralInfoFromResultSet(ResultSet resultSet) throws SQLException {

        return new Check.Builder().setCheck_number(resultSet.getString(CHECK_NUMBER)).setPrint_date(resultSet.getTimestamp(PRINT_DATA).toLocalDateTime())
                .setSum_total(resultSet.getBigDecimal(SUM_TOTAL))
                .build();
    }


}