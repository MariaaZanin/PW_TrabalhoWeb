package br.edu.ifsul.controle;


import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleCidade")
@ViewScoped
public class ControleCidade implements Serializable{
    @EJB
    private CidadeDAO<Cidade> dao;
    private Cidade objeto;

    public ControleCidade(){
        
    }
    
    public String listar(){
       return "/privado/cidade/listar?faces-redirect=true"; 
    }
    
    public void novo(){
        setObjeto(new Cidade());
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

    public CidadeDAO<Cidade> getDao() {
        return dao;
    }

    public void setDao(CidadeDAO<Cidade> dao) {
        this.dao = dao;
    }

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }
     
}
