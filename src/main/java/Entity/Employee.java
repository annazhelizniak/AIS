package Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Employee {
    private String id_employee;
    private String empl_surname;
    private String empl_name;
    private String empl_patronymic;
    private String empl_role;
    private BigDecimal salary;
    private LocalDateTime date_of_birth;
    private LocalDateTime date_of_start;
    private String phone_number;
    private String city;
    private String street;
    private String zip_code;

    private String email;

    private String password;

    public Employee() {
    }
    public static class Builder implements IBuilder<Employee> {
        Employee employee = new Employee();
        public Builder setId_employee(String id_employee) {
            employee.id_employee = id_employee;
            return this;
        }

        public Builder setEmpl_surname(String empl_surname) {
            employee.empl_surname = empl_surname;
            return this;
        }

        public Builder setEmpl_name(String empl_name) {
            employee.empl_name = empl_name;
            return this;
        }

        public Builder setEmpl_patronymic(String empl_patronymic) {
            employee.empl_patronymic = empl_patronymic;
            return this;
        }

        public Builder setEmpl_role(String empl_role) {
            employee.empl_role = empl_role;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            employee.salary = salary;
            return this;
        }

        public Builder setDate_of_birth(LocalDateTime date_of_birth) {
            employee.date_of_birth = date_of_birth;
            return this;
        }

        public Builder setDate_of_start(LocalDateTime date_of_start) {
            employee.date_of_start = date_of_start;
            return this;
        }

        public Builder setPhone_number(String phone_number) {
            employee.phone_number = phone_number;
            return this;
        }

        public Builder setCity(String city) {
            employee.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            employee.street = street;
            return this;
        }

        public Builder setZip_code(String zip_code) {
            employee.zip_code = zip_code;
            return this;
        }

        public Builder setEmail(String email) {
            employee.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            employee.password = password;
            return this;
        }

        @Override
        public Employee build() {
            return employee;
        }
    }
    public String getId_employee() {
        return id_employee;
    }

    public String getEmpl_surname() {
        return empl_surname;
    }

    public String getEmpl_name() {
        return empl_name;
    }

    public String getEmpl_patronymic() {
        return empl_patronymic;
    }

    public String getEmpl_role() {
        return empl_role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDateTime getDate_of_birth() {
        return date_of_birth;
    }

    public LocalDateTime getDate_of_start() {
        return date_of_start;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public void setEmpl_surname(String empl_surname) {
        this.empl_surname = empl_surname;
    }

    public void setEmpl_name(String empl_name) {
        this.empl_name = empl_name;
    }

    public void setEmpl_patronymic(String empl_patronymic) {
        this.empl_patronymic = empl_patronymic;
    }

    public void setEmpl_role(String empl_role) {
        this.empl_role = empl_role;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDate_of_birth(LocalDateTime date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setDate_of_start(LocalDateTime date_of_start) {
        this.date_of_start = date_of_start;
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
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id_employee.equals(employee.id_employee) && empl_surname.equals(employee.empl_surname) && empl_name.equals(employee.empl_name) && Objects.equals(empl_patronymic, employee.empl_patronymic) && empl_role.equals(employee.empl_role) && salary.equals(employee.salary) && date_of_birth.equals(employee.date_of_birth) && date_of_start.equals(employee.date_of_start) && phone_number.equals(employee.phone_number) && city.equals(employee.city) && street.equals(employee.street) && zip_code.equals(employee.zip_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, date_of_start, phone_number, city, street, zip_code);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee='" + id_employee + '\'' +
                ", empl_surname='" + empl_surname + '\'' +
                ", empl_name='" + empl_name + '\'' +
                ", empl_patronymic='" + empl_patronymic + '\'' +
                ", empl_role='" + empl_role + '\'' +
                ", salary=" + salary +
                ", date_of_birth=" + date_of_birth +
                ", date_of_start=" + date_of_start +
                ", phone_number='" + phone_number + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zip_code='" + zip_code + '\'' +
                '}';
    }
}
