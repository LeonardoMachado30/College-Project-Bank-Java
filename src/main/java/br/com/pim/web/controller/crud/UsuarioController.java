/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.crud;

import br.com.pim.facade.usuario.UsuarioFacade;
import br.com.pim.model.user.Usuario;
import br.com.pim.web.controller.CrudController;
import br.com.pim.web.controller.authentication.LoginController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;


@ManagedBean
@ViewScoped
public class UsuarioController extends CrudController<Usuario> {

    private static final Logger LOG = Logger.getLogger(UsuarioController.class);
    
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter
    
    @EJB
    private UsuarioFacade usuarioFacade;

    @Override
    protected void setup() {
    }

    @Override
    protected String buildQuery() {
        return null;
    }

    @Override
    public void update() {
        this.instance = loginController.getCurrentUser();
        super.update(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
