/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.contrato.impl;

import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.EntityFacade;
import br.com.pim.facade.contrato.ContratoFacade;
import br.com.pim.model.common.TipoContrato;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioContrato;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ContratoFacadeImpl extends EntityFacade implements ContratoFacade {

    /**
     * Persistence context
     */
    @PersistenceContext(unitName = "PimPU")
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
        if (genericDao == null) {
            genericDao = new GenericDAO(emanager);
        }
        return genericDao;
    }

    @Override
    public List<UsuarioContrato> getAllContratosCadastrados(Usuario currentUser) {
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", currentUser);
        
        return getGenericDao().namedQuery("Contratos.findAll", params);
    }

    @Override
    public List<TipoContrato> getAllTipoContrato() {
        return getGenericDao().namedQuery("TipoContrato.findAll");
    }

    @Override
    public UsuarioContrato createUpdate(UsuarioContrato contrato) {
        return getGenericDao().merge(contrato);
    }

    @Override
    public void deleteContrato(long id) {
        UsuarioContrato contrato = getGenericDao().find(UsuarioContrato.class, id);
        getGenericDao().remove(contrato);
    }
    
}
