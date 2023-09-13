package javapro.bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class ExchangeMenu {
    public static void exchangeMenu(Scanner scan, EntityManager em, Client client) {
        Account initialAccount;
        Account aimAccount;
        double finalAmount = 0;

        System.out.println("Please enter initial currency for exchange:");
        String initialCurrency = scan.nextLine().toUpperCase();

        try {
            initialAccount = FindAccount.findAccount(client, initialCurrency);
            initialAccount.getCurrency();
        } catch (NullPointerException | EntityNotFoundException ex) {
            System.out.println("Client doesn't have " + initialCurrency.toUpperCase() + " account for debiting. Please first add account.");
            return;
        }

        System.out.println("Please enter currency you want to get after exchange:");
        String aimCurrency = scan.nextLine().toUpperCase();

        try {
            aimAccount = FindAccount.findAccount(client, aimCurrency);
            aimAccount.getCurrency();
        } catch (NullPointerException | EntityNotFoundException ex) {
            System.out.println("Client doesn't have " + aimCurrency.toUpperCase() + " account for crediting. Please first add account.");
            return;
        }

        System.out.println("Please enter desired amount for exchange:");
        double amount = scan.nextDouble();
        scan.nextLine();

        try {
            if (FindAccount.findAccount(client, initialCurrency).getAmount() < amount) {
                System.out.println("Amount of funds at " + initialCurrency.toUpperCase() + " account is insufficient. Please replenish account first.");
                return;
            }
        } catch (NullPointerException | EntityNotFoundException e) {
            System.out.println("Client doesn't have such account");
        }

        String exRateName = initialCurrency + aimCurrency;

        ExRate exRate = em.find(ExRate.class, exRateName);

        Transaction transaction = new Transaction(client.getPassportNumber(), client.getName(), new Date(), initialCurrency.toUpperCase(), amount, aimCurrency.toUpperCase(), amount * exRate.getExRateValue(), exRate.getExRateValue());

        em.getTransaction().begin();
        try {
            initialAccount.setAmount(initialAccount.getAmount() - amount);
            finalAmount = amount * exRate.getExRateValue();
            aimAccount.setAmount(aimAccount.getAmount() + finalAmount);
            em.persist(transaction);
            em.persist(initialAccount);
            em.persist(aimAccount);
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.clear();
//        System.out.println("Amount after exchange of " + amount + " " + initialCurrency + " to " + aimCurrency + " is: " + finalAmount);
    }
}
