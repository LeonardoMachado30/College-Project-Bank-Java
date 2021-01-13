/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.authentication;

import br.com.pim.facade.usuario.UsuarioFacade;
import br.com.pim.model.user.Usuario;
import br.com.pim.web.controller.BasicController;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

/**
 *
 * @author amoraes
 */
@ManagedBean
@SessionScoped
public class LoginController extends BasicController implements Serializable {

    private static final long serialVersionUID = 7765876811740798583L;
    private static final Logger LOG = Logger.getLogger(LoginController.class);

    // This will be the variable where we'll be fetching EVERYTHING from the user
    private Usuario currentUser;

    // Login parameters
    private String cpf;
    private String nome;
    private String email;
    private String nascimento;
    private String password;
    private boolean checkbox = false;

    // Define password
    private String senha;
    private String confirmaSenha;

    // status
    private boolean loggedIn;

    @EJB
    private UsuarioFacade userFacade;

    /**
     * Login operation.
     *
     * @return
     */
    public String doLogin() {
        Usuario toLogin = userFacade.login(cpf, senha);

        // fetches the URL query in the hidden field
        String urlQuery = null;
        Map<String, String> paramMap = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();

        if (paramMap.containsKey("urlQuery")) {
            urlQuery = paramMap.get("urlQuery");
        }

        // Successful login
        if (toLogin != null) {
            // Search for the user since we know that is existent
            currentUser = toLogin;
            loggedIn = true;

            // perform the redirect accordingly to the param
            if (StringUtils.isBlank(urlQuery)) {
                return "/p/dashboard?faces-redirect=true";
            }
        }

        // Log error with the current user
        LOG.error("Invalid credentials for cpf: " + cpf);
        error(null, "Credenciais inválidas ou inexistente.");

        return "";
    }

    public void cadastrarUsuario() throws IOException {
        if (!checkbox) {
            error(null, "É necessário aceitar os termos de uso");;
            return;
        } else if (!senha.equals(confirmaSenha)) {
            error(null, "Senhas não coincidem. Tente novamente.");;
            return;
        } else if (senha.length() < 8) {
            error(null, "Senha deve conter no mínimo 8 caracteres.");
            return;
        } else if (senha.equalsIgnoreCase(cpf)) {
            error(null, "Senha não pode ser igual ao CPF");
            return;
        }

        try {
            userFacade.cadastrar(cpf, nome, email, senha);
        } catch (Exception e) {
            System.out.println("CPF já cadastrado");
            error(null, "CPF já cadastrado.");
            return;
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pim/index?faces-redirect=true&c=true");
    }
    
    public void atualizaCadastro() throws ParseException {
        if (nascimento != null) {
            currentUser.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));
        }
        userFacade.update(currentUser);
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        currentUser = null;
        loggedIn = false;

        return "/index?faces-redirect=true";
    }

    // Getters & Setters... nothing to do here

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
