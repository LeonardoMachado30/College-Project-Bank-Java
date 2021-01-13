/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.pim.dao.DataAccessObject;
import br.com.pim.model.BaseEntity;


public class GenericDAO implements Serializable, DataAccessObject {

    private static final long serialVersionUID = 1L;
    private static final int MAX_RESULT_SET_SIZE = 10000;
    private static final int AVG_FETCH_SIZE = 10;
    private static final int LOCK_TIMEOUT = 600000;
    protected EntityManager emanager;

    public GenericDAO(EntityManager emanager) {
        this.emanager = emanager;
    }

    @Override
    public <E extends BaseEntity> E find(Class<E> clazz, Serializable id) {
        emanager.getEntityManagerFactory().getCache().evictAll();
        return emanager.find(clazz, id);
    }

    @Override
    public <E extends BaseEntity> E persist(E obj) {
        emanager.persist(obj);
        return obj;
    }

    @Override
    public <E extends BaseEntity> E update(E obj) {
        return emanager.merge(obj);
    }

    @Override
    public <E extends BaseEntity> E remove(E obj) {
        E toRemove = emanager.merge(obj);
        emanager.remove(toRemove);
        return toRemove;
    }

    @Override
    public <E extends BaseEntity> E merge(E obj) {
        obj = emanager.merge(obj);
        return obj;
    }

    @Override
    public <E> E singleResultNamedQuery(String name, Map<String, Object> params) {

        Query query = emanager.createNamedQuery(name);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);

        return (E) query.getSingleResult();
    }

    @Override
    public List namedQuery(String name, Class returnType) {
    	TypedQuery<Integer> query =
    		      emanager.createNamedQuery(name, returnType);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        return query.getResultList();
    }

    @Override
    public List namedQuery(String name, Integer max) {
        Query query = emanager.createNamedQuery(name);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(max != null && max > 0 ? max : MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> namedQuery(String name) {
        Query query = emanager.createNamedQuery(name);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public List namedQueryGenericType(String name) {
        Query query = emanager.createNamedQuery(name);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> namedQuery(String name,
            Map<String, Object> params) {
        Query query = emanager.createNamedQuery(name);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> namedQuery(String name,
            Map<String, Object> params, Integer first, Integer rows) {
        Query query = emanager.createNamedQuery(name);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        query.setFirstResult(first);
        query.setMaxResults(rows);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> namedQuery(String name,
            Map<String, Object> params, Integer max) {
        Query query = emanager.createNamedQuery(name);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", max < AVG_FETCH_SIZE ? max : AVG_FETCH_SIZE);

        // Max results cap
        query.setMaxResults(max > 0 ? max : DEFAULT_MAX_RESULTS);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> nativeNamedQuery(String name, Map<Integer, Object> params, Class<E> clazz) {
        Query query = emanager.createNamedQuery(name, clazz);
        if (params != null) {
            for (Integer param : params.keySet()) {
                query.setParameter(param.intValue(), params.get(param));
            }
        }
        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> nativeNamedQuery(String name, Map<Integer, Object> params, Class<E> clazz, Integer max) {
        Query query = emanager.createNamedQuery(name, clazz);
        if (params != null) {
            for (Integer param : params.keySet()) {
                query.setParameter(param.intValue(), params.get(param));
            }
        }
        
        if (max != null) {
            query.setMaxResults(max > 0 ? max : DEFAULT_MAX_RESULTS);
        }
        
        return query.getResultList();
    }

    @Override
    public <E> List<E> nativeQuery(Class<E> type, String name,
            Map<String, Object> params, Integer max) {
        Query query;
        if (type == null) {
            query = emanager.createNativeQuery(name);
        } else {
            query = emanager.createNativeQuery(name, type);
        }
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(Integer.parseInt(param), params.get(param));
            }
        }
        if (max != null) {
            query.setMaxResults(max > 0 ? max : DEFAULT_MAX_RESULTS);
        }

        return query.getResultList();
    }

    @Override
    public <E> List<E> nativeQuery(String sql, Map<String, Object> params) {
        Query query = emanager.createNativeQuery(sql);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql,
            Map<String, Object> params) {
        emanager.getEntityManagerFactory().getCache().evictAll();
        
        Query query = emanager.createQuery(hql);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        // Maximum cap
        query.setMaxResults(MAX_RESULT_SET_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql,
            Map<String, Object> params, Integer rows) {
        Query query = emanager.createQuery(hql);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }
        query.setMaxResults(rows);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", rows < AVG_FETCH_SIZE ? rows : AVG_FETCH_SIZE);

        return query.getResultList();
    }

    @Override
    public <E extends BaseEntity> List<E> query(String hql,
            Map<String, Object> params, Integer first, Integer rows) {
        Query query = emanager.createQuery(hql);
        if (params != null) {
            for (String param : params.keySet()) {
                query.setParameter(param, params.get(param));
            }
        }
        query.setFirstResult(first);
        query.setMaxResults(rows);

        // Performance hints
        query.setHint("javax.persistence.lock.timeout", LOCK_TIMEOUT);
        query.setHint("eclipselink.jdbc.fetch-size", AVG_FETCH_SIZE);

        return query.getResultList();
    }

    public int update(String stm, Map<String, Object> params) {
        if (stm != null && !stm.trim().isEmpty()) {
            Query q = emanager.createQuery(stm);

            if (params != null) {
                for (String param : params.keySet()) {
                    q.setParameter(param, params.get(param));
                }
            }
            return q.executeUpdate();
        }

        return -1;
    }

    @Override
    public void flushEm() {
        emanager.flush();
    }

    @Override
    public EntityTransaction getTransaction() {
        return emanager.getTransaction();
    }
}
