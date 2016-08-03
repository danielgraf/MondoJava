package org.mondojava.api.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author dgraf
 * Converts any JSON string to a Java POJO
 */
public class ToJava {
    
    private static final ObjectMapper mapper = new ObjectMapper();
        
    public static Object convert(String json, Class clazz) throws Exception
    {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.readValue(json, clazz);
    }
    
}
