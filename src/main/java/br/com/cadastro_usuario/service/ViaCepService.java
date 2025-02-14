package br.com.cadastro_usuario.service;

import br.com.cadastro_usuario.domain.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {

    private final WebClient webClient;

    // Construtor que recebe um WebClient configurado
    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br").build();
    }

    //Método para consultar o CEP usando WebClient
    public Mono<Address> consultarCep(String cep){
        String path = "/ws/" + cep + "/json/";
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(Address.class);
    }
}
