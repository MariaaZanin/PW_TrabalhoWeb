package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Voo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooDAO<TIPO> extends DAOGenerico<Voo> implements Serializable {
    
    public VooDAO(){
        super();
        classePersistente = Voo.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descrição", "like"));
        
        ordemAtual = listaOrdem.get(1);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Voo getObjectByID(Object id) throws Exception {
        Voo obj = em.find(Voo.class, id);
        obj.getVoo_agendado().size();
        return obj;
    }
    
}