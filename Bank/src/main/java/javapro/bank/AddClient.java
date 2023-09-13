package javapro.bank;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Scanner;

public class AddClient {
    public static void addClient(Scanner scan, EntityManager em) {
        System.out.println("Please enter passport number of the client:");
        String passportNumber = scan.nextLine().toUpperCase();
        try {
            Client clientExist = em.find(Client.class, passportNumber);
            System.out.println(clientExist.toString() + " already exists");
        } catch (EntityNotFoundException | NullPointerException e) {
            System.out.println("Please enter client's name:");
            String name = scan.nextLine();
            System.out.println("Please enter client's address:");
            String address = scan.nextLine();

            Client client = new Client(passportNumber, name, address);

            em.getTransaction().begin();
            try {
                em.persist(client);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                throw ex;
            }
        } catch (EntityExistsException ex) {
            System.out.println("Such Client already exists.");
        }
    }
}