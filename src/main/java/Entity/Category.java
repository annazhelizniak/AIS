package Entity;

import java.util.Objects;

public class Category {

    private Long category_number;
    private String category_name;

    public Category() {

    }

    public static class Builder implements IBuilder<Category> {

        Category category = new Category();

        public Builder setCategory_number(Long id) {
            category.category_number = id;
            return this;
        }

        public Builder setCategory_name(String name) {
            category.category_name = name;
            return this;
        }

        @Override
        public Category build() {
            return category;
        }
    }

    public Long getCategory_number() {
        return category_number;
    }

    public void setCategory_number(Long category_number) {
        this.category_number = category_number;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return category_number.equals(category.category_number) && category_name.equals(category.category_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_number, category_name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_number=" + category_number +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
