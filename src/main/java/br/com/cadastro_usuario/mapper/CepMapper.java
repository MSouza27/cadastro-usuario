package br.com.cadastro_usuario.mapper;

import br.com.cadastro_usuario.domain.model.Address;

public class CepMapper {
    public static Address toCep(Address dados){
        if (dados == null){
            return null;
        }
        return new Address(
                dados.getCep(),
                dados.getLogradouro(),
                dados.getComplemento(),
                dados.getBairro(),
                dados.getLocalidade(),
                dados.getUf(),
                dados.getEstado(),
                dados.getRegiao(),
                dados.getDdd(),
                ""
        );
    }
}
