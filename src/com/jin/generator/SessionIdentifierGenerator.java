package com.jin.generator;

import java.security.SecureRandom;
import java.math.BigInteger;


public final class SessionIdentifierGenerator {
  private SecureRandom random = new SecureRandom();

  public String passwordGenerator() {
    return new BigInteger(130, random).toString(32);
  }
}