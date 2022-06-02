package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleAeroporto")
@ViewScoped
public class ControleAeroporto implements Serializable{
    @EJB
    private AeroportoDAO<Aeroporto> dao;
    private Aeroporto objeto;
    @EJB
    private CidadeDAO<Cidade> daoCidade;
    
    public ControleAeroporto(){
        
    }
    
    public String listar(){
       return "/privado/aeroporto/listar?faces-redirect=true"; 
    }
    
    public void novo(){
        setObjeto(new Aeroporto());
    }
    
    public void alterar(Object id){
        try{
            setObjeto(getDao().getObjectByID(id));
        }catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
     public void excluir(Object id){
        try{
            setObjeto(getDao().getObjectByID(id));
            getDao().remove(getObjeto());
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
     
     public void salvar(){
         try{
             if(getObjeto().getId() == null){
                 getDao().persist(getObjeto());
             }else{
                 getDao().merge(getObjeto());
             }
             Util.mensagemInformacao("Objeto persistido com sucesso!");
         }catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
     }

    public AeroportoDAO<Aeroporto> getDao() {
        return dao;
    }

    public void setDao(AeroportoDAO<Aeroporto> dao) {
        this.dao = dao;
    }

    public Aeroporto getObjeto() {
        return objeto;
    }

    public void setObjeto(Aeroporto objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }
  
    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }
     
}
