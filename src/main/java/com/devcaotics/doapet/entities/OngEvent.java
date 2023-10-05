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
public class OngEvent
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String title;

  @Column
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  private Date startDatetime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date endDatetime;

  public int getId()
  { return this.id; }

  public void setId(int id)
  { this.id = id; }

  public String getTitle()
  { return this.title; }

  public void setTitle(String title)
  { this.title = title; }

  public String getDescription()
  { return this.description; }

  public void setDescription(String description)
  { this.description = description; }

  public Date getStartDatetime()
  { return this.startDatetime; }

  public void setStartDatetime(Date startDatetime)
  { this.startDatetime = startDatetime; }

  public Date getEndDatetime()
  { return this.endDatetime; }

  public void setEndDatetime(Date endDatetime)
  { this.endDatetime = endDatetime; }
}
