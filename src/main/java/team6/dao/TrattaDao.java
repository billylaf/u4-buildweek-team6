package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Tratta;
import team6.exceptions.EntityNotFoundException;

import java.util.List;

public class TrattaDao {

    private final EntityManager em;

    public TrattaDao(EntityManager em){
        this.em = em;
    }

    public void save(Tratta tratta) {
        EntityTransaction t = em.getTransaction();

        try{
            t.begin();

            em.persist(tratta);

            t.commit();

            System.out.println("La tratta: " + tratta.getId() + " è stata salvata correttamente!");
        } catch (Exception e) {
            if(t.isActive()) {
                t.rollback();
            }
            throw e;
        }
    }

    public Tratta findById(Long id) {
        Tratta p = em.find(Tratta.class, id);
        if (p == null) {
            throw new EntityNotFoundException("Tratta con ID " + id + " non trovata nel database.");
        }
        return p;
    }

    public List<Tratta> findAll(){
        return em.createQuery("SELECT p FROM Tratta p", Tratta.class).getResultList();
    }

    public Tratta findByIdAndDelete(Long id){
        Tratta p = findById(id);

        EntityTransaction t = em.getTransaction();
        try {

            t.begin();

            em.remove(p);

            t.commit();

            System.out.println("La tratta con ID: " + p.getId() + "è stata eliminata con successo!");
            return p;
        } catch (Exception e) {
            if (t.isActive()){
                t.rollback();
            } throw e;
        }

    }
}
