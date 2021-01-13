/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.dao;

import br.com.pim.model.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityTransaction;


public interface DataAccessObject extends java.io.Serializable {

    public static final int DEFAULT_MAX_RESULTS = 10;

    /**
     * Find an entity through its id
     * 
     * @param <E>
     * @param clazz
     * @param id
     * @return 
     */
    <E extends BaseEntity> E find(Class<E> clazz, Serializable id);

    /**
     * Persists a new object
     * 
     * @param <E>
     * @param obj
     * @return 
     */
    <E extends BaseEntity> E persist(E obj);

    /**
     * Update a given object
     * 
     * @param <E>
     * @param obj
     * @return 
     */
    <E extends BaseEntity> E update(E obj);

    /**
     * Removes an object
     * 
     * @param <E>
     * @param obj
     * @return 
     */
    <E extends BaseEntity> E remove(E obj);

    /**
     * Merge a given object. This action can also persist it if it doesn't exits
     * 
     * @param <E>
     * @param obj
     * @return 
     */
    <E extends BaseEntity> E merge(E obj);

    /**
     * Retrieves an object through a query which should contains only one result
     * 
     * @param <E>
     * @param name
     * @param params
     * @return 
     */
    <E> E singleResultNamedQuery(String name, Map<String, Object> params);

    /**
     * Runs a native named query following the db standards
     * 
     * @param <E>
     * @param name
     * @param params
     * @param clazz
     * @return 
     */
    <E extends BaseEntity> List<E> nativeNamedQuery(String name, Map<Integer, Object> params, Class<E> clazz);

    /**
     * Runs a native named query following the db standards
     * 
     * @param <E>
     * @param name
     * @param params
     * @param clazz
     * @param max
     * @return 
     */
    <E extends BaseEntity> List<E> nativeNamedQuery(String name, Map<Integer, Object> params, Class<E> clazz, Integer max);

    /**
     * Runs a named query through the configuration orm
     * 
     * @param name
     * @return 
     */
    List namedQueryGenericType(String name);

    /**
     * Runs a named query through the configuration orm
     * 
     * @param <E>
     * @param name
     * @return 
     */
    <E extends BaseEntity> List<E> namedQuery(String name);
    
    /**
     * Runs a named query through the configuration orm
     * 
     * @param <E>
     * @param name
     * @param params
     * @return 
     */
    <E extends BaseEntity> List<E> namedQuery(String name, Map<String, Object> params);
    
    /**
     * Runs a named query through the configuration orm
     * 
     * @param <E>
     * @param name
     * @param params
     * @param first
     * @param rows
     * @return 
     */
    <E extends BaseEntity> List<E> namedQuery(String name, Map<String, Object> params, Integer first, Integer rows);

    /**
     * Runs a named query through the configuration orm
     * 
     * @param <E>
     * @param name
     * @param params
     * @param max
     * @return 
     */
    <E extends BaseEntity> List<E> namedQuery(String name, Map<String, Object> params, Integer max);

    /**
     * Perform an hql direct query.
     * 
     * @param <E>
     * @param hql
     * @param params
     * @return 
     */
    <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params);

    /**
     * Perform an hql direct query.
     * 
     * @param <E>
     * @param hql
     * @param params
     * @param rows
     * @return 
     */
    <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params, Integer rows);

    /**
     * Perform an hql direct query.
     * 
     * @param <E>
     * @param hql
     * @param params
     * @param first
     * @param rows
     * @return 
     */
    <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params, Integer first, Integer rows);

    /**
     * Retrieves a list of queries through a native query.
     * 
     * @param <E>
     * @param type
     * @param name
     * @param params
     * @param max
     * @return 
     */
    <E> List<E> nativeQuery(Class<E> type, String name, Map<String, Object> params, Integer max);

    /**
     * Retrieves a list of queries through a native query.
     * 
     * @param <E>
     * @param sql
     * @param params
     * @return 
     */
    <E> List<E> nativeQuery(String sql, Map<String, Object> params);

    /**
     * Flush the current transaction. This will make the persist take immediate
     * effect when using it. Basically forcing the db to do the operation.
     */
    void flushEm();
    
    /**
     * gets the current transaction
     * @return 
     */
    EntityTransaction getTransaction();

    /**
     * Gets a list of primitives instead of inheritance
     * 
     * @param name
     * @return
     */
    List namedQuery(String name, Class returnType);

    /**
     * Fetches a limited named query list based in the provided max value
     * 
     * @param name
     * @param max
     * @return
     */
	List namedQuery(String name, Integer max);
    
}
