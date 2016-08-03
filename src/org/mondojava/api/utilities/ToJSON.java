package org.mondojava.api.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author dgraf
 * Converts any object from POJO to JSON
 */
public class ToJSON {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static String convert(Object object) throws Exception
    {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(object);
    }
    
}
