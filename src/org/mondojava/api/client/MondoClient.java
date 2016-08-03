package org.mondojava.api.client;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import org.mondojava.api.objects.MondoAccounts;
import org.mondojava.api.objects.MondoAuthResponse;
import org.mondojava.api.objects.MondoBalance;
import org.mondojava.api.objects.MondoExpandedTransaction;
import org.mondojava.api.objects.MondoExpandedTransactionContainer;
import org.mondojava.api.objects.MondoExpandedTransactions;
import org.mondojava.api.objects.MondoFeedItem;
import org.mondojava.api.objects.MondoTransaction;
import org.mondojava.api.objects.MondoTransactionContainer;
import org.mondojava.api.objects.MondoWebhooks;
import org.mondojava.api.rest.AUTHclient;
import org.mondojava.api.rest.RESTclient;
import org.mondojava.api.utilities.ToJava;

/**
 *
 * @author dgraf
 * MondoClient is a one-stop class for interacting with the Mondo API
 * All API methods will be reflected in here
 */
public class MondoClient {
    
    private final String client_id;
    private final String client_secret;
    private String access_token ;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    // If we are authorised, access_token will be populated
    // Otherwise throw an exception
    public String getAccess_token() throws Exception {
        if (access_token.isEmpty()) throw new Exception("Not authorised");
        return access_token;
    }
    
    private String state; // Store the state token used in authorisation

    public String getState() {
        return state;
    }
    
    // Construct this with your secrets
    public MondoClient(String cl_id, String cl_secret)
    {
        client_id = cl_id;
        client_secret = cl_secret;
        access_token = "";
    }
    
    // Construct the URL to authorise your app via OAuth2
    public String getAuthenticationURL(String redirect_url) throws Exception
    {
        AUTHclient ac = new AUTHclient();
        String URL = ac.getAuthenticationURL(client_id, redirect_url);
        state = ac.getState();
        return URL;
    }
    
    // Perform Access Token Exchange step of authorisation
    // code was obtained in step 1 using the URL consructed in getAuthenticationURL
    public MondoAuthResponse requestAccessToken(String code, String redirect_url) throws Exception
    {
        AUTHclient ac = new AUTHclient();
        MondoAuthResponse resp = ac.getAccessToken(code, client_id, client_secret, redirect_url);
        access_token = resp.getAccess_token();
        return resp;   
    }
    
    // Return a list of Mondo accounts using the access token
    public MondoAccounts getAccounts() throws Exception
    {
        RESTclient rc = new RESTclient("accounts");
        MondoAccounts accounts = (MondoAccounts) ToJava.convert(rc.get(access_token), MondoAccounts.class);
        return accounts;
    }
    
    // Get the balance for any supplied account
    public MondoBalance getBalance(String account_id) throws Exception
    {
        RESTclient rc = new RESTclient("balance");
        MondoBalance balance = (MondoBalance) ToJava.convert(rc.get(access_token, new AbstractMap.SimpleEntry("account_id", account_id)), MondoBalance.class);
        return balance; 
    }
    
    // Get a list of transactions with expanded merchants
    public MondoExpandedTransactions getTransactions(String account_id) throws Exception
    {
        RESTclient rc = new RESTclient("transactions");
        AbstractMap.SimpleEntry[] args = {new AbstractMap.SimpleEntry("expand[]", "merchant"), new AbstractMap.SimpleEntry("account_id", account_id)};
        MondoExpandedTransactions transactions = (MondoExpandedTransactions) ToJava.convert(rc.get(access_token, args), MondoExpandedTransactions.class);
        return transactions;  
    }
    
    // Get a single transaction with expanded merchant
    public MondoExpandedTransaction getTransaction(String account_id, String transaction_id) throws Exception
    {
        RESTclient rc = new RESTclient("transactions");
        AbstractMap.SimpleEntry[] args = {new AbstractMap.SimpleEntry("expand[]", "merchant"), new AbstractMap.SimpleEntry("account_id", account_id), new SimpleEntry("transaction_id", transaction_id)};
        MondoExpandedTransactionContainer transaction = (MondoExpandedTransactionContainer) ToJava.convert(rc.get(access_token, transaction_id, args), MondoExpandedTransactionContainer.class);
        return transaction.getTransaction();  
    }
    
    // Annotate a transaction
    public MondoTransaction annotateTransaction(String transaction_id, SimpleEntry... tags) throws Exception
    {
        RESTclient rc = new RESTclient("transactions");
        MondoTransactionContainer txn = (MondoTransactionContainer) ToJava.convert(rc.patch(access_token, transaction_id, tags), MondoTransactionContainer.class);
        return txn.getTransaction(); 
    }
    
    // Post a feed item
    public void postFeedItem(MondoFeedItem item, String account_id) throws Exception
    {
        RESTclient rc = new RESTclient("feed");
        
        ArrayList<SimpleEntry> feedparams = new ArrayList<>();
        
            feedparams.add(new SimpleEntry("account_id", account_id));
            feedparams.add(new SimpleEntry("type", item.getType()));
            feedparams.add(new SimpleEntry("url", item.getUrl()));
            feedparams.add(new SimpleEntry("params[title]", item.getTitle()));
            feedparams.add(new SimpleEntry("params[image_url]", item.getImage_url()));
            feedparams.add(new SimpleEntry("params[background_color]", item.getBackground_color()));
            feedparams.add(new SimpleEntry("params[body_color]", item.getBody_color()));
            feedparams.add(new SimpleEntry("params[title_color]", item.getTitle_color()));
            feedparams.add(new SimpleEntry("params[body]", item.getBody()));
            

        rc.post(access_token, feedparams.toArray(new SimpleEntry[feedparams.size()]));
        
    }
    
    // Post a webhook
    public void registerWebhook(String account_id, String url) throws Exception
    {
        RESTclient rc = new RESTclient("webhooks");
        SimpleEntry[] params = {new SimpleEntry("url", url), new SimpleEntry("account_id", account_id)};
        rc.post(access_token, params);
    }
    
    // List Webhooks
    public MondoWebhooks getWebhooks(String account_id) throws Exception
    {
        RESTclient rc = new RESTclient("webhooks");
        SimpleEntry[] params = {new SimpleEntry("account_id", account_id)};
        MondoWebhooks webhooks = (MondoWebhooks) ToJava.convert(rc.get(access_token, params), MondoWebhooks.class);
        return webhooks;
    }
    
    // Delete Webhook
    public void deleteWebhook(String webhook_id) throws Exception
    {
        RESTclient rc = new RESTclient("webhooks");
        rc.delete(access_token, webhook_id);
    }
    
}
