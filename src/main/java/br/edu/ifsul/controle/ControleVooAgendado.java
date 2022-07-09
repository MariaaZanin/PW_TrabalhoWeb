package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleVooAgendado")
@ViewScoped
public class ControleVooAgendado implements Serializable{
    
    @EJB
    private VooAgendadoDAO<VooAgendado> dao;
    private VooAgendado objeto;
    
    @EJB
    private VooDAO<Voo> daoVoo;

    public ControleVooAgendado(){
        
    }
    
    public String listar(){
       return "/privado/vooAgendado/listar?faces-redirect=true"; 
    }
    
    public void novo(){
        objeto = new VooAgendado();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectByID(id);
        }catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
     public void excluir(Object id){
        try{
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
     
     public void salvar(){
         try{
             if(objeto.getId() == null){
                 dao.persist(objeto);
             }else{
                 dao.merge(objeto);
             }
             Util.mensagemInformacao("Objeto persistido com sucesso!");
         }catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
     }
    
    public VooAgendadoDAO<VooAgendado> getDao() {
        return dao;
    }

    public void setDao(VooAgendadoDAO<VooAgendado> dao) {
        this.dao = dao;
    }

    public VooAgendado getObjeto() {
        return objeto;
    }

    public void setObjeto(VooAgendado objeto) {
        this.objeto = objeto;
    }
    
    public VooDAO<Voo> getDaoVoo(){
        return daoVoo;
    }
    
    public void setDaoVoo(VooDAO<Voo> daoVoo) {
        this.daoVoo = daoVoo;
    }
    
}
