package Entity;

import java.util.Objects;

public class Product {
    private Long id_product;
    private Long category_number;
    private String product_name;

    private String producer;
    private String characteristics;

    public Product(){}

    public static class Builder implements IBuilder<Product> {

        Product product = new Product();

        public Builder setId_product(Long id_product) {
            product.id_product = id_product;
            return this;
        }

        public Builder setCategory_number(Long category_number) {
            product.category_number = category_number;
            return this;
        }
        public Builder setProduct_name(String name) {
            product.product_name = name;
            return this;
        }
        public Builder setProducer(String producer) {
            product.producer = producer;
            return this;
        }

        public Builder setCharacteristics(String characteristics) {
            product.characteristics = characteristics;
            return this;
        }

        @Override
        public Product build() {
            return product;
        }
    }
    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Long getCategory_number() {
        return category_number;
    }

    public void setCategory_number(Long category_number) {
        this.category_number = category_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id_product.equals(product.id_product) && category_number.equals(product.category_number) && product_name.equals(product.product_name) && characteristics.equals(product.characteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_product, category_number, product_name, characteristics);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_product=" + id_product +
                ", category_number=" + category_number +
                ", product_name='" + product_name + '\'' +
                ", characteristics='" + characteristics + '\'' +
                '}';
    }
}
