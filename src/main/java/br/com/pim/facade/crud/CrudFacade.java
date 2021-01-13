/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.crud;

import br.com.pim.model.BaseEntity;
import java.io.Serializable;
import java.util.List;

public interface CrudFacade {

    /**
     * Fetches the object by ID for the specified class.
     * 
     * @param <E>
     * @param clazz
     * @param id
     * @return 
     */
    <E extends BaseEntity> E find(Class<E> clazz, Serializable id);
    
    /**
     * Persists a new object and performs a flush.
     * 
     * @param <E>
     * @param obj 
     */
    <E extends BaseEntity> void persist(E obj);
    
    /**
     * Performs the update of a provided object.
     * 
     * @param <E>
     * @param obj
     * @return 
     */
    <E extends BaseEntity> E merge(E obj);
    
    /**
     * Removes an object.
     * 
     * @param <E>
     * @param obj 
     */
    <E extends BaseEntity> void remove(E obj);
    
    /**
     * Removes a list of provided objects.
     * 
     * @param <E>
     * @param list 
     */
    <E extends BaseEntity> void remove(List<E> list);
    
    /**
     * Performs an update in a list of provided objects.
     * 
     * @param <E>
     * @param list 
     */
    <E extends BaseEntity> void merge(List<E> list);
    
}