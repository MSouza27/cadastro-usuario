package br.com.cadastro_usuario.service.impl;

import br.com.cadastro_usuario.domain.model.Address;
import br.com.cadastro_usuario.domain.model.User;
import br.com.cadastro_usuario.domain.repository.UserRepository;
import br.com.cadastro_usuario.service.UserService;
import br.com.cadastro_usuario.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ViaCepService viaCepService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ViaCepService viaCepService) {
        this.userRepository = userRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    @Override
    public User create(User userToCreate, String cep, String numero) {
        if (userRepository.existsByDataCpf(userToCreate.getData().getCpf())) {
            throw new IllegalArgumentException("This CPF number already exists.");
        }

        // Consulta o CEP e converte para Address
        Address address = viaCepService.consultarCep(cep).block();

        if (address == null) {
            throw new IllegalArgumentException("Invalid CEP or service unavailable.");
        }

        // Define o número do endereço
        address.setNumero(numero);
        userToCreate.getData().setAddress(address);

        return userRepository.save(userToCreate);
    }

}