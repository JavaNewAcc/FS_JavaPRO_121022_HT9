package javapro.bank;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class ClientMenu {
    public static void clientMenu(Scanner scan, EntityManager em, Client client) {
        System.out.println("Please choose operation for selected client:");
        System.out.println("1. Add account");
        System.out.println("2. Replenishment of account");
        System.out.println("3. Exchange operations");
        System.out.println("4. Current balance at all available accounts");
        System.out.println("5. Return to main menu");

        String optClientMenu = scan.nextLine();

        switch (optClientMenu) {
            case "1" -> {
                AddAccount.addAccount(scan, em, client);
            }
            case "2" -> {
                AccountReplenishment.accountReplenishment(em, scan, client);
            }
            case "3" -> {
                ExchangeMenu.exchangeMenu(scan, em, client);
            }
            case "4" -> {
                Balance.printBalance(em, client);
            }
            default -> {
                return;
            }
        }
    }
}
