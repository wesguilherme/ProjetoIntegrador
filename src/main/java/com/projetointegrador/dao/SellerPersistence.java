package com.projetointegrador.dao;

import com.projetointegrador.entity.Seller;
import com.projetointegrador.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SellerPersistence {

    private Connection cnx;
    private EntityManager em;

    public SellerPersistence(){
        em = JPAUtil.getEntityManager();
    }

    public void atualiza(Seller seller) {

    }

    public List<Seller> lista(){
        TypedQuery<Seller> qry = em.createQuery("From Seller", Seller.class);
        return qry.getResultList();
    }

    public void cadastrar(Seller seller) {
        em.getTransaction().begin();
        em.persist(seller);
        em.getTransaction().commit();
    }

    public Seller get(String codigo) {
        return em.find(Seller.class, codigo);
    }

    public void exclui(String codigo) {
        try {
            PreparedStatement ps = cnx.prepareStatement("delete from vendedores where codigo = ?");
            ps.setString(1, codigo);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void atualizaSituacaoVendedoresEmLote() {
//        em.getTransaction().begin();
//
//        List<Seller> vendedores = lista();
//        vendedores.forEach(v -> {
//            v.setSituacao(SituacaoVendedor.INATIVO);
//            em.merge(v);
//        });
//
//        em.getTransaction().commit();
//    }
//
//    public static void main(String[] args) {
//        new SellerPersistence().atualizaSituacaoVendedoresEmLote();
//    }
}
