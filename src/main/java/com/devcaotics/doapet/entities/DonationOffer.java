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
public class DonationOffer
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  private Date donationOfferDate;

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public String getTitle()
  { return this.title; }

  public void setTitle(String title)
  { this.title = title; }

  public String getDescription()
  { return this.description; }

  public void setDescription(String description)
  { this.description = description; }

  public Date getDonationOfferDate()
  { return this.donationOfferDate; }

  public void setDonationOfferDate(Date donationOfferDate)
  { this.donationOfferDate = donationOfferDate; }
}
