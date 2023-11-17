/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.doapet.repository;


import com.example.doapet.model.Adocao;
import com.example.doapet.model.StatusAdocao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ALUNO
 */
public interface AdocaoRepository extends CrudRepository<Adocao, Long>{
    List<Adocao> findByStatusAdocao(StatusAdocao statusAdocao);
}
