package dao;

import Entity.Check;

public interface CheckDao extends GenericDao<Check, String>, AutoCloseable {
    void close();
}
