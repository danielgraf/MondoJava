/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mondojava.api.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author dgraf
 */
public class MondoTransaction {


    public MondoTransaction() {

        account_balance = 0;
        amount = 0;
        created = new Date();
        currency = "";
        description = "";
        id = "";
        metadata = new HashMap<>();
        notes = "";
        is_load = false;
        settled = new Date();
        attachments = new ArrayList<>();
        category = "";
        local_amount = 0;
        local_currency = "";
        updated = new Date();
        account_id = "";
        counterparty = new MondoCounterParty();
        scheme = "";
        dedupe_id = "";
        originator = false;

    }
    private String category;
    private long local_amount;
    private String local_currency;
    private Date updated;
    private String account_id;
    private MondoCounterParty counterparty;
    private String scheme;
    private String dedupe_id;
    private boolean originator;

    public boolean isOriginator() {
        return originator;
    }

    public void setOriginator(boolean originator) {
        this.originator = originator;
    }

    public String getDedupe_id() {
        return dedupe_id;
    }

    public void setDedupe_id(String dedupe_id) {
        this.dedupe_id = dedupe_id;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public MondoCounterParty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(MondoCounterParty counterparty) {
        this.counterparty = counterparty;
    }

  
    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLocal_currency() {
        return local_currency;
    }

    public void setLocal_currency(String local_currency) {
        this.local_currency = local_currency;
    }

    public long getLocal_amount() {
        return local_amount;
    }

    public void setLocal_amount(long local_amount) {
        this.local_amount = local_amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    private long account_balance;
    private long amount;
    private Date created;
    private String currency;
    private String description;
    private String id;
    private Object merchant;
    private HashMap<String, String> metadata;
    private String notes;
    private boolean is_load;
    private Date settled;
    private ArrayList<MondoTransactionAttachment> attachments;
 
    public ArrayList<MondoTransactionAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<MondoTransactionAttachment> attachments) {
        this.attachments = attachments;
    }

    public long getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(long account_balance) {
        this.account_balance = account_balance;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isIs_load() {
        return is_load;
    }

    public void setIs_load(boolean is_load) {
        this.is_load = is_load;
    }

    public Date getSettled() {
        return settled;
    }

    public void setSettled(Date settled) {
        this.settled = settled;
    }

}

