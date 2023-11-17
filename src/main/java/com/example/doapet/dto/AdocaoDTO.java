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
public record AdocaoDTO(String tituloAdoc, String descricaoAdoc, String nomeAnim,String especieAnim,String racaAnim,double pesoAnim, int idadeAnim, String descricaoAnim, MultipartFile fotoAnim) {
    
}
