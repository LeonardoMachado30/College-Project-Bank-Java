/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.crud;

import br.com.pim.facade.operacao.OperacaoFacade;
import br.com.pim.facade.usuario.UsuarioFacade;
import br.com.pim.model.common.TipoCriptoativo;
import br.com.pim.model.common.TipoOperacao;
import br.com.pim.model.investimento.Operacao;
import br.com.pim.web.controller.authentication.LoginController;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class OperacaoController {
    
    @EJB
    private OperacaoFacade operacaoFacade;

    @EJB
    private UsuarioFacade userFacade;
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    
    // operacao
    private String descricao;
    private Double valor;
    private TipoOperacao tipoOperacao;
    private TipoCriptoativo tipoCriptoativo;
    private Integer opcaoSelecionada;
    
    public void adicionaOperacao() throws ParseException, IOException {        
        Operacao operacao = new Operacao();
        operacao.setDescricao(descricao);
        
        tipoOperacao = operacaoFacade.getAllTipoOperacao().stream()
                .filter(op -> opcaoSelecionada.equals(op.getId().intValue()))
                .findAny()
                .orElse(null);
        
        operacao.setTipoCriptoativo(tipoCriptoativo);
        operacao.setTipoOperacao(tipoOperacao);
        operacao.setUsuario(loginController.getCurrentUser());
        operacao.setValor(valor);
        
        if (tipoOperacao.getId().equals(1l)) {
            loginController.getCurrentUser().setSaldoCarteira(loginController.getCurrentUser().getSaldoCarteira() + valor);
        } else {
            loginController.getCurrentUser().setSaldoCarteira(loginController.getCurrentUser().getSaldoCarteira() - valor);
        }
        
        operacaoFacade.createOperacao(operacao);
        userFacade.update(loginController.getCurrentUser());
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/p/investments?faces-redirect=true&add=true");
    }
    
    public List<Operacao> getOperacoesCadastrados(){
        return operacaoFacade.getAllOperacoesCadastrados(loginController.getCurrentUser());
    }
    
    public List<TipoCriptoativo> getTipoCriptoativoList() {
        return operacaoFacade.getAllTipoCriptoativo();
    }
    
    // Getters & Setters

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public TipoCriptoativo getTipoCriptoativo() {
        return tipoCriptoativo;
    }

    public void setTipoCriptoativo(TipoCriptoativo tipoCriptoativo) {
        this.tipoCriptoativo = tipoCriptoativo;
    }

    public Integer getOpcaoSelecionada() {
        return opcaoSelecionada;
    }

    public void setOpcaoSelecionada(Integer opcaoSelecionada) {
        this.opcaoSelecionada = opcaoSelecionada;
    }
    
}
