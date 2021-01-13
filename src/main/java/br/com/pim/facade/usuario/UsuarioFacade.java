/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.facade.usuario;

import br.com.pim.model.common.Banco;
import br.com.pim.model.common.TipoContrato;
import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.investimento.Operacao;
import br.com.pim.model.user.Usuario;
import br.com.pim.model.user.UsuarioCartoesCadastrados;
import br.com.pim.model.user.UsuarioContrato;
import java.util.List;


public interface UsuarioFacade {
    
    /**
     * Authenticate the user through native query.
     * 
     * @param cpf
     * @param password
     * @return 
     */
    Usuario login(String cpf, String password);
    
    /**
     * Register a new user
     * 
     * @param cpf
     * @param nome
     * @param email
     * @param senha
     * @return 
     */
    Usuario cadastrar(String cpf, String nome, String email, String senha);
    
    /**
     * Update user
     * 
     * @param usuario
     * @return 
     */
    Usuario update(Usuario usuario);

    List<Banco> getAllBancos();

    List<Operacao> getOperacoes();
    
    List<TipoCriptoativo> getAllTipoCriptoativo();
    
    List<TipoContrato> getAllTipoContrato();
    
    List<TipoOperacao> getAllTipoOperacao();

    List<UsuarioCartoesCadastrados> getAllCartoesCadastrados(Usuario currentUser);

    List<UsuarioContrato> getAllContratosCadastrados(Usuario currentUser);

    void createOperacao(Operacao operacao);

    List<Operacao> getAllOperacoesCadastrados(Usuario currentUser);
    
}
