/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pim.web.controller.converter;

import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("converterObject")
public class ConverterObject implements Converter {

    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String value) {
        if (value != null) {
            return getViewMap(context).get(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context,
            UIComponent component, Object value) {
        if (context != null && value != null) {
            getViewMap(context).put(String.valueOf(value.hashCode()), value);
            return String.valueOf(value.hashCode());
        }
        return "-1";
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getViewMap(FacesContext context) {
        return context.getViewRoot().getViewMap();
    }
}
