package javapro.bank;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            emf = Persistence.createEntityManagerFactory("bank");
            em = emf.createEntityManager();

            try {
                AddExRate.addExRate("EUR", "USD", 1.12, em);
                AddExRate.addExRate("EUR", "UAH", 41.50, em);
                AddExRate.addExRate("USD", "UAH", 40.01, em);
                AddExRate.addExRate("USD", "EUR", 0.94, em);
                AddExRate.addExRate("UAH", "EUR", 0.025, em);
                AddExRate.addExRate("UAH", "USD", 0.026, em);
            } catch (EntityExistsException ex) {
                ex.printStackTrace();
            }
            try {
                while (true) {
                    System.out.println("Please choose operation:");
                    System.out.println("1. Add client");
                    System.out.println("2. Find client");
                    System.out.println("3. Remove client");
                    System.out.println("4. Print all clients");
                    String option = scan.nextLine();
                    switch (option) {
                        case "1" -> {
                            AddClient.addClient(scan, em);
                        }
                        case "2" -> {
                            try {
                                System.out.println("Please enter passport number of the Client:");
                                String passportNumber = scan.nextLine().toUpperCase();
                                Client client = FindClient.findClient(passportNumber, em);
                                System.out.println(client);
                                ClientMenu.clientMenu(scan, em, client);
                            } catch (EntityNotFoundException e) {
                                System.out.println("There is no such client");
                            }
                        }
                        case "3" -> {
                            System.out.println("Please enter passport number of the Client:");
                            String passportNumber = scan.nextLine().toUpperCase();
                            if (RemoveClient.removeClient(passportNumber, em)) {
                                System.out.println("Client with passport number " + passportNumber + " has been removed.");
                            } else {
                                System.out.println("There is no such client");
                            }
                        }
                        case "4" -> {
                            List<Client> list = GetAllClients.printClients(em);
                            if (!list.isEmpty()) {
                                for (Client client : list) {
                                    System.out.println(client);
                                }
                            } else {
                                System.out.println("There are no clients yet");
                            }
                        }
                    }
                }
            } finally {
                em.close();
                emf.close();
            }
        }
    }
}
