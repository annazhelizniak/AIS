
package dao.jdbc;

import Entity.Customer_Card;
import Entity.Employee;
import Entity.Product;
import Entity.Role;
import dao.CategoryDao;
import dao.Customer_CardDao;
import dao.EmployeeDao;
import exceptions.ServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEmployeeDao implements EmployeeDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcCustomer_CardDao.class);

    private static String GET_ALL = "SELECT * FROM `employee` ORDER BY empl_surname";
    private static String GET_BY_ID = "SELECT * FROM `employee` WHERE id_employee=?";
    private static String CREATE = "INSERT INTO `employee`"
            + " (empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
    private static String UPDATE = "UPDATE `employee`"
            + " SET empl_surname=?, empl_name=? , empl_patronymic=?, empl_role=?, salary=?, date_of_birth=?, date_of_start=?, phone_number=?, city=?, street=?, zip_code=?" + " WHERE id_employee=? ";
    private static String DELETE = "DELETE FROM `employee` WHERE id_employee=?";
    private static String GET_ALL_KASIRS = "SELECT * FROM `employee` WHERE empl_role='kasir' ORDER BY empl_surname";
    private static String FIND_PHONE_ADD_BY_SURNAME = "SELECT phone_number, city, street, zipcode FROM `employee` WHERE empl_surname=?";

    // table columns names
    private static String ID_EMPLOYEE = "id_employee";
    private static String EMPL_SURNAME = "empl_surname";
    private static String EMPL_NAME = "empl_name";
    private static String EMPL_PATRONYMIC = "empl_patronymic";
    private static String EMPL_ROLE = "empl_role";
    private static String SALARY = "salary";
    private static String DATE_OF_BIRTH = "date_of_birth";
    private static String DATE_OF_START = "date_of_start";
    private static String PHONE_NUMBER = "phone_number";
    private static String CITY = "city";
    private static String STREET = "street";
    private static String ZIP_CODE = "zip_code";

    private static String EMAIL = "email";
    private static String PASSWORD = "password";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcEmployeeDao(Connection connection) {
        this.connection = connection;
        connectionShouldBeClosed = false;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public JdbcEmployeeDao(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }
    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                employees.add(extractUEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return employees;
    }
    @Override
    public List<Employee> gET_ALL_KASIRS() {
        List<Employee> employees = new ArrayList<>();

        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL_KASIRS)) {
            while (resultSet.next()) {
                employees.add(extractUEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao getAll SQL exception", e);
            throw new ServerException(e);
        }
        return employees;
    }
    @Override
    public List<Employee> fIND_PHONE_ADD_BY_SURNAME(String surname){
        List<Employee> customerCards = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_PHONE_ADD_BY_SURNAME)) {
            query.setString(1, surname);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                customerCards.add(extractUEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao searchUsersBySurname SQL exception: " + surname, e);
            throw new ServerException(e);
        }
        return customerCards;

    }


    @Override
    public Optional<Employee> getById(String id) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = Optional.of(extractUEmployeeFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return employee;
    }



    @Override
    public void create(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, employee.getEmpl_surname());
            query.setString(2, employee.getEmpl_name());
            query.setString(3, employee.getEmpl_patronymic());
            query.setString(4, employee.getEmpl_role());
            query.setBigDecimal(5, employee.getSalary());
            query.setTimestamp(6, Timestamp.valueOf(employee.getDate_of_birth()));
            query.setTimestamp(7, Timestamp.valueOf(employee.getDate_of_start()));
            query.setString(8, employee.getPhone_number());
            query.setString(9, employee.getCity());
            query.setString(10, employee.getStreet());
            query.setString(11, employee.getZip_code());
            query.setString(12, employee.getEmail());
            query.setString(13, employee.getPassword());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                employee.setId_employee(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {

            query.setString(1, employee.getEmpl_surname());
            query.setString(2, employee.getEmpl_name());
            query.setString(3, employee.getEmpl_patronymic());
            query.setString(4, employee.getEmpl_role());
            query.setBigDecimal(5, employee.getSalary());
            query.setTimestamp(6, Timestamp.valueOf(employee.getDate_of_birth()));
            query.setTimestamp(7, Timestamp.valueOf(employee.getDate_of_start()));
            query.setString(8, employee.getPhone_number());
            query.setString(9, employee.getCity());
            query.setString(10, employee.getStreet());
            query.setString(11, employee.getZip_code());
            query.setString(12, employee.getEmail());
            query.setString(13, employee.getPassword());
            query.setString(14, employee.getId_employee());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserDao update SQL exception: " + employee.getId_employee(), e);
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

    protected static Employee extractUEmployeeFromResultSet(ResultSet resultSet) throws SQLException {

        return new Employee.Builder().setId_employee(resultSet.getString(ID_EMPLOYEE))
                .setEmpl_name(resultSet.getString(EMPL_SURNAME))
                .setEmpl_surname(resultSet.getString(EMPL_NAME))
                .setEmpl_patronymic(resultSet.getString(EMPL_PATRONYMIC))
                .setEmpl_role(resultSet.getString(EMPL_ROLE))
                .setSalary(resultSet.getBigDecimal(SALARY))
                .setDate_of_birth(resultSet.getTimestamp(DATE_OF_BIRTH).toLocalDateTime())
                .setDate_of_start(resultSet.getTimestamp(DATE_OF_START).toLocalDateTime())
                .setPhone_number(resultSet.getString(PHONE_NUMBER))
                .setCity(resultSet.getString(CITY))
                .setStreet(resultSet.getString(STREET))
                .setZip_code(resultSet.getString(ZIP_CODE))
                .setEmail(resultSet.getString(EMAIL))
                .setPassword(resultSet.getString(PASSWORD))
                .build();
    }

    protected static Employee extractEmployeeGeneralInfoFromResultSet(ResultSet resultSet) throws SQLException {

        return new Employee.Builder().setId_employee(resultSet.getString(ID_EMPLOYEE))
                .setEmpl_name(resultSet.getString(EMPL_SURNAME))
                .setEmpl_surname(resultSet.getString(EMPL_NAME))
                .build();
    }


}
