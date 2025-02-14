package br.com.cadastro_usuario.service;

import br.com.cadastro_usuario.domain.model.User;

public interface UserService {

    User findById(Long id);
    User create(User userToCreate, String cep, String numero);
}
