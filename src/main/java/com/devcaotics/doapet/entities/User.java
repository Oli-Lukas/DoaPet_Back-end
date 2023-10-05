package com.devcaotics.doapet.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @Column
  private String login;

  @Column
  private String password;

  @Embedded
  private UserAddress address;

  public Long getId()
  { return this.Id; }

  public void setId(Long Id)
  { this.Id = Id; }

  public String getName()
  { return this.name; }

  public void setName(String name)
  { this.name = name; }

  public String getEmail()
  { return this.email; }

  public void setEmail(String email)
  { this.email = email; }

  public Date getBirthDate()
  { return this.birthDate; }

  public void setBirthDate(Date birthDate)
  { this.birthDate = birthDate; }

  public String getLogin()
  { return this.login; }

  public void setLogin(String login)
  { this.login = login; }

  public String getPassword()
  { return this.password; }

  public void setPassword(String password)
  { this.password = password; }

  public UserAddress getAddress()
  { return this.address; }

  public void setAddress(UserAddress address)
  { this.address = address; }
}
