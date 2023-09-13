package javapro.bank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;

    @Column(nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Date date;

    @Column
    private String fromCurrency;

    @Column
    private Double initAmount;

    @Column
    private String toCurrency;

    @Column
    private Double finalAmount;

    @Column
    private Double exRate;

    public Transaction() {
    }

    public Transaction(String passportNumber, String clientName, Date date, String fromCurrency, Double initAmount, String toCurrency, Double finalAmount, Double exRate) {
        this.passportNumber = passportNumber;
        this.clientName = clientName;
        this.date = date;
        this.fromCurrency = fromCurrency;
        this.initAmount = initAmount;
        this.toCurrency = toCurrency;
        this.finalAmount = finalAmount;
        this.exRate = exRate;
    }

    public Long getId() {
        return transactionId;
    }

    public void setId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Double getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(Double initAmount) {
        this.initAmount = initAmount;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Double getExRate() {
        return exRate;
    }

    public void setExRate(Double exRate) {
        this.exRate = exRate;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionID=" + transactionId + ", passportNumber='" + passportNumber + '\'' + ", clientName='" + clientName + '\'' + ", date=" + date + ", fromCurrency='" + fromCurrency + '\'' + ", initAmount=" + initAmount + ", toCurrency='" + toCurrency + '\'' + ", finalAmount=" + finalAmount + ", exRate=" + exRate + '}';
    }
}