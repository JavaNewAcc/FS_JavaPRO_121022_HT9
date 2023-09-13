package javapro.bank;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExRate {

    @Column(nullable = false)
    private String fromCurrency;

    @Column(nullable = false)
    private String toCurrency;

    @Id
    private String exRateName;

    @Column(nullable = false)
    private Double exRateValue;

    public ExRate() {
    }

    public ExRate(String fromCurrency, String toCurrency, Double exRateValue) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exRateValue = exRateValue;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getExRateValue() {
        return exRateValue;
    }

    public void setExRateValue(Double exRateValue) {
        this.exRateValue = exRateValue;
    }

    public String getExRateName() {
        return exRateName;
    }

    public void setExRateName(String exRateName) {
        this.exRateName = exRateName;
    }
}
