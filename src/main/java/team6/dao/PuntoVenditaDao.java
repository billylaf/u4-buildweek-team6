package team6.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Distributore;
import team6.entities.PuntoVendita;
import team6.enums.StatoDistributore;
import team6.exceptions.ElementoNonTrovatoException;

public class PuntoVenditaDao {
    private final EntityManager em;


    public PuntoVenditaDao(EntityManager em) {
        this.em = em;
    }

    // metodi di utilities
    //Salva puntovendita
    public void save(PuntoVendita pv){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(pv);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Trova by id
    public PuntoVendita findById(Long id){
        PuntoVendita trovato = em.find(PuntoVendita.class, id);
        if(trovato==null){
            throw new ElementoNonTrovatoException("Punto vendita con id: " + id + " non è stato trovato");
        }
        return trovato;
    }

    //Cambia stato distributore
    public void cambiaStatoDistributore (Long idDistributore, StatoDistributore nuovoStato){
        // lancio la mia findbyid
        PuntoVendita pv = findById(idDistributore);
        // mi assicuro che sia un istanza di distributore e non punto vendita
        if(pv instanceof Distributore){
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            // effettuo il casting e setto il nuovo stato
            ((Distributore) pv).setStato(nuovoStato);
            tx.commit();
        }
    }
}
