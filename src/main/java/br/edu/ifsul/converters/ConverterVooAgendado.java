package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.VooAgendado;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value="converterVooAgendado")
@RequestScoped
public class ConverterVooAgendado implements Serializable, Converter{

     @PersistenceContext(unitName = "Trabalho_PW2022-1PU")
    protected EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione uma Pessoa")){
            return null;
        }
        return em.find(VooAgendado.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        VooAgendado obj = (VooAgendado) t;
        return obj.getId().toString();
    }
    
}
