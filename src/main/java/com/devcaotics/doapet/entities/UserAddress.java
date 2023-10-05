package com.devcaotics.doapet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserAddress
{
  @Column
  private String place;

  @Column
  private String district;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private String cep;

  public String getPlace()
  { return this.place; }

  public void setPlace(String place)
  { this.place = place; }

  public String getDistrict()
  { return this.district; }

  public void setDistrict(String district)
  { this.district = district; }

  public String getCity()
  { return this.city; }

  public void setCity(String city)
  { this.city = city; }

  public String getState()
  { return this.state; }

  public void setState(String state)
  { this.state = state; }

  public String getCep()
  { return this.cep; }

  public void setCep(String cep)
  { this.cep = cep; }
}
