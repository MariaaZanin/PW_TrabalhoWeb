package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.dao.PassagemDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value="controlePassagem")
@ViewScoped
public class ControlePassagem implements Serializable{
    @EJB
    private PassagemDAO<Passagem> dao;
    private Passagem objeto;
    
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    
    @EJB
    private ClasseDAO<Classe> daoClasse;
    
    @EJB
    private VooAgendadoDAO<VooAgendado> daoVooAgendado;

    public ControlePassagem() {
    }

    public String listar(){
       return "/privado/passagem/listar?faces-redirect=true"; 
    }
    public void novo(){
        objeto = new Passagem();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectByID(id);        
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: "+ Util.getMensagemErro(e));
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

    public PassagemDAO<Passagem> getDao() {
        return dao;
    }

    public void setDao(PassagemDAO<Passagem> dao) {
        this.dao = dao;
    }

    public Passagem getObjeto() {
        return objeto;
    }

    public void setObjeto(Passagem objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public ClasseDAO<Classe> getDaoClasse() {
        return daoClasse;
    }

    public void setDaoClasse(ClasseDAO<Classe> daoClasse) {
        this.daoClasse = daoClasse;
    }

    public VooAgendadoDAO<VooAgendado> getDaoVooAgendado() {
        return daoVooAgendado;
    }

    public void setDaoVooAgendado(VooAgendadoDAO<VooAgendado> daoVooAgendado) {
        this.daoVooAgendado = daoVooAgendado;
    }

}
