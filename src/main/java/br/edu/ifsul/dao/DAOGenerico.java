package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAOGenerico<TIPO> implements Serializable {
    
    private List<TIPO> listObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "Trabalho_PW2022-1PU")
    protected EntityManager em;
    protected Class classePersistente;

    public DAOGenerico(){
        
    }
    
    public List<TIPO> getListObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    public void persist(TIPO obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception {
        em.merge(obj);
    }

    public void remove(TIPO obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public TIPO getObjectByID(Object id) throws Exception {
        return (TIPO) em.find(classePersistente, id);
    }
    
    public void setListObjetos(List<TIPO> listObjetos) {
        this.listObjetos = listObjetos;
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }
    
}
