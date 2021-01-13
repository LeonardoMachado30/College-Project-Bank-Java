/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller;

import br.com.pim.model.BaseEntity;
import br.com.pim.model.basic.UidBasicEntity;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;


public abstract class CrudController<E extends BaseEntity>
        extends AbstractQueryController<E> implements Serializable {

    private static final long serialVersionUID = -5887756772094205026L;

    // Log support     
    private static final Logger LOG = Logger.getLogger(CrudController.class);

    // Properties for maintenance
    protected E instance;
    protected Serializable id;
    protected Class<E> entityClass;
    
    // custom save redirect page
    private String savePageRedirect;

    /**
     * Save the object. This can only happen if the object it not saved in the
     * database nor being edited.
     *
     * @throws Exception
     */
    public void save() throws Exception {
        if (((UidBasicEntity) instance).getId() == null) {
            try {
                
                // persist it
                facade.persist(instance);

                // inform the user about the action
                info(null, "Registro salvo com sucesso");

                // create a new instance so this will be out of current scope
                createInstance();
                
                FacesContext.getCurrentInstance().getExternalContext().redirect(savePageRedirect == null ? "search?created=true" : savePageRedirect);                
            } catch (Exception e) {
                // log the exception and reinit the instance
                LOG.error("Error saving record", e);

                // inform the user
                error(getFormClientId(), "Não foi possível salvar o registro");

                // reinit
                initInstance();

                throw new Exception();
            }
            refresh();
        }
    }

    /**
     * Update can only happens if the object is is not null, which in other
     * words means that it is being edited.
     */
    public void update() {
        if (((UidBasicEntity) instance).getId() != null) {
            try {
                // update the object
                instance = facade.merge(instance);

                // inform the user
                info(null, "Registro atualizado com sucesso");

                FacesContext.getCurrentInstance().getExternalContext().redirect("search?updated=true");
            } catch (Exception e) {
                // log the exception and reinit the instance
                LOG.error("Error updating record: ", e);

                // inform the user
                error(getFormClientId(), "Não foi possível atualizar o registro");

                // reinit
                initInstance();
            }
        }
    }

    /**
     * Delete current object. This can only be done if the object is not null,
     * which means that it is being edited.
     */
    public void delete() {
        if (((UidBasicEntity) instance).getId() != null) {
            try {
                // merge it before so we will have the most updated object
                instance = facade.merge(instance);

                // remove it
                facade.remove(instance);

                // set id to null so we can initialize the instance property
                id = null;

                // init the instance now that the ID is null
                initInstance();

                // inform the user that the operation was susccessfully done
                info(null, "Registro removido com sucesso");
                FacesContext.getCurrentInstance().getExternalContext().redirect("search?removed=true");
            } catch (Exception ex) {
                // log the exception
                LOG.error("Error deleting record", ex);

                // inform the user
                error(getFormClientId(), "Não foi possível remover o registro");
            }
        }
    }
    
    /**
     * Remove all selected rows in the results table
     */
    public void deleteSelected(){
        if (getSelection().size() > 0){
            try {
                // Iterate over all results and delete
                for (E obj : getSelection()){
                    // grab it temporarily and update to avoid conflict
                    E toRemove = facade.merge(obj);
                    
                    // remove it
                    facade.remove(toRemove);
                }

                // inform the user that the operation was susccessfully done
                info(null, "Registros removidos com sucesso");
            } catch (Exception ex) {
                // log the exception
                LOG.error("Error deleting several records", ex);
                
                // inform the user
                error(getFormClientId(), "Não foi possível remover os registros selecionados");
            }
            
            // refresh the results in the datatable
            refresh();
        } else {
        	error(getFormClientId(), "Registro inexistente ou já removido");
        }
    }

    /**
     * Get the object checking all subclasses.
     *
     * @param obj
     * @param property
     * @return
     */
    private Object getValue(Object obj, String property) {
        Class t = obj.getClass();
        Object found = null;
        Field field;

        while (t != null) {
            try {
                field = t.getDeclaredField(property);
                field.setAccessible(true);
                found = field.get(obj);
                break;
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
                t = t.getSuperclass();
            }
        }
        return found;
    }

    private Object getValueFromComposed(Object obj, String property) {
        List<String> temp = Arrays.asList(property.split(":"));

        for (String t : temp) {
            obj = getValue(obj, t);

            // property is null or doesn't have a value
            // set it to empty and break the loop
            if (obj == null) {
                obj = "";
                break;
            }
        }

        return obj;
    }

    /**
     * After action, refresh it.
     */
    public void refresh() {
        filter();
    }

    /**
     * Available method to be overridden and insert some code when close dialog
     * is performed
     */
    public void onCloseAction() {
    }

    /**
     * Get the entity class for the current object.
     *
     * @return
     */
    protected Class<E> getEntityClass() {
        if (entityClass == null) {
            final Type superType = getClass().getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) superType;
                entityClass = (Class<E>) type.getActualTypeArguments()[0];
            }
        }
        return entityClass;
    }

    /**
     * Initialize a new instance for crud manipulation
     */
    protected void initInstance() {
        createInstance();
    }

    /**
     * Select a record to edit and then push forward to the correct page If
     * irregular, false will be returned to handle the operation, otherwise it
     * will point out a true for success
     */
    public void edit(String path) {
        // if selection for edition is different than 1, reject it
        if (getSelection().size() < 1) {
            error(getFormClientId(), "Não existe nenhum registro com o ID informado");
            return;
        } else if (getSelection().size() != 1) {
            error(getFormClientId(), "Selecione apenas um registro");
            return;
        }

        // set the selected value in the instance
        instance = getSelection().get(0);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "?id=" + ((UidBasicEntity) instance).getId());
        } catch (IOException e) {
            LOG.error("Unable to perform redirect", e);
        }
    }

    /**
     * Select a record to view details
     */
    public void viewDetails() {
        // if selection for edition is different than 1, reject it
        if (getSelection().size() != 1) {
            error(getFormClientId(), "Selecione apenas um registro para ver os detalhes");
            return;
        }

        // set the selected value in the instance
        instance = getSelection().get(0);

        // open dialog only if a single record is selected
        PrimeFaces.current().executeScript("PF('detailsDialog').show();");
    }

    /**
     * Create new instance
     */
    protected void createInstance() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            if (externalContext.getRequestParameterMap().containsKey("id")) {
                String id = externalContext.getRequestParameterMap().get("id");
                instance = facade.find(getEntityClass(), Long.parseLong(id));
            } else {
                instance = getEntityClass().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private Boolean objectHasProperty(Object obj, String propertyName) {
        List<Field> properties = getAllFields(obj);
        for (Field field : properties) {
            if (field.getName().equalsIgnoreCase(propertyName)) {
                return true;
            }
        }
        return false;
    }

    private static List<Field> getAllFields(Object obj) {
        List<Field> fields = new ArrayList<Field>();
        getAllFieldsRecursive(fields, obj.getClass());
        return fields;
    }

    private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFieldsRecursive(fields, type.getSuperclass());
        }

        return fields;
    }

    /**
     * All crud forms must have the form id crudForm. Secondary forms must be
     * handled manually, but this situation is not so usual.
     *
     * @return
     */
    private String getFormClientId() {
        return "crudForm";
    }

    /**
     * Create a new object and initiate the instance
     */
    public void create() {
        id = null;
        initInstance();
    }

    // Getters and Setters.. nothing to do here
    public final E getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    public void setInstance(E instance) {
        this.instance = instance;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getSavePageRedirect() {
        return savePageRedirect;
    }

    public void setSavePageRedirect(String savePageRedirect) {
        this.savePageRedirect = savePageRedirect;
    }
}
