package org.mondojava.api.objects;

import java.util.ArrayList;

/**
 *
 * @author dgraf
 * Represents the balance payload returned on the /balance endpoint
 */
public class MondoBalance {
    
    public MondoBalance()
    {
        balance = 0;
        currency = "GBP";
        spend_today = 0;
        local_currency = "GBP";
        local_exchange_rate = 0;
        local_spend = new ArrayList<>();
    }
    private long balance;
    private String currency;
    private long spend_today;
    private String local_currency;

    public String getLocal_currency() {
        return local_currency;
    }

    public void setLocal_currency(String local_currency) {
        this.local_currency = local_currency;
    }

    public long getLocal_exchange_rate() {
        return local_exchange_rate;
    }

    public void setLocal_exchange_rate(long local_exchange_rate) {
        this.local_exchange_rate = local_exchange_rate;
    }

    private long local_exchange_rate;
    private ArrayList<Long> local_spend;

    public ArrayList<Long> getLocal_spend() {
        return local_spend;
    }

    public void setLocal_spend(ArrayList<Long> local_spend) {
        this.local_spend = local_spend;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getSpend_today() {
        return spend_today;
    }

    public void setSpend_today(long spend_today) {
        this.spend_today = spend_today;
    }
    
}
