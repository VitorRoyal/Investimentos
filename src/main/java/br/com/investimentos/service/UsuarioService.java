package br.com.investimentos.service;

import br.com.investimentos.controller.dto.AtualizaUsuarioDto;
import br.com.investimentos.controller.dto.CarteiraResponseDto;
import br.com.investimentos.controller.dto.CriaCarteiraDto;
import br.com.investimentos.controller.dto.CriaUsuarioDto;
import br.com.investimentos.entity.Carteira;
import br.com.investimentos.entity.EnderecoCobranca;
import br.com.investimentos.entity.Usuario;
import br.com.investimentos.exceptions.UsuarioInvalidoException;
import br.com.investimentos.exceptions.UsuarioNotFoundException;
import br.com.investimentos.repository.CarteiraRepository;
import br.com.investimentos.repository.EnderecoCobrancaRepository;
import br.com.investimentos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private CarteiraRepository carteiraRepository;

    private EnderecoCobrancaRepository enderecoCobrancaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, CarteiraRepository carteiraRepository, EnderecoCobrancaRepository enderecoCobrancaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carteiraRepository = carteiraRepository;
        this.enderecoCobrancaRepository = enderecoCobrancaRepository;
    }

    public UUID cadastrarUsuario(CriaUsuarioDto dto) {

        var usuario = new Usuario(UUID.randomUUID(),
                dto.nome(),
                dto.email(),
                dto.senha(),
                Instant.now(),
                null);

        if(usuarioRepository.existsByEmail(dto.email())){
            throw new UsuarioInvalidoException("Email já cadastrado");
        }

        var usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioSalvo.getUsuarioId();
    }

    public Optional<Usuario> buscarUsuarioPeloId(String usuarioId) {
         if(!usuarioRepository.existsById(UUID.fromString(usuarioId))){
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
        return usuarioRepository.findById(UUID.fromString(usuarioId));
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public void atualizarUsuario(String usuarioId, AtualizaUsuarioDto dto) {
        var id = UUID.fromString(usuarioId);
        var usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            var usuarioAtualizado = usuario.get();
            if(dto.nome() != null){
                usuarioAtualizado.setNome(dto.nome());
            }
            if(dto.senha() != null){
                usuarioAtualizado.setSenha(dto.senha());
            }
            usuarioRepository.save(usuarioAtualizado);
        } else {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }

    }

    public void deletarUsuario(String usuarioId) {
        var id = UUID.fromString(usuarioId);
        var usarioExiste = usuarioRepository.existsById(id);
        if(usarioExiste){
            usuarioRepository.deleteById(id);
        } else {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }

    }

    public void criarCarteira(String usuarioId, CriaCarteiraDto dto) {
        var usuario = usuarioRepository.findById(UUID.fromString(usuarioId))
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        var carteira = new Carteira(
                UUID.randomUUID(),
                dto.descricao(),
                usuario,
                null,
                new ArrayList<>()
        );

        var carteiraCriada = carteiraRepository.save(carteira);

        var enderecoCobranca = new EnderecoCobranca(
                carteiraCriada.getCarteiraId(),
                carteira,
                dto.endereco(),
                dto.numero()
        );

        enderecoCobrancaRepository.save(enderecoCobranca);

    }

    public List<CarteiraResponseDto> listarCarteira(String usuarioId) {
        var usuario = usuarioRepository.findById(UUID.fromString(usuarioId))
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        return usuario.getCarteiras()
                .stream()
                .map(carteira -> new CarteiraResponseDto(carteira.getCarteiraId().toString(), carteira.getDescricao()))
                .toList();
    }
}
