package javapro.bank;

import javax.persistence.*;

//import javapro.bank.Currencies;
import org.hibernate.annotations.CollectionId;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private Long accountNumber;

    @Column(nullable = false)
    private String currency;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "passport_number")
    private Client client;

    public Account() {
    }

    public Account(Double amount, Client client, String currency) {
        this.amount = amount;
        this.client = client;
        this.currency = currency;
    }

    public void addClient(Client client) {
        this.client = client;
        client.addAccount(this);
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", accountNumber=" + accountNumber + ", currency='" + currency + '\'' + ", amount=" + amount + '}';
    }
}
