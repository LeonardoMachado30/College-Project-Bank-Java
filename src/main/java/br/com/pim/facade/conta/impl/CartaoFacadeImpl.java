/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.conta.impl;

import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.EntityFacade;
import br.com.pim.facade.conta.CartaoFacade;
import br.com.pim.model.common.Banco;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioCartoesCadastrados;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CartaoFacadeImpl extends EntityFacade implements CartaoFacade {

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
    public List<UsuarioCartoesCadastrados> getAllCartoesCadastrados(Usuario currentUser) {
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", currentUser);
        
        return getGenericDao().namedQuery("CartoesCadastrados.findAll", params);
    }

    @Override
    public List<Banco> getBancoList() {
        return getGenericDao().namedQuery("Banco.findAll");
    }

    @Override
    public void deleteCartaoCadastrado(long id) {
        UsuarioCartoesCadastrados cartao = getGenericDao().find(UsuarioCartoesCadastrados.class, id);
        getGenericDao().remove(cartao);
    }

    @Override
    public UsuarioCartoesCadastrados createUpdate(UsuarioCartoesCadastrados cartao) {
        return getGenericDao().merge(cartao);
    }
    
    
    
}
