/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mondojava.api.utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author dgraf
 */
public class HttpResponseException extends Exception {

    public HttpResponseException(CloseableHttpResponse resp) throws IOException {
        
        super(resp.getStatusLine().getStatusCode() + ": " + resp.getStatusLine().getReasonPhrase() + " - " + EntityUtils.toString(resp.getEntity(), "UTF-8"));
        resp.close();
    }

}
