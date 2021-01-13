/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.dao.impl;

import br.com.pim.dao.EntityAccessObject;
import br.com.pim.model.BaseEntity;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class EntityDAO  implements Serializable, EntityAccessObject {

    protected EntityManager emanager;

    public EntityDAO(EntityManager emanager) {
        this.emanager = emanager;
    }

    @Override
    public <E extends BaseEntity> E find(Class<E> clazz, Serializable id) {
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