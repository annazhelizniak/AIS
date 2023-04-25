package dao;

import Entity.Category;

import java.util.List;

public interface CategoryDao  extends GenericDao<Category, Long>, AutoCloseable {
    List<Category> searchCategoriesByName(String categoryName);

    void close();
}
