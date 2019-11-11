package com.intrastat.dto;

import java.util.List;

import com.intrastat.model.Role;

/**
 * 
 * @author shivaprasad
 *
 */

public class UserResponseDTO {

  private Integer id;
  private String username;
  private String email;
  List<Role> roles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
