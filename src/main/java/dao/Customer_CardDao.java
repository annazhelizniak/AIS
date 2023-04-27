package dao;

import Entity.Customer_Card;

import java.util.List;

public interface Customer_CardDao extends GenericDao<Customer_Card, String>, AutoCloseable {
    List<Customer_Card> searchCustomersBySurname(String cust_name);
    List<Customer_Card> gET_CLIENTS_BY_PERCENT(String persent);


    //List<Customer_Card> searchCustomerByProduct(String card_number);

    void close();
}
