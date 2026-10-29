package edu.fpdual.webapplication.service;


import edu.fpdual.webapplication.client.CurrencyClient;
import edu.fpdual.webapplication.dto.Currency;

import java.util.List;

public class CurrencyService {

    private final CurrencyClient currencyClient;

    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return currencyClient.ping();
    }

    public Currency getCurrency(String currencyName) {
        return currencyClient.get(currencyName);
    }

    public List<Currency> getAllCurrency() {
        return currencyClient.get();
    }

    public boolean updateCurrency(Currency currency) {
        return currencyClient.put(currency);
    }

    public boolean createCurrency(Currency currency) {
        return currencyClient.post(currency);
    }

    public boolean deleteBudget(Currency currency) {
        return currencyClient.delete(currency);
    }

}
