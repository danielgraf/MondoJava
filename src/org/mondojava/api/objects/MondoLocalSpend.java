/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 */
public class MondoLocalSpend {
    
    public MondoLocalSpend()
    {
        spend_today = 0;
        currency = "GBP";
    }
    
    private long spend_today;
    private String currency;

    public long getSpend_today() {
        return spend_today;
    }

    public void setSpend_today(long spend_today) {
        this.spend_today = spend_today;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
}
