package com.devcaotics.doapet.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class AdoptionApplication
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private AdoptionStatus adoptionStatus;

  @ManyToOne
  private Animal animal;

  @ManyToOne
  private User RequestingUser;

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public AdoptionStatus getAdoptionStatus()
  { return this.adoptionStatus; }

  public void setAdoptionStatus(AdoptionStatus adoptionStatus)
  { this.adoptionStatus = adoptionStatus; }

  public Animal getAnimal()
  { return this.animal; }

  public void setAnimal(Animal animal)
  { this.animal = animal; }

  public User getRequestingUser()
  { return this.RequestingUser; }

  public void setRequestingUser(User RequestingUser)
  { this.RequestingUser = RequestingUser; }
}
