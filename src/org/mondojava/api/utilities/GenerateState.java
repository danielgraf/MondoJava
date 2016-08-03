package org.mondojava.api.utilities;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author dgraf
 * Generates a suitably unpredictable token to use in the 
 * state field during authorisation
 */
public class GenerateState {
    
  private static final SecureRandom random = new SecureRandom();

  public static String generate() {
    return new BigInteger(130, random).toString(32);
  }
    
}
