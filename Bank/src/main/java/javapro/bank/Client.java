package javapro.bank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Table(name = "clients")
@NamedQuery(name = "selectByPassportNumber", query = "SELECT client FROM Client client WHERE client.passportNumber LIKE :pattern")
public class Client {
    @Id
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    public Client() {
    }

    public Client(String passportNumber, String name, String address) {
        this.passportNumber = passportNumber;
        this.name = name;
        this.address = address;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setClient(this);
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Client{" + "passportNumber='" + passportNumber + '\'' + ", name='" + name + '\'' + ", address='" + address + '\'' + ", accounts=" + accounts.toString() + '}';
    }
}
