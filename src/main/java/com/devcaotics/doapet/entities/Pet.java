package com.devcaotics.doapet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pet
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column
  private String animal;

  @Column
  private String name;

  @Column
  private String description;  

  public Long getId()
  { return this.id; }

  public void setId(Long id)
  { this.id = id; }

  public String getAnimal()
  { return this.animal; }

  public void setAnimal(String animal)
  { this.animal = animal; }

  public String getName()
  { return this.name; }

  public void setName(String name)
  { this.name = name; }

  public String getDescription()
  { return this.description; }

  public void setDescription(String description)
  { this.description = description; }
}
