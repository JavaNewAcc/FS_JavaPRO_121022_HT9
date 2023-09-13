package javapro.bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class RemoveClient {
    public static boolean removeClient(String passportNumber, EntityManager em) throws EntityNotFoundException {
        Client client;
        try {
            client = FindClient.findClient(passportNumber, em);
        } catch (EntityNotFoundException e) {
            throw e;
        }

        try {
            em.getTransaction().begin();
            em.remove(client);
            em.getTransaction().commit();
//            System.out.println("Client " + client.getName() + " with passport number " + client.getPassportNumber() + " has been successfully deleted.");
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return false;
    }
}