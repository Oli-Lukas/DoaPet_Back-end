/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.example.doapet.dto;

import com.example.doapet.model.TipoUsuario;

/**
 *
 * @author euluc
 */
public record CadastroDTO(String nome, String email, String senha, String endereco, String telefone, TipoUsuario tipoUsuario ) {

}
