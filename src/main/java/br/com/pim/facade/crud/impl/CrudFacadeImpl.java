/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.crud.impl;

import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.EntityFacade;
import br.com.pim.facade.crud.CrudFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CrudFacadeImpl extends EntityFacade implements CrudFacade {
    
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
    @Override
    public GenericDAO getGenericDao() {
        if (genericDao == null){
            genericDao = new GenericDAO(emanager);
        }
        return genericDao;
    }
}
