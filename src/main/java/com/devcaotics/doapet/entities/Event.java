package com.devcaotics.doapet.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Event
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User organizer;

  @Column(nullable = false)
  private String EventName;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime EventDate;

  @Column(nullable = false)
  private String endereco;

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public User getOrganizer()
  { return this.organizer; }

  public void setOrganizer(User organizer)
  { this.organizer = organizer; }

  public String getEventName()
  { return this.EventName; }

  public void setEventName(String EventName)
  { this.EventName = EventName; }

  public String getDescription()
  { return this.description; }

  public void setDescription(String description)
  { this.description = description; }

  public LocalDateTime getEventDate()
  { return this.EventDate; }

  public void setEventDate(LocalDateTime EventDate)
  { this.EventDate = EventDate; }

  public String getEndereco()
  { return this.endereco; }

  public void setEndereco(String endereco)
  { this.endereco = endereco; }
}
