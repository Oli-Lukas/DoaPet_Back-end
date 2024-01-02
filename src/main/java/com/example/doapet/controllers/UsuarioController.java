package com.example.doapet.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doapet.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {
  
  private final UsuarioRepository usuarioRepository;
}
