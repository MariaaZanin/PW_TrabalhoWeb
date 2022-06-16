package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAOGenerico<TIPO> implements Serializable {

    private List<TIPO> listObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "Trabalho_PW2022-1PU")
    protected EntityManager em;
    protected Class classePersistente;
    protected String filtro = "";
    protected List<Ordem> listaOrdem = new ArrayList<>();
    protected Ordem ordemAtual;
    protected ConverterOrdem converterOrdem;
    protected Integer maximoObjeto = 5;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;

    public DAOGenerico() {

    }

   
    public List<TIPO> getListObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            switch (ordemAtual.getOperador()) {
                case "=":
                    if (ordemAtual.getAtributo().equals("id")) {
                        try {
                            Integer.parseInt(filtro);
                        } catch (Exception e) {
                            filtro = "0";
                        }
                    }
                    where += " where " + ordemAtual.getAtributo() + " = '" + filtro + "' ";
                    break;
                case "like" :
                    where += " where upper(" + ordemAtual.getAtributo() + ") like '" + filtro.toUpperCase() + "%' ";
                    break;
            }
        }
        jpql += where;
        jpql += " order by " + ordemAtual.getAtributo();
        System.out.println("JPQL " + jpql);
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjeto).getResultList();
    }
    
    public void primeiro(){
        posicaoAtual = 0;
    }
    
    public void anterior(){
        posicaoAtual -= maximoObjeto;
        if(posicaoAtual < 0){
            posicaoAtual = 0;
        }
    }
    
    public void proximo(){
        if(posicaoAtual + maximoObjeto < totalObjetos){
            posicaoAtual += maximoObjeto;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjeto;
        if(resto > 0){
            posicaoAtual = totalObjetos - resto;
        } else{
            posicaoAtual = totalObjetos - maximoObjeto;
        }
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjeto;
        if(ate > totalObjetos){
            ate = totalObjetos;
        }
        if(totalObjetos > 0 ){
        return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
        }else{
            return "Nenhum registro encontrado";
        }
    }    
    
    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordemAtual.getAtributo();
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Ordem> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(List<Ordem> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    public Ordem getOrdemAtual() {
        return ordemAtual;
    }

    public void setOrdemAtual(Ordem ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    public ConverterOrdem getConverterOrdem() {
        return converterOrdem;
    }

    public void setConverterOrdem(ConverterOrdem converterOrdem) {
        this.converterOrdem = converterOrdem;
    }

    public Integer getMaximoObjeto() {
        return maximoObjeto;
    }

    public void setMaximoObjeto(Integer maximoObjeto) {
        this.maximoObjeto = maximoObjeto;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

}
