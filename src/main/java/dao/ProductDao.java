package dao;

import Entity.Product;

public interface ProductDao extends GenericDao<Product, Long>, AutoCloseable {
    void close();
}
