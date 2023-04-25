package Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Store_Product {

    private String upc;
    private String upc_prom;
    private Long id_product;
    private BigDecimal selling_price;
    private int products_number;
    private boolean promotional_product;

    public Store_Product() {
    }
    public static class Builder implements IBuilder<Store_Product> {

        Store_Product product = new Store_Product();
        public Builder setUpc(String upc) {
            product.upc = upc;
            return this;
        }

        public Builder setUpc_prom(String upc_prom) {
            product.upc_prom = upc_prom;
            return this;
        }

        public Builder setId_product(Long id_product) {
            product.id_product = id_product;
            return this;
        }

        public Builder setSelling_price(BigDecimal selling_price) {
            product.selling_price = selling_price;
            return this;
        }

        public Builder setProducts_number(int products_number) {
            product.products_number = products_number;
            return this;
        }

        public Builder setPromotional_product(boolean promotional_product) {
            product.promotional_product = promotional_product;
            return this;
        }
        @Override
        public Store_Product build() {
            return product;
        }
    }

    public String getUpc() {
        return upc;
    }

    public String getUpc_prom() {
        return upc_prom;
    }

    public Long getId_product() {
        return id_product;
    }

    public BigDecimal getSelling_price() {
        return selling_price;
    }

    public int getProducts_number() {
        return products_number;
    }

    public boolean getPromotional_product() {
        return promotional_product;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setUpc_prom(String upc_prom) {
        this.upc_prom = upc_prom;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public void setSelling_price(BigDecimal selling_price) {
        this.selling_price = selling_price;
    }

    public void setProducts_number(int products_number) {
        this.products_number = products_number;
    }

    public void setPromotional_product(boolean promotional_product) {
        this.promotional_product = promotional_product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store_Product that = (Store_Product) o;
        return products_number == that.products_number && promotional_product == that.promotional_product && upc.equals(that.upc) && Objects.equals(upc_prom, that.upc_prom) && id_product.equals(that.id_product) && selling_price.equals(that.selling_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upc, upc_prom, id_product, selling_price, products_number, promotional_product);
    }

    @Override
    public String toString() {
        return "Store_Product{" +
                "upc=" + upc +
                ", upc_prom=" + upc_prom +
                ", id_product=" + id_product +
                ", selling_price=" + selling_price +
                ", products_number=" + products_number +
                ", promotional_product=" + promotional_product +
                '}';
    }
}
