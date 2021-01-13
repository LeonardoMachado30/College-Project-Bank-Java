/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller;

import br.com.pim.common.StringUtil;
import br.com.pim.facade.crud.CrudFacade;
import br.com.pim.facade.query.QueryFacade;
import br.com.pim.model.BaseEntity;
import br.com.pim.web.controller.authentication.LoginController;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;


public abstract class AbstractQueryController<E extends BaseEntity>
        extends BasicController implements Serializable {

    // Log support
    private static final Logger LOG = Logger.getLogger(AbstractQueryController.class);

    // EntityFacade that will be used by upper levels
    @EJB
    protected CrudFacade facade;

    // QueryFacade that will be used for crud queries
    @EJB
    protected QueryFacade queryFacade;

    // To fetch the current user in the system
    @ManagedProperty(value = "#{loginController}")
    protected LoginController authenticator;

    // All cruds must implement this, will be the search query with filters + setup for parameters
    protected abstract String buildQuery();

    protected abstract void setup();

    // Lists will be managed by netbeans table, so, we can keep it in a simple list
    protected Map<String, Object> parameters = new LinkedHashMap<>();
    protected List<E> selection;
    protected List<E> results;

    // By default, don't show the results, let's keep the screen clean and tidy
    private boolean showResults = false;

    public void doQuery() {
        try {
            // Clear parameters
            parameters.clear();

            // Clear selection before any search
            selection = new LinkedList<>();

            // Capture parameters' values
            setup();

            // Escape strings
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (entry.getValue() instanceof String) {
                    parameters.put(entry.getKey(), StringUtil.escapeSql(entry.getValue().toString()));
                }

            }

            // Perform the main query
            results = queryFacade.query(StringUtil.escapeSql(buildQuery()), parameters);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        // Empty data model if required
        if (results == null) {
            results = Collections.emptyList();
        }
    }

    /**
     * Filter responsible for clearing the results and making it visible.
     */
    public void filter() {
        // perform the search
        doQuery();

        // set show results to true so the user can see it
        showResults = true;
    }

    /**
     * The crud template must be divided in 2 forms. 1st. Search form, which
     * will be with id searchForm (obviously) 2nd. Crud form, which name will be
     * crudForm (keep it simple)
     *
     * @return
     */
    protected String getSearchFormClientId() {
        return "searchForm";
    }

    // getters and setters... nothing to do here
    public boolean isShowResults() {
        return showResults;
    }

    public void setShowResults(boolean showResults) {
        this.showResults = showResults;
    }

    public LoginController getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(LoginController authenticator) {
        this.authenticator = authenticator;
    }

    public List<E> getSelection() {
        return selection;
    }

    public void setSelection(List<E> selection) {
        this.selection = selection;
    }

    public List<E> getResults() {
        return results;
    }

    public void setResults(List<E> results) {
        this.results = results;
    }
}
