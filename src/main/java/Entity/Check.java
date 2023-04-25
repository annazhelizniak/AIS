package Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Check {
    private String check_number;
    private String id_employee;
    private String card_number;
    private LocalDateTime print_date;
    private BigDecimal sum_total;
    private BigDecimal vat;

    public Check() {
    }
    public static class Builder implements IBuilder<Check> {

        Check check = new Check();
        public Builder setCheck_number(String check_number) {
            check.check_number = check_number;
            return this;
        }

        public Builder setId_employee(String id_employee) {
            check.id_employee = id_employee;
            return this;
        }

        public Builder setCard_number(String card_number) {
            check.card_number = card_number;
            return this;
        }

        public Builder setPrint_date(LocalDateTime print_date) {
            check.print_date = print_date;
            return this;
        }

        public Builder setSum_total(BigDecimal sum_total) {
            check.sum_total = sum_total;
            return this;
        }

        public Builder setVat(BigDecimal vat) {
            check.vat = vat;
            return this;
        }
        @Override
        public Check build() {
            return check;
        }
    }
    public String getCheck_number() {
        return check_number;
    }

    public String getId_employee() {
        return id_employee;
    }

    public String getCard_number() {
        return card_number;
    }

    public LocalDateTime getPrint_date() {
        return print_date;
    }

    public BigDecimal getSum_total() {
        return sum_total;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setPrint_date(LocalDateTime print_date) {
        this.print_date = print_date;
    }

    public void setSum_total(BigDecimal sum_total) {
        this.sum_total = sum_total;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return check_number.equals(check.check_number) && id_employee.equals(check.id_employee) && Objects.equals(card_number, check.card_number) && print_date.equals(check.print_date) && sum_total.equals(check.sum_total) && vat.equals(check.vat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(check_number, id_employee, card_number, print_date, sum_total, vat);
    }

    @Override
    public String toString() {
        return "Check{" +
                "check_number='" + check_number + '\'' +
                ", id_employee='" + id_employee + '\'' +
                ", card_number='" + card_number + '\'' +
                ", print_date=" + print_date +
                ", sum_total=" + sum_total +
                ", vat=" + vat +
                '}';
    }
}
