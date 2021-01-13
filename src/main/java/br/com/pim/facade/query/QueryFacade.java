/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.query;

import br.com.pim.model.BaseEntity;
import java.util.List;
import java.util.Map;


public interface QueryFacade {
    
    /**
     * Retrieves a list based in the given query with parameters. This query will be
     * used in the crud screen in most of the cases.
     * 
     * @param <E>
     * @param hql
     * @param params
     * @return 
     */
    <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params);

    /**
     * Retrieves a list based in the given query with parameters specifying the amount
     * of rows. This query will be used in the crud screen in most of the cases.
     * 
     * @param <E>
     * @param hql
     * @param params
     * @param rows
     * @return 
     */
    <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params, Integer rows);

    /**
     * Retrieves a list based in the given query with parameters specifying the amount
     * of rows and the starting point. This query will be used in the crud 
     * screen in most of the cases.
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
     * Retrieves a single result based in a named query with parameters.
     * 
     * @param <E>
     * @param name
     * @param params
     * @return 
     */
    <E> E singleResultNamedQuery(String name, Map<String, Object> params);
    
    /**
     * Retrieves a list of objects based in a named query with no parameters.
     * 
     * @param <E>
     * @param name
     * @return 
     */
    <E extends BaseEntity> List<E> namedQueryNoParams(String name);
    
    /**
     * Retrieves a list of object based in a named query with parameters
     * 
     * @param <E>
     * @param name
     * @param params
     * @return 
     */
    <E extends BaseEntity> List<E> namedQuery(String name, Map<String, Object> params);
    
}
