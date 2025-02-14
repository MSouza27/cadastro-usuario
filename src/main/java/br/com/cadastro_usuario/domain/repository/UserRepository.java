package br.com.cadastro_usuario.domain.repository;

import br.com.cadastro_usuario.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByDataCpf(String userCpf);
}
