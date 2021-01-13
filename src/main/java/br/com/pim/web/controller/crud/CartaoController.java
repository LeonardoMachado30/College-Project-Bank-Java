/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.crud;

import br.com.pim.facade.conta.CartaoFacade;
import br.com.pim.model.common.Banco;
import br.com.pim.model.user.UsuarioCartoesCadastrados;
import br.com.pim.web.controller.authentication.LoginController;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ViewScoped
@ManagedBean
public class CartaoController {
    
    @EJB
    private CartaoFacade cartaoFacade;
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    
    // Propriedades
    private String agencia;
    private String conta;
    private String expiracao;
    private String nomeConta;
    private Banco banco;

    /**
     * Retorna lista de bancos cadastrados
     * 
     * @return 
     */
    public List<Banco> getBancoList() {
        return cartaoFacade.getBancoList();
    }
    
    /**
     * Retorna lista de cartões cadastrados por um usuário
     * 
     * @return 
     */
    public List<UsuarioCartoesCadastrados> getCartoesCadastrados() {
        return cartaoFacade.getAllCartoesCadastrados(loginController.getCurrentUser());
    }
    
    /**
     * Remove cartão com o ID passado.
     * 
     * @param id
     * @throws IOException 
     */
    public void removerCartao(long id) throws IOException {
        cartaoFacade.deleteCartaoCadastrado(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/p/profile?faces-redirect=true&cartaoRemovido=true");
    }
    
    /**
     * Cadastra cartão para o usuário logado no momento.
     * 
     * @throws ParseException
     * @throws IOException 
     */
    public void adicionaCartao() throws ParseException, IOException {
        UsuarioCartoesCadastrados ucc = new UsuarioCartoesCadastrados();
        ucc.setAgencia(Long.parseLong(agencia));
        ucc.setBanco(banco);
        ucc.setConta(Long.parseLong(conta));
        ucc.setNome(nomeConta);
        ucc.setExpiracao(new SimpleDateFormat("MM/yyyy").parse(expiracao));
        ucc.setUsuario(loginController.getCurrentUser());
        cartaoFacade.createUpdate(ucc);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/p/profile?faces-redirect=true&cartaoCriado=true");
    }
    
    // Getter&Setters - Nada a fazer

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(String expiracao) {
        this.expiracao = expiracao;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
}
