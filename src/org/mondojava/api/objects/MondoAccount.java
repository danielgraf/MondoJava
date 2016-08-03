
package org.mondojava.api.objects;

import java.util.Date;

/**
 *
 * @author dgraf
 * Represents a Mondo account as returned by the /accounts endpoint
 */
public class MondoAccount {
    
    public MondoAccount()
    {
        id = "";
        description = "";
        created = new Date();
    }
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    private Date created;
    
    
}
