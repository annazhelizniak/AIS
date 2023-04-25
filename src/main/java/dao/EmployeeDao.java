package dao;

import Entity.Employee;

public interface EmployeeDao extends GenericDao<Employee, String>, AutoCloseable {
    void close();
}
