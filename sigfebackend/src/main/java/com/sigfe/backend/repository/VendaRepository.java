package com.sigfe.backend.repository;

import com.sigfe.backend.model.Venda;
import com.sigfe.backend.model.enums.StatusVenda;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository <Venda, Long> {

    List<Venda> findByDataTransacaoBetweenAndStatus (LocalDate inicio,
                                            LocalDate fim,
                                            StatusVenda status);

    @EntityGraph(attributePaths = {"itens", "itens.produto"})
    @Query("select v from Venda v where v.id = :id")
    Optional<Venda> buscarPorIdComItens(@Param("id") Long id);

    @EntityGraph(attributePaths = {"itens", "itens.produto"})
    @Query("select v from Venda v")
    List<Venda> listarTodasComItens();

}

/*No Spring Boot, um Repository (Repositório) é uma camada
de abstração que serve para gerenciar o acesso aos dados.
Ele funciona como uma "ponte" entre
na aplicação e no banco de dados.*/
