package dao;

import Entity.Product;
import Entity.Store_Product;

import java.util.List;
import java.util.Optional;

public interface Store_productDao extends GenericDao<Store_Product, String>, AutoCloseable {
    List<Store_Product> aLL_PRODUCTS_FOR_SALE();
    Optional<Store_Product> getByIdFOR_KASIR(String id);
    List<Store_Product> aLL_PRODUCTS_FOR_SALE_SORT_BY_NUMBER();
    List<Store_Product> aLL_PRODUCTS_NOT_FOR_SALE_SORT_BY_NUMBER();
    List<Store_Product> aLL_PRODUCTS_FROM_STORE_SORTED_BY_NUMBER();
    List<Store_Product> aLL_PRODUCTS_NOT_FOR_SALE();






    void close();
}
