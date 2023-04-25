package dao;

import Entity.Check;
import java.util.List;
public interface CheckDao extends GenericDao<Check, String>, AutoCloseable {
    void close();
}
