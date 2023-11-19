/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.repository;

import com.example.doapet.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ALUNO
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    UserDetails findByEmail(String email);
    Usuario save(Usuario u);
}
