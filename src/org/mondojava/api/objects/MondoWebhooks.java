/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mondojava.api.objects;

import java.util.ArrayList;

/**
 *
 * @author dgraf
 */
public class MondoWebhooks {
    
    public MondoWebhooks()
    {
        webhooks = new ArrayList<>();
    }
    
    private ArrayList<MondoWebhook> webhooks;

    public ArrayList<MondoWebhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(ArrayList<MondoWebhook> webhooks) {
        this.webhooks = webhooks;
    }
    
}
