package javapro.bank;

public class FindAccount {
    public static Account findAccount(Client client, String currency) {
        for (Account account : client.getAccounts()) {
            if (account.getCurrency().equals(currency.toUpperCase())) {
                return account;
            }
        }
        return null;
    }
}
