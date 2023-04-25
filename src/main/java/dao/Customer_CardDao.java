package dao;

import Entity.Customer_Card;

public interface Customer_CardDao extends GenericDao<Customer_Card, String>, AutoCloseable {
    void close();
}
