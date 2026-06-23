package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.StatoMezzo;

import java.util.List;

public class StatoMezzoDAO {
    private final EntityManager entityManager;


    public StatoMezzoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(StatoMezzo newStatoMezzo) {


        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newStatoMezzo);

        transaction.commit();

        System.out.println("Lo stato del mezzo" + newStatoMezzo + " e' stato salvato nel DB");

    }

    public List<StatoMezzo> findStoricoByMezzoId(long idMezzo) {
        return this.entityManager
                .createQuery("SELECT x FROM StatoMezzo x WHERE x.mezzo.id = :idMezzo ORDER BY x.data_inizio", StatoMezzo.class)
                .setParameter("idMezzo", idMezzo)
                .getResultList();
    }

}
