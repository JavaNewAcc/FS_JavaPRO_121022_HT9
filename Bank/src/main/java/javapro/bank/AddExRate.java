package javapro.bank;

import javax.persistence.EntityManager;

public class AddExRate {
    public static void addExRate(String fromCurrency, String toCurrency, Double exRateValue, EntityManager em) {
        ExRate exRate = new ExRate(fromCurrency, toCurrency, exRateValue);
        exRate.setExRateName(fromCurrency + toCurrency);

        em.getTransaction().begin();
        try {
            em.persist(exRate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }
}
