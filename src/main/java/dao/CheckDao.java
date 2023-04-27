package dao;

import Entity.Check;
import Entity.Store_Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public interface CheckDao extends GenericDao<Check, String>, AutoCloseable {

    BigDecimal sUM_CHECKS_OF_KASIR_FOR_PERIOD(String kasir_id, LocalDateTime start, LocalDateTime end);

    BigDecimal aMOUNT_OF_PRODUCT_SAILED_IN_PERIOD(String product, LocalDateTime start, LocalDateTime end);
    BigDecimal sUM_CHECKS_FOR_PERIOD(LocalDateTime start, LocalDateTime end);
    List<Check> cHECKS_FOR_TODAY();
    List<Check> cHECKS_FOR_PERIOD(LocalDateTime start, LocalDateTime end);
    List<Check> cHECKS_OF_KASIR_FOR_PERIOD(String kasir_id, LocalDateTime start, LocalDateTime end);
    void close();
}
