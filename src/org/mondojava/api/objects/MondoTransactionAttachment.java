package org.mondojava.api.objects;

import java.util.Date;

/**
 *
 * @author dgraf
 * Represents the transaction attachment object
 */
public class MondoTransactionAttachment {

    public MondoTransactionAttachment() {

        created = new Date();
        external_id = "";
        file_type = "";
        file_url = "";
        id = "";
        type = "";
        url = "";
        user_id = "";
    }
    private Date created;
    private String external_id;
    private String file_type;
    private String file_url;
    private String id;
    private String type;
    private String url;
    private String user_id;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
