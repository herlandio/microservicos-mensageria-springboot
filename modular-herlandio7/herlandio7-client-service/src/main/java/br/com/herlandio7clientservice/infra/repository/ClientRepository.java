package br.com.herlandio7clientservice.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.herlandio7clientservice.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> { // Client representa a entidade em domain e long
                                                                        // o id
    Optional<Client> findByCpf(String cpf); // cria metodo que não existe
}
