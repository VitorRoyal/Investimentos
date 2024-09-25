package br.com.investimentos.controller;

import br.com.investimentos.controller.dto.AtualizaUsuarioDto;
import br.com.investimentos.controller.dto.CarteiraResponseDto;
import br.com.investimentos.controller.dto.CriaCarteiraDto;
import br.com.investimentos.controller.dto.CriaUsuarioDto;
import br.com.investimentos.entity.Usuario;
import br.com.investimentos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CriaUsuarioDto dto) {
        usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable String id) {
        var usuario = usuarioService.buscarUsuarioPeloId(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        var usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String id,
                                                    @RequestBody AtualizaUsuarioDto dto) {
        usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{usuarioId}/carteiras")
    public ResponseEntity<Void> criarCarteira(@PathVariable String usuarioId,
                                              @RequestBody CriaCarteiraDto dto) {
        usuarioService.criarCarteira(usuarioId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{usuarioId}/carteiras")
    public ResponseEntity<List<CarteiraResponseDto>> listarCarteira(@PathVariable String usuarioId) {
        var carteiras = usuarioService.listarCarteira(usuarioId);
        return ResponseEntity.ok(carteiras);
    }


}
