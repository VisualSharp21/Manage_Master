package com.sigfe.backend.dto.produto;

import com.sigfe.backend.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String marca,
        BigDecimal preco,
        int quantidade,
        LocalDate validade,
        String nomeCategoria,
        Long fornecedorId,
        String nomeFornecedor,
        boolean vencido
) {
    public ProdutoResponseDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getValidade(),
                produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem Categoria",
                produto.getFornecedor() != null ? produto.getFornecedor().getId() : null,
                produto.getFornecedor() != null ? produto.getFornecedor().getNome() : "Sem Fornecedor",
                produto.estaVencido()
        );
    }
}
