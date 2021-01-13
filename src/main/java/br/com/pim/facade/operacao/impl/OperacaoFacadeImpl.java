/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.operacao.impl;

import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.EntityFacade;
import br.com.pim.facade.operacao.OperacaoFacade;
import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.investimento.Operacao;
import br.com.pim.model.user.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class OperacaoFacadeImpl extends EntityFacade implements OperacaoFacade {

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
    public List<TipoCriptoativo> getAllTipoCriptoativo() {
        return getGenericDao().namedQuery("TipoCriptoativo.findAll");
    }

    @Override
    public List<Operacao> getOperacoes() {
        return getGenericDao().namedQuery("Operacao.findAll");
    }

    @Override
    public List<TipoOperacao> getAllTipoOperacao() {
        return getGenericDao().namedQuery("TipoOperacao.findAll");
    }

    @Override
    public void createOperacao(Operacao operacao) {
        getGenericDao().persist(operacao);
    }

    @Override
    public List<Operacao> getAllOperacoesCadastrados(Usuario currentUser) {
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", currentUser);
        
        return getGenericDao().namedQuery("OperacoesCadastradas.findAll", params);
    }
}
