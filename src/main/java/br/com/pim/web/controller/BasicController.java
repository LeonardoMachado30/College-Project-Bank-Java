/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;


public abstract class BasicController implements Serializable {

    private static final long serialVersionUID = -615301608942885315L;

    // Log support
    private static final Logger LOG = Logger.getLogger(BasicController.class);

    /**
     * Adds an <b>INFO</b> message to the view through facesContext.
     *
     * @param clientId
     * @param message
     */
    public void info(String clientId, String message) {
        createMessage(clientId, FacesMessage.SEVERITY_INFO, message);
    }

    /**
     * Adds a <b>WARN</b> message to the view through facesContext.
     *
     * @param clientId
     * @param message
     */
    public void warn(String clientId, String message) {
        createMessage(clientId, FacesMessage.SEVERITY_WARN, message);
    }

    /**
     * Adds a <b>ERROR</b> message to the view through facesContext.
     *
     * @param clientId
     * @param message
     */
    public void error(String clientId, String message) {
        createMessage(clientId, FacesMessage.SEVERITY_ERROR, message);
    }

    /**
     * Adds a <b>FATAL</b> message to the view through facesContext.
     *
     * @param clientId
     * @param message
     */
    public void fatal(String clientId, String message) {
        createMessage(clientId, FacesMessage.SEVERITY_FATAL, message);
    }

    /**
     * ClientId is the form which will have the message rendered.
     *
     * @param clientId
     * @param severity
     * @param message
     */
    public void createMessage(String clientId, FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(severity, message, null));
    }

    /**
     * When redirecting from managedBean, use this method
     *
     * @param url
     */
    public void redirect(String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + url);
        } catch (IOException ex) {
            LOG.error("Unable to redirect to login page", ex);
        }
    }
}
