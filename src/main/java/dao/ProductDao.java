package dao;

import Entity.Customer_Card;
import Entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long>, AutoCloseable {


    List<Product> aLL_PRODUCTS_FROM_STORE_SORTED_BY_NAME();
    List<Product> aLL_PRODUCTS_FROM_STORE_FROM_PRODUCER(String producer);
    List<Product> aLL_PRODUCTS_FROM_STORE_FROM_CATEGORY(String category);
    List<Product> aLL_PRODUCTS_FROM_STORE_WITH_CHARACTERISTICS(String characteristics);
    List<Product> aLL_PRODUCTS_WITH_NAME(String name);


    List<Product> aLL_PRODUCTS_IN_CHECK();
    List<Product> aLL_PRODUCTS_IN_CHECK_WITH_CHARACTERISTICS(String characteristics);
    List<Product> aLL_PRODUCTS_IN_CHECK_PRODUCER(String producer);
    List<Product> aLL_PRODUCTS_IN_CHECK_FROM_CATEGORY(String category);


    List<Product> aLL_PRODUCTS_PRODUCER(String producer);
    List<Product> aLL_PRODUCTS_FROM_CATEGORY(String category);
    List<Product> aLL_PRODUCTS_WITH_CHARACTERISTICS(String characteristics);

    void close();
}
