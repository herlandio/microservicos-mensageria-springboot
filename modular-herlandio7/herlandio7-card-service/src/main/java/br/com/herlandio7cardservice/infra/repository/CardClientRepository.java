package br.com.herlandio7cardservice.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.herlandio7cardservice.domain.CardClient;

public interface CardClientRepository extends JpaRepository<CardClient, Long> {
    List<CardClient> findByCpf(String cpf);
}
