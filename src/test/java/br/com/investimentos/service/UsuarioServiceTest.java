package br.com.investimentos.service;

import br.com.investimentos.controller.dto.AtualizaUsuarioDto;
import br.com.investimentos.controller.dto.CriaUsuarioDto;
import br.com.investimentos.entity.Usuario;
import br.com.investimentos.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Captor
    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class cadastrarUsuario{

        @Test
        @DisplayName("shoud create a user with sucess")
        void deveCadastrarUsuario(){
            //Arrange
            var usuario = new Usuario(
                    UUID.randomUUID(),
                    "nome",
                    "email",
                    "123",
                    Instant.now(),
                    null);

            doReturn(usuario).when(usuarioRepository).save(usuarioArgumentCaptor.capture());
            var input = new CriaUsuarioDto(
                    "nome",
                    "email",
                    "123");

            //Act
            var output =  usuarioService.cadastrarUsuario(input);

            //Assert
            assertNotNull(output);

            var usuarioCapturado = usuarioArgumentCaptor.getValue();

            assertEquals(input.nome(), usuarioCapturado.getNome());
            assertEquals(input.email(), usuarioCapturado.getEmail());
            assertEquals(input.senha(), usuarioCapturado.getSenha());
        }

        @Test
        @DisplayName("should throw exception when some error occurs")
        void deveJogarExceptionQuandoAlgumErroAparecer() {
            // Arrange
            var input = new CriaUsuarioDto("nome", "email", "123");
            doThrow(new RuntimeException("Erro ao salvar usuário")).when(usuarioRepository).save(any());

            // Act & Assert
            assertThrows(RuntimeException.class, () -> usuarioService.cadastrarUsuario(input));
        }

    }

    @Nested
    class buscarUsuarioPeloId{

        @Test
        @DisplayName("should return a user when it exists")
        void deveRetornarUsuarioQuandoExistir(){
            //Arrange
            var usuario = new Usuario(
                    UUID.randomUUID(),
                    "nome",
                    "email",
                    "123",
                    Instant.now(),
                    null);

            doReturn(Optional.of(usuario)).when(usuarioRepository).findById(uuidArgumentCaptor.capture());

            //Act
            var output = usuarioService.buscarUsuarioPeloId(usuario.getUsuarioId().toString());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(usuario.getUsuarioId(), uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("should return empty when user does not exist")
        void deveRetornarVazioQuandoUsuarioNaoExistir(){
            //Arrange
            var usuarioId = UUID.randomUUID();

            doReturn(Optional.empty()).when(usuarioRepository).findById(uuidArgumentCaptor.capture());

            //Act
            var output = usuarioService.buscarUsuarioPeloId(usuarioId.toString());

            //Assert
            assertTrue(output.isEmpty());
            assertEquals(usuarioId, uuidArgumentCaptor.getValue());

        }

    }

    @Nested
    class buscarTodosUsuarios{

        @Test
        @DisplayName("should return all users")
        void deveRetornarTodosUsuarios(){
            //Arrange
            var usuario = new Usuario(
                    UUID.randomUUID(),
                    "nome",
                    "email",
                    "123",
                    Instant.now(),
                    null);

            doReturn(List.of(usuario)).when(usuarioRepository).findAll();

            //Act
            var output = usuarioService.buscarTodosUsuarios();

            //Assert
            assertFalse(output.isEmpty());
            assertEquals(1, output.size());
        }

    }

    @Nested
    class atualizarUsuario{

        @Test
        @DisplayName("should update user when it exists")
        void deveAtualizarUsuarioQuandoExistir(){
            //Arrange
            var atualizaUsuarioDto = new AtualizaUsuarioDto(
                    "novo nome",
                    "nova senha");
            var usuario = new Usuario(
                    UUID.randomUUID(),
                    "nome",
                    "email",
                    "123",
                    Instant.now(),
                    null);

            doReturn(Optional.of(usuario)).when(usuarioRepository).findById(uuidArgumentCaptor.capture());
            doReturn(usuario).when(usuarioRepository).save(usuarioArgumentCaptor.capture());

            //Act
            usuarioService.atualizarUsuario(usuario.getUsuarioId().toString(), atualizaUsuarioDto);


            //Assert
            assertEquals(usuario.getUsuarioId(), uuidArgumentCaptor.getValue());
            var usuarioAtualizado = usuarioArgumentCaptor.getValue();
            assertEquals(atualizaUsuarioDto.nome(), usuarioAtualizado.getNome());
            assertEquals(atualizaUsuarioDto.senha(), usuarioAtualizado.getSenha());

            verify(usuarioRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(usuarioRepository, times(1)).save(usuario);
        }

        @Test
        @DisplayName("should throw exception when user does not exist")
        void deveJogarExceptionQuandoUsuarioNaoExistir(){
            //Arrange
            var usuarioId = UUID.randomUUID();
            var dto = new AtualizaUsuarioDto("novo nome", "nova senha");

            doReturn(Optional.empty()).when(usuarioRepository).findById(uuidArgumentCaptor.capture());

            //Act & Assert
            assertThrows(RuntimeException.class, () -> usuarioService.atualizarUsuario(usuarioId.toString(), dto));
        }

    }

    @Nested
    class deletarUsuario{

        @Test
        @DisplayName("should delete user when it exists")
        void deveDeletarUsuarioQuandoExistir(){
            //Arrange
            var usuario = new Usuario(
                    UUID.randomUUID(),
                    "nome",
                    "email",
                    "123",
                    Instant.now(),
                    null);

            doReturn(true).when(usuarioRepository).existsById(uuidArgumentCaptor.capture());

            //Act
            usuarioService.deletarUsuario(usuario.getUsuarioId().toString());

            //Assert
            assertEquals(usuario.getUsuarioId(), uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("should not delete user when it does not exist")
        void naoDeveDeletarUsuarioQuandoNaoExistir(){
            //Arrange
            var usuarioId = UUID.randomUUID();

            doReturn(false).when(usuarioRepository).existsById(uuidArgumentCaptor.capture());

            //Act
            usuarioService.deletarUsuario(usuarioId.toString());

            //Assert
            assertEquals(usuarioId, uuidArgumentCaptor.getValue());
        }

    }

}