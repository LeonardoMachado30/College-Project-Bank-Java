/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.crud;

import br.com.pim.facade.contrato.ContratoFacade;
import br.com.pim.model.common.TipoContrato;
import br.com.pim.model.user.UsuarioContrato;
import br.com.pim.web.controller.authentication.LoginController;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class ContratoController {
    
    @EJB
    private ContratoFacade contratoFacade;
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    
    // Propriedades
    private TipoContrato contrato;
        
    /**
     * Cadastra contrato para o usuário logado no momento.
     * 
     * @throws ParseException
     * @throws IOException 
     */
    public void adicionaContrato() throws ParseException, IOException {        
        UsuarioContrato uc = new UsuarioContrato();
        uc.setTipoContrato(contrato);
        uc.setInclusao(new Date());
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        
        uc.setEncerramento(c.getTime());
        uc.setUsuario(loginController.getCurrentUser());
        contratoFacade.createUpdate(uc);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/p/profile?faces-redirect=true&contrato=true");
    }
    
    /**
     * Remove contrato com o ID passado.
     * 
     * @param id
     * @throws IOException 
     */
    public void removerContrato(long id) throws IOException {
        contratoFacade.deleteContrato(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/p/profile?faces-redirect=true&contratoRemovido=true");
    }
    
    /**
     * Retorna lista de contratos cadastrados pelo usuário
     * 
     * @return 
     */
    public List<UsuarioContrato> getContratosCadastrados() {
        return contratoFacade.getAllContratosCadastrados(loginController.getCurrentUser());
    }
    
    /**
     * Retorna lista de tipos de contratos cadastrados no sistema
     * 
     * @return 
     */
    public List<TipoContrato> getTipoContrato() {
        return contratoFacade.getAllTipoContrato();
    }   
    
    // Getter&Setters - Nada a fazer

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public TipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(TipoContrato contrato) {
        this.contrato = contrato;
    }
}
