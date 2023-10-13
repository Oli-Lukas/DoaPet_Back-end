package com.devcaotics.doapet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Animal
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User owner;

  @Enumerated(EnumType.STRING)
  private AdoptionStatus adotpionStatus;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String specie;

  private String breed;
  private double weight;
  private int    estimatedAge;

  @Column(nullable = false)
  private String description;


  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public User getOwner()
  { return this.owner; }

  public void setOwner(User owner)
  { this.owner = owner; }

  public AdoptionStatus getAdotpionStatus()
  { return this.adotpionStatus; }

  public void setAdotpionStatus(AdoptionStatus adotpionStatus)
  { this.adotpionStatus = adotpionStatus; }

  public String getName()
  { return this.name; }

  public void setName(String name)
  { this.name = name; }

  public String getSpecie()
  { return this.specie; }

  public void setSpecie(String specie)
  { this.specie = specie; }

  public String getBreed()
  { return this.breed; }

  public void setBreed(String breed)
  { this.breed = breed; }

  public double getWeight()
  { return this.weight; }

  public void setWeight(double weight)
  { this.weight = weight; }

  public int getEstimatedAge()
  { return this.estimatedAge; }

  public void setEstimatedAge(int estimatedAge)
  { this.estimatedAge = estimatedAge; }

  public String getDescription()
  { return this.description; }

  public void setDescription(String description)
  { this.description = description; }
}
