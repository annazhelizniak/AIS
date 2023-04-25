package dao;

import Entity.Store_Product;

public interface Store_productDao extends GenericDao<Store_Product, String>, AutoCloseable {
    void close();
}
