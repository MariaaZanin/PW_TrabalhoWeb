package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    
    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa(){
        
    }
    
    public String listar(){
       return "/privado/pessoa/listar?faces-redirect=true"; 
    }
    
    public void novo(){
        setObjeto(new Pessoa());
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
    
    
    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }
    
}
