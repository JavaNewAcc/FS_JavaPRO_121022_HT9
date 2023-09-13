package javapro.bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class FindClient {

    public static Client findClient(String passportNumber, EntityManager em) throws EntityNotFoundException {
        return em.getReference(Client.class, passportNumber);
    }
}
