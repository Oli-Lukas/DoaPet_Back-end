package com.devcaotics.doapet.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Ong
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  @Temporal(TemporalType.DATE)
  private Date foundationDate;

  @Column
  private String login;

  @Column
  private String password;

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public String getName()
  { return this.name; }

  public void setName(String name)
  { this.name = name; }

  public String getEmail()
  { return this.email; }

  public void setEmail(String email)
  { this.email = email; }

  public Date getFoundationDate()
  { return this.foundationDate; }

  public void setFoundationDate(Date foundationDate)
  { this.foundationDate = foundationDate; }

  public String getLogin()
  { return this.login; }

  public void setLogin(String login)
  { this.login = login; }

  public String getPassword()
  { return this.password; }

  public void setPassword(String password)
  { this.password = password; }
}
