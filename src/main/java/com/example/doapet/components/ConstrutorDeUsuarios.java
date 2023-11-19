package com.example.doapet.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.doapet.model.TipoUsuario;
import com.example.doapet.model.Usuario;
import com.example.doapet.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ConstrutorDeUsuarios {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostConstruct
  public void init() {
    usuarioRepository.deleteAll();

    usuarioRepository.save(new Usuario("Raul Manuel Thales Lopes"     , "raul.manuel@gmail.com"      , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "PA, Belém, Pedreira, Passagem Santa Helena"                 , "(91) 98508-6973"));
    usuarioRepository.save(new Usuario("Lúcia Elisa Nina Alves"       , "lucia.alves@outlook.com"    , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "RN, Parnamirim, Bela Parnamirim, Rua Pedro Barbosa"         , "(84) 99635-5425"));
    usuarioRepository.save(new Usuario("Leandro Fábio Fernandes"      , "leandro.fernandes@gmail.com", passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "AL, Maceió, Cidade Universitária, Quadra H4"                , "(82) 98400-6706"));
    usuarioRepository.save(new Usuario("Marlene Yasmin Brenda Novaes" , "marlene.novaes@outlook.com" , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "TO, Palmas, Plano Diretor Sul, Quadra 309 Sul Avenida NS 15", "(63) 99774-8321"));
    usuarioRepository.save(new Usuario("Manuel Antonio Assunção"      , "manuel.assuncao@gmail.com"  , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "SP, São Roque, Centro, Rua Quintino Bocaiúva"               , "(11) 99521-2431"));
  }
}
