package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Passagem;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class PassagemDAO<TIPO> extends DAOGenerico<Passagem> implements Serializable {

    public PassagemDAO(){
        super();
        classePersistente = Passagem.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("bagagem", "Bagagem", "="));
        
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }


}
