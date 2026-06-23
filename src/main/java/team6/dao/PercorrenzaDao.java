package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team6.entities.Percorrenza;
import team6.exceptions.ElementoNonTrovatoException;
import team6.exceptions.EntityNotFoundException;

import java.util.List;

public class PercorrenzaDao {

    private final EntityManager em;

    public PercorrenzaDao(EntityManager em) {
        this.em = em;
    }

    public void save(Percorrenza percorrenza) {
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();

            em.persist(percorrenza);

            t.commit();

            System.out.println("La percorrenza: " + percorrenza + " è stata salvata correttamente!");
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            throw e;
        }
    }

    public Percorrenza findById(Long id) {
        Percorrenza p = em.find(Percorrenza.class, id);
        if (p == null) {
            throw new EntityNotFoundException("Percorrenza con ID " + id + " non trovata nel database.");
        }
        return p;
    }

    public List<Percorrenza> findAll() {
        return em.createQuery("SELECT p FROM Percorrenza p", Percorrenza.class).getResultList();
    }

    public Percorrenza findByIdAndDelete(Long id) {
        Percorrenza p = findById(id);

        EntityTransaction t = em.getTransaction();
        try {

            t.begin();

            em.remove(p);

            t.commit();

            System.out.println("La percorrenza con ID: " + p.getId() + "è stata eliminata con successo!");
            return p;
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            throw e;
        }
    }

    //Metodi task richiesti
    // Conta quante volte un veicolo ha percorso una determinata tratta
    public long contaVolteMezzoHaPercorsoTratta(Long idMezzo, Long idTratta) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Percorrenza p WHERE p.mezzo.id = :idMezzo AND p.tratta.id = :idTratta", Long.class
        );
        query.setParameter("idMezzo", idMezzo);
        query.setParameter("idTratta", idTratta);
        return query.getSingleResult(); // restituisce il numero long di viaggi fatti
    }

    // calcolo dell media percorrenza di una tratta attraverso un id tratta
    public Double calcolaTempoMedioEffettivo(long idTratta) {
        List<Percorrenza> percorrenze = em
                .createQuery("SELECT p FROM Percorrenza p WHERE p.tratta.id = :idTratta", Percorrenza.class)
                .setParameter("idTratta", idTratta)
                .getResultList();

        if (percorrenze.isEmpty()) {
            throw new ElementoNonTrovatoException("Nessuna percorrenza trovata per la tratta: " + idTratta);
        }

        return percorrenze.stream()
                .mapToInt(Percorrenza::getPercorrenzaEffettiva).average().orElseThrow();
    }

}
