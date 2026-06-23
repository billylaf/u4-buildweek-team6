package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Utente;

public class UtenteDao {
    private final EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    //Metodi di utilities
    //Salva un nuovo utente
    public void save(Utente utente) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(utente);
            tx.commit();
            System.out.println("Utente salvato con successo!");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.out.println("Errore nel salvataggio utente: " + e.getMessage());
        }
    }

    //Cerca un utente tramite il suo ID
    public Utente findById(Long id) {
        return em.find(Utente.class, id);
    }

    //Cancella un utente
    public void delete(Long id) {
        Utente trovato = findById(id);
        if (trovato != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(trovato);
            tx.commit();
            System.out.println("Utente cancellato!");
        }
    }
}