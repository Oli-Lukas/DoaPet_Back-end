package com.example.doapet.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.repository.EventoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("evento")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventoController {
  
  private final EventoRepository eventoRepository;
}
