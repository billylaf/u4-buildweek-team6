package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Percorrenza;
import team6.exceptions.EntityNotFoundException;

import java.util.List;

public class PercorrenzaDao {

    private final EntityManager em;

    public PercorrenzaDao(EntityManager em){
        this.em = em;
    }

    public void save(Percorrenza percorrenza) {
        EntityTransaction t = em.getTransaction();

        try{
            t.begin();

            em.persist(percorrenza);

            t.commit();

            System.out.println("La percorrenza: " + percorrenza + " è stata salvata correttamente!");
        } catch (Exception e) {
            if(t.isActive()) {
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

    public List<Percorrenza> findAll(){
        return em.createQuery("SELECT p FROM Percorrenza p", Percorrenza.class).getResultList();
    }

    public Percorrenza findByIdAndDelete(Long id){
        Percorrenza p = findById(id);

        if (p == null) {
            throw new EntityNotFoundException("Percorrenza con ID " + id + " non trovata nel database.");
        }

        EntityTransaction t = em.getTransaction();
        try {

            t.begin();

            em.remove(p);

            t.commit();

            System.out.println("La percorrenza con ID: " + p.getId() + "è stata eliminata con successo!");
            return p;
        } catch (Exception e) {
            if (t.isActive()){
                t.rollback();
            } throw e;
        }

    }
}
