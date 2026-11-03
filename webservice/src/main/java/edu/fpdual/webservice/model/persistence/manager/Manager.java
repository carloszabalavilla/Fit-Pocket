package edu.fpdual.webservice.model.persistence.manager;

import java.sql.Connection;
import java.util.List;

public interface Manager<T> {

    /**
     * Find all the entities.
     *
     * @param con DB connection
     * @return a {@link List} of {@link T}
     */
    List<T> findAll(Connection con);

    /**
     * Find a set of entities using a fieldName and a value set.
     *
     * @param con       DB connection
     * @param fieldName String fieldName to search for.
     * @param value     A value to search.
     * @return a {@link List} of {@link T}
     */
    List<T> findAllBy(Connection con, String fieldName, Object value);

    /**
     * Find an entity using a fieldName and its value.
     *
     * @param con       DB connection
     * @param fieldName String fieldName to search for.
     * @param value     A value to search.
     * @return a {@link T}
     */
    T findBy(Connection con, String fieldName, Object value);

    /**
     * Deletes an entity.
     *
     * @param con    DB connection
     * @param entity T entity to search for.
     * @return a {@link Boolean}
     */
    boolean delete(Connection con, T entity);

    /**
     * Creates an entity.
     *
     * @param con    DB connection
     * @param entity The entity to create
     * @return a {@link Boolean}
     */

    boolean create(Connection con, T entity);

    /**
     * Updates an entity.
     *
     * @param con    DB connection
     * @param entity The entity to update
     * @return a {@link Boolean}
     */
    boolean update(Connection con, T entity);
}
