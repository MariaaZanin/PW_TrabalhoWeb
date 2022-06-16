package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Classe;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ClasseDAO<TIPO> extends DAOGenerico<Classe> implements Serializable {
    
    public ClasseDAO(){
        super();
        classePersistente = Classe.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        
        ordemAtual = listaOrdem.get(1);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}