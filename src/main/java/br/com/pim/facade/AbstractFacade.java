/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade;

import br.com.pim.dao.impl.GenericDAO;
import javax.persistence.EntityManager;


public class AbstractFacade {

    private GenericDAO genericDao;

    public void setGenericDao(EntityManager entityManager) {
        genericDao = new GenericDAO(entityManager);
    }

    public GenericDAO getGenericDao() {
        if (genericDao == null) {
            throw new IllegalStateException("Entity manager is not available.");
        }
        return genericDao;
    }

    public void flush() {
        getGenericDao().flushEm();
    }

}
