package org.mondojava.api.objects;

import java.util.Date;

/**
 *
 * @author dgraf
 * Represents the merchant expanded within a transaction from the /transactions endpoint
 */
public class MondoMerchant {

    public MondoMerchant() {
        
        address = new MondoAddress();
        created = new Date();
        group_id = "";
        id = "";
        logo = "";
        emoji = "";
        name = "";
        category = "";
        online = false;
        atm = false;
        metadata = new MondoMerchantMetaData();
        disable_feedback = false;
        updated = new Date();
        

    }
    private MondoAddress address;
    private Date created;
    private String group_id;
    private String id;
    private String logo;
    private String emoji;
    private String name;
    private String category;
    private boolean online;
    private boolean atm;
    private MondoMerchantMetaData metadata;
    private boolean disable_feedback;
    private Date updated;
   

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAtm() {
        return atm;
    }

    public void setAtm(boolean atm) {
        this.atm = atm;
    }

    public MondoMerchantMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(MondoMerchantMetaData metadata) {
        this.metadata = metadata;
    }

    public boolean isDisable_feedback() {
        return disable_feedback;
    }

    public void setDisable_feedback(boolean disable_feedback) {
        this.disable_feedback = disable_feedback;
    }
    
    
            

    public MondoAddress getAddress() {
        return address;
    }

    public void setAddress(MondoAddress address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
