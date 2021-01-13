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


@FacesConverter("enumConverter")
public class EnumConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String value) {
        if (value != null) {
            return getAttributes(component).get(value);
        }
        return new Object();
    }

    @Override
    public String getAsString(FacesContext context,
            UIComponent component, Object value) {
        if (value instanceof Enum) {
            Enum vo = (Enum) value;
            getAttributes(component).put(String.valueOf(vo.ordinal()), vo);
            return String.valueOf(vo.ordinal());
        }

        return "-1";
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getAttributes(UIComponent component) {
        return component.getAttributes();
    }
}
