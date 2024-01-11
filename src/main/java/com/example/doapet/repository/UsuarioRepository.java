/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doapet.repository;

import com.example.doapet.model.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.example.doapet.model.TipoUsuario;
import java.util.List;


/**
 *
 * @author ALUNO
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}
