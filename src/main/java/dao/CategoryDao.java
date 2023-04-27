package dao;

import Entity.Category;

import java.util.List;

public interface CategoryDao  extends GenericDao<Category, Long>, AutoCloseable {
    List<Category> searchCategoriesByName(String categoryName);

    List<Category> sEARCH_CATEGORY_IN_CHECK();
    List<Category> sEARCH_CATEGORY_FROM_STORE();

    void close();
}
