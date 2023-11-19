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

    this.deletarTodosOsUsuarios();
    this.adicionarUsuariosIndividuais();
    this.adicionarUsuariosOngs();
  }

  private void deletarTodosOsUsuarios()
  { usuarioRepository.deleteAll(); }

  private void adicionarUsuariosIndividuais() {

    usuarioRepository.save(new Usuario("Raul Manuel Thales Lopes"     , "raul.manuel@gmail.com"      , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "PA, Belém, Pedreira, Passagem Santa Helena"                 , "(91) 98508-6973"));
    usuarioRepository.save(new Usuario("Lúcia Elisa Nina Alves"       , "lucia.alves@outlook.com"    , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "RN, Parnamirim, Bela Parnamirim, Rua Pedro Barbosa"         , "(84) 99635-5425"));
    usuarioRepository.save(new Usuario("Leandro Fábio Fernandes"      , "leandro.fernandes@gmail.com", passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "AL, Maceió, Cidade Universitária, Quadra H4"                , "(82) 98400-6706"));
    usuarioRepository.save(new Usuario("Marlene Yasmin Brenda Novaes" , "marlene.novaes@outlook.com" , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "TO, Palmas, Plano Diretor Sul, Quadra 309 Sul Avenida NS 15", "(63) 99774-8321"));
    usuarioRepository.save(new Usuario("Manuel Antonio Assunção"      , "manuel.assuncao@gmail.com"  , passwordEncoder.encode("12345"), TipoUsuario.INDIVIDUAL, "SP, São Roque, Centro, Rua Quintino Bocaiúva"               , "(11) 99521-2431"));
  }

  private void adicionarUsuariosOngs() {

    usuarioRepository.save(new Usuario("Dominacao Mundial pelos Gatos"     , "dominacao.dos.gatos@gmail.com", passwordEncoder.encode("12345"), TipoUsuario.ONG, "AM, Manaus, Coroado, Rua Tupiá"                                        , "(92) 99466-3141"));
    usuarioRepository.save(new Usuario("Associação dos Cachorros Caramelos", "cachorro.caramelo@gmail.com"  , passwordEncoder.encode("12345"), TipoUsuario.ONG, "GO, Aparecida de Goiâna, Sítio Santa Luzia Residencial, Rua P1"        , "(62) 99315-7641"));
    usuarioRepository.save(new Usuario("Tartarugas Ninja de Araçatuba"     , "tartarugas.ninja@gmail.com"   , passwordEncoder.encode("12345"), TipoUsuario.ONG, "SP, Araçatuba, Residencial Jardim Atlântico, Rua Manoel Munhoz Correia", "(18) 98376-0028"));
    usuarioRepository.save(new Usuario("Aliança dos Cães Astronautas"      , "caes.astronautas@gmail.com"   , passwordEncoder.encode("12345"), TipoUsuario.ONG, "MG, Teófilo Otoni, São Francisco, Rua Coronel Antônio Barbosa"         , "(33) 99598-1666"));
    usuarioRepository.save(new Usuario("Assembleia Felina do Maranhão"     , "assembleia.felina@gmail.com"  , passwordEncoder.encode("12345"), TipoUsuario.ONG, "MA, São Luís, Rio dos Cachorros, Rua Nova"                             , "(98) 98344-6621"));
  }
}
