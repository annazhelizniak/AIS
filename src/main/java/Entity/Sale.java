package Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Sale {
    private String upc;
    private String check_number;
    private int product_number;
    private BigDecimal selling_price;

    public Sale() {
    }

    public static class Builder implements IBuilder<Sale> {

        Sale sale = new Sale();
        public Builder setUpc(String upc) {
            sale.upc = upc;
            return this;
        }

        public Builder setCheck_number(String check_number) {
            sale.check_number = check_number;
            return this;
        }

        public Builder setProduct_number(int product_number) {
            sale.product_number = product_number;
            return this;
        }

        public Builder setSelling_price(BigDecimal selling_price) {
            sale.selling_price = selling_price;
            return this;
        }
        @Override
        public Sale build() {
            return sale;
        }
    }

    public String getUpc() {
        return upc;
    }

    public String getCheck_number() {
        return check_number;
    }

    public int getProduct_number() {
        return product_number;
    }

    public BigDecimal getSelling_price() {
        return selling_price;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public void setProduct_number(int product_number) {
        this.product_number = product_number;
    }

    public void setSelling_price(BigDecimal selling_price) {
        this.selling_price = selling_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return product_number == sale.product_number && upc.equals(sale.upc) && check_number.equals(sale.check_number) && selling_price.equals(sale.selling_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upc, check_number, product_number, selling_price);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "upc='" + upc + '\'' +
                ", check_number='" + check_number + '\'' +
                ", product_number=" + product_number +
                ", selling_price=" + selling_price +
                '}';
    }
}
