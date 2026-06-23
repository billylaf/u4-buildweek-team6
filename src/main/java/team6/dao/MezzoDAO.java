package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Mezzo;

public class MezzoDAO {
    private final EntityManager entityManager;


    public MezzoDAO(EntityManager em) {
        this.entityManager = em;
    }

    public void save(Mezzo newMezzo) {


        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        this.entityManager.persist(newMezzo);

        transaction.commit();

        System.out.println("Il mezzo" + newMezzo + " e' stato salvato nel DB");

    }

}
