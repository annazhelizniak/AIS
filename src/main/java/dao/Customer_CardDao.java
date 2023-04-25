package dao;

import Entity.Customer_Card;

import java.util.List;

public interface Customer_CardDao extends GenericDao<Customer_Card, String>, AutoCloseable {
    List<Customer_Card> searchCustomersByName(String cust_name);

    //List<Customer_Card> searchCustomerByProduct(String card_number);

    void close();
}
