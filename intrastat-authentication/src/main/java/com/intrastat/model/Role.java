package com.intrastat.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author shivaprasad
 *
 */

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}
