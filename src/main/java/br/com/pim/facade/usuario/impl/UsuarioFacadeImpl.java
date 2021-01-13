/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.usuario.impl;

import br.com.pim.common.SHA512Util;
import br.com.pim.dao.impl.GenericDAO;
import br.com.pim.facade.EntityFacade;
import br.com.pim.facade.usuario.UsuarioFacade;
import br.com.pim.model.common.Banco;
import br.com.pim.model.common.TipoContrato;
import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.investimento.Operacao;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioCartoesCadastrados;
import br.com.pim.model.user.UsuarioContrato;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class UsuarioFacadeImpl extends EntityFacade implements UsuarioFacade {

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
    public Usuario login(String cpf, String password) {
        // Define parameters
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, password);
        params.put(2, Long.parseLong(cpf.replaceAll("[^\\d]", "")));

        // perform query
        List<Usuario> userList = getGenericDao().nativeNamedQuery("Usuario.login", params, Usuario.class);

        return !userList.isEmpty() ? (Usuario) userList.get(0) : null;
    }

    @Override
    public Usuario cadastrar(String cpf, String nome, String email, String senha) {      
        Usuario novo = new Usuario();
        novo.setCpf(Long.parseLong(cpf.replaceAll("[^\\d]", "")));
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(SHA512Util.get_SHA_512_SecurePassword(senha, ""));
        novo.setSaldoCarteira(50.0);
        
        novo = emanager.merge(novo);
        
        Operacao operacao = new Operacao();
        operacao.setDescricao("Depósito em carteira");
        operacao.setUsuario(novo);
        operacao.setValor(50.0);
        
        TipoOperacao tipoOperacao = emanager.find(TipoOperacao.class, 1L);
        operacao.setTipoOperacao(tipoOperacao);
        
        novo.getOperacaoList().add(operacao);
        emanager.merge(novo);
        
        return novo;
    }

    @Override
    public Usuario update(Usuario usuario) {
        return emanager.merge(usuario);
    }

    @Override
    public List<Banco> getAllBancos() {
        return emanager.createNamedQuery("Banco.findAll").getResultList();
    }

    @Override
    public List<UsuarioCartoesCadastrados> getAllCartoesCadastrados(Usuario currentUser) {
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", currentUser);
        
        return getGenericDao().namedQuery("CartoesCadastrados.findAll", params);
    }

    @Override
    public List<UsuarioContrato> getAllContratosCadastrados(Usuario currentUser) {
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", currentUser);
        
        return getGenericDao().namedQuery("Contratos.findAll", params);
    }

    @Override
    public List<TipoCriptoativo> getAllTipoCriptoativo() {
        return emanager.createNamedQuery("TipoCriptoativo.findAll").getResultList();
    }

    @Override
    public List<TipoContrato> getAllTipoContrato() {
        return emanager.createNamedQuery("TipoContrato.findAll").getResultList();
    }

    @Override
    public List<Operacao> getOperacoes() {
        return emanager.createNamedQuery("Operacao.findAll").getResultList();
    }

    @Override
    public List<TipoOperacao> getAllTipoOperacao() {
        return emanager.createNamedQuery("TipoOperacao.findAll").getResultList();
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
