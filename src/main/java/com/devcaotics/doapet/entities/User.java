package com.devcaotics.doapet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;
  
  private String endereco;

  private String phoneNumber;

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public UserType getUserType()
  { return this.userType; }

  public void setUserType(UserType userType)
  { this.userType = userType; }

  public String getName()
  { return this.name; }

  public void setName(String name)
  { this.name = name; }

  public String getEmail()
  { return this.email; }

  public void setEmail(String email)
  { this.email = email; }

  public String getSenha()
  { return this.senha; }

  public void setSenha(String senha)
  { this.senha = senha; }

  public String getEndereco()
  { return this.endereco; }

  public void setEndereco(String endereco)
  { this.endereco = endereco; }

  public String getPhoneNumber()
  { return this.phoneNumber; }

  public void setPhoneNumber(String phoneNumber)
  { this.phoneNumber = phoneNumber; }
}
