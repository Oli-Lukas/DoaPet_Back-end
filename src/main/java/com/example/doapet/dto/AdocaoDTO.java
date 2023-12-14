/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.example.doapet.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ALUNO
 */
public record AdocaoDTO(
  String  tituloAdocao,
  String  descricaoAdocao,
  String  nomeAnimal,
  String  especieAnimal,
  String  racaAnimal,
  Double  pesoAnimal,
  Integer idadeAnimal,
  String  descricaoAnimal,
  MultipartFile fotoAnimal
) {}
