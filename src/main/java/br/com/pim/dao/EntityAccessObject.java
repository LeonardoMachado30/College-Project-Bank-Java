/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.dao;

import br.com.pim.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.EntityTransaction;


public interface EntityAccessObject {

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
     * Flush the current transaction. This will make the persist take immediate
     * effect when using it. Basically forcing the db to do the operation.
     */
    void flushEm();
    
    /**
     * gets the current transaction
     * @return 
     */
    EntityTransaction getTransaction();
}
