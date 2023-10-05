package com.devcaotics.doapet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcaotics.doapet.entities.Ong;
import com.devcaotics.doapet.repositories.OngRepository;

@RestController
@RequestMapping("ong")
public class OngController
{
  @Autowired
  private OngRepository repository;

  @PostMapping
  public void create(@RequestBody Ong ong)
  {
    System.out.println(ong.getName());
    System.out.println(ong.getEmail());
  }
}
