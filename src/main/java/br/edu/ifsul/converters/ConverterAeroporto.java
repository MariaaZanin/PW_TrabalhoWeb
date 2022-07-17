package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Aeroporto;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterAeroporto")
@RequestScoped
public class ConverterAeroporto implements Serializable, Converter {
 
    @PersistenceContext(unitName = "Trabalho_PW2022-1PU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Aeroporto.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Aeroporto obj = (Aeroporto) t;
        return obj.getId().toString();
    }
}