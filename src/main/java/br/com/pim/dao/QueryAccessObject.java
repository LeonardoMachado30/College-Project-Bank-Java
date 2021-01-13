/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.dao;

import br.com.pim.model.BaseEntity;
import java.util.List;
import java.util.Map;


public interface QueryAccessObject {

    public static final int DEFAULT_MAX_RESULTS = 10;

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
     * @return 
     */
    <E extends BaseEntity> List<E> nativeNamedQuery(String name, Map<Integer, Object> params);

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
}
