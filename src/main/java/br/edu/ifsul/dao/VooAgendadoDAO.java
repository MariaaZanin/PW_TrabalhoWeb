package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.VooAgendado;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooAgendadoDAO<TIPO> extends DAOGenerico<VooAgendado> implements Serializable {
    
    public VooAgendadoDAO(){
        super();
        classePersistente =VooAgendado.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("aeronave", "Aeronave", "like"));
        
        ordemAtual = listaOrdem.get(1);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    
}