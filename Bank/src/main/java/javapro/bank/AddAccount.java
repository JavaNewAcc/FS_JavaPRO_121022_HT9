package javapro.bank;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class AddAccount {
    public static Account addAccount(Scanner scan, EntityManager em, Client client) {
        System.out.println("Please enter currency (UAH, USD or EUR):");
        String currency = scan.nextLine().toUpperCase();

        for (Account acc : client.getAccounts()) {
            if (acc.getCurrency().equals(currency)) {
                System.out.println("Client already has such account");
                return null;
            }
        }

        Account account = null;

        switch (currency) {
            case "UAH" -> {
                account = new Account(0.0, client, "UAH");
                client.addAccount(account);
            }
            case "USD" -> {
                account = new Account(0.0, client, "USD");
                client.addAccount(account);
            }
            case "EUR" -> {
                account = new Account(0.0, client, "EUR");
                client.addAccount(account);
            }
            default -> {
                System.out.println("There are no accounts in such currency in our bank yet");
            }
        }

        em.getTransaction().begin();
        try {
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

        if (account != null) {
            account.setAccountNumber(account.getId() + 12345 * 10000);

            em.getTransaction().begin();
            try {
                em.persist(account);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
            }
        }
        return account;
    }
}
