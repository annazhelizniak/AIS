package dao;

import Entity.Sale;

public interface SaleDao extends GenericDao<Sale, String>, AutoCloseable {
    void close();
}
