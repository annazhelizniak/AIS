package Entity;

import java.util.Objects;

public class Customer_Card {
    private String card_number;
    private String cust_surname;
    private String cust_name;
    private String cust_patronymic;
    private String phone_number;
    private String city;
     private String street;
     private String zip_code;
     private int percent;

    public Customer_Card() {
    }
    public static class Builder implements IBuilder<Customer_Card> {

        Customer_Card customerCard = new Customer_Card();
        public Builder setCard_number(String card_number) {
            customerCard.card_number = card_number;
            return this;
        }

        public Builder setCust_surname(String cust_surname) {
            customerCard.cust_surname = cust_surname;
            return this;
        }

        public Builder setCust_name(String cust_name) {
            customerCard.cust_name = cust_name;
            return this;
        }

        public Builder setCust_patronymic(String cust_patronymic) {
            customerCard.cust_patronymic = cust_patronymic;
            return this;
        }

        public Builder setPhone_number(String phone_number) {
            customerCard.phone_number = phone_number;
            return this;
        }

        public Builder setCity(String city) {
            customerCard.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            customerCard.street = street;
            return this;
        }

        public Builder setZip_code(String zip_code) {
            customerCard.zip_code = zip_code;
            return this;
        }

        public Builder setPercent(int percent) {
            customerCard.percent = percent;
            return this;
        }

        @Override
        public Customer_Card build() {
            return customerCard;
        }
    }
    public String getCard_number() {
        return card_number;
    }

    public String getCust_surname() {
        return cust_surname;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getCust_patronymic() {
        return cust_patronymic;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip_code() {
        return zip_code;
    }

    public int getPercent() {
        return percent;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setCust_surname(String cust_surname) {
        this.cust_surname = cust_surname;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public void setCust_patronymic(String cust_patronymic) {
        this.cust_patronymic = cust_patronymic;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer_Card that = (Customer_Card) o;
        return percent == that.percent && card_number.equals(that.card_number) && cust_surname.equals(that.cust_surname) && cust_name.equals(that.cust_name) && Objects.equals(cust_patronymic, that.cust_patronymic) && phone_number.equals(that.phone_number) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(zip_code, that.zip_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent);
    }

    @Override
    public String toString() {
        return "Customer_Card{" +
                "card_number='" + card_number + '\'' +
                ", cust_surname='" + cust_surname + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", cust_patronymic='" + cust_patronymic + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", percent=" + percent +
                '}';
    }
}
