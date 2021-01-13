/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.query.impl;

import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.query.QueryFacade;
import br.com.pim.model.BaseEntity;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class QueryFacadeImpl implements QueryFacade {
    
    /**
     * Persistence context
     */
    @PersistenceContext( unitName = "PimPU")
    private EntityManager emanager;
    
    /**
     * Data access object
     */
    private GenericDAO genericDao;

    /**
     * Instantiate it in case that the object doesn't exists
     * 
     * @return 
     */
    public GenericDAO getGenericDao() {
        if (genericDao == null){
            genericDao = new GenericDAO(emanager);
        }
        return genericDao;
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params) {
        return getGenericDao().query(hql, params);
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params, Integer rows) {
        return getGenericDao().query(hql, params, rows);
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql, Map<String, Object> params, Integer first, Integer rows) {
        return getGenericDao().query(hql, params, first, rows);
    }

    @Override
    public <E> E singleResultNamedQuery(String name, Map<String, Object> params) {
        return getGenericDao().singleResultNamedQuery(name, params);
    }

    @Override
    public <E extends BaseEntity> List<E> namedQueryNoParams(String name) {
        return getGenericDao().namedQuery(name);
    }

    @Override
    public <E extends BaseEntity> List<E> namedQuery(String name, Map<String, Object> params) {
        return getGenericDao().namedQuery(name, params);                
    }
    
}