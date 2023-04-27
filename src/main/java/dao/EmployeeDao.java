package dao;

import Entity.Customer_Card;
import Entity.Employee;

import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable {

    List<Employee> gET_ALL_KASIRS();
    List<Employee> fIND_PHONE_ADD_BY_SURNAME(String surname);

    void close();
}
