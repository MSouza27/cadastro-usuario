package br.com.cadastro_usuario.controller;

import br.com.cadastro_usuario.domain.model.User;
import br.com.cadastro_usuario.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var cep = userToCreate.getData().getAddress().getCep();
        var numero = userToCreate.getData().getAddress().getNumero();

        var userCreated = userService.create(userToCreate, cep, numero);
        URI location = URI.create("/users/" + userCreated.getId());
        return ResponseEntity.created(location).body(userCreated);
    }
}
