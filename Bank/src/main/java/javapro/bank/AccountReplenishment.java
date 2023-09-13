package javapro.bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountReplenishment {
    public static void accountReplenishment(EntityManager em, Scanner scan, Client client) {

        System.out.println("Please enter currency of account:");
        String sCurrency = scan.nextLine();
        Account account;

        try {
            account = FindAccount.findAccount(client, sCurrency);
            account.getAmount();
        } catch (EntityNotFoundException | NullPointerException e) {
            System.out.println("This client doesn't have " + sCurrency.toUpperCase() + " account");
            return;
        }

        System.out.println("Please enter amount:");
        double amount = 0.0;
        try {
            amount = scan.nextDouble();
            if (amount < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.println("You have entered wrong amount. Please check your input value.");
        }

        double newAmount = account.getAmount() + amount;
        account.setAmount(newAmount);

        Transaction transaction = new Transaction(client.getPassportNumber(), client.getName(), new Date(), sCurrency.toUpperCase(), newAmount, null, null, null);
        //String passportNumber, String clientName, Date date, String fromCurrency, Double initAmount, String toCurrency, Double finalAmount, Double exRate

        em.getTransaction().begin();
        try {
            em.persist(transaction);
//            em.persist(account);
//            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        scan.nextLine();
    }
}
