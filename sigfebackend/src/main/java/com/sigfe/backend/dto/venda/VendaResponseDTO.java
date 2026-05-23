package com.sigfe.backend.dto.venda;

import com.sigfe.backend.model.ItemTransacao;
import com.sigfe.backend.model.Venda;
import com.sigfe.backend.model.enums.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDTO(
        Long id,
        FormaPagamento formaPagamento,
        String numeroDocumento,
        String status,
        LocalDateTime data,
        BigDecimal valorTotal,        // ← ADICIONAR
        List<ItemVendaResponseDTO> itens  // ← ADICIONAR
) {
    public VendaResponseDTO(Venda venda) {
        this(
                venda.getId(),
                venda.getFormaPagamento(),
                venda.getNumeroDocumento(),
                venda.getStatus() != null ? venda.getStatus().name() : "ABERTA",
                venda.getDataTransacao() != null
                        ? venda.getDataTransacao().atStartOfDay()
                        : LocalDateTime.now(),
                (venda.getItens() != null ? venda.getItens() : List.<ItemTransacao>of()).stream()  // ← calcula valorTotal
                        .filter(i -> i.getPreco() != null && i.getQuantidade() != null)
                        .map(i -> i.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                (venda.getItens() != null ? venda.getItens() : List.<ItemTransacao>of()).stream()  // ← mapeia itens
                        .filter(i -> i.getProduto() != null)
                        .map(ItemVendaResponseDTO::new)
                        .toList()
        );
    }

    // DTO interno para cada item
    public record ItemVendaResponseDTO(
            Long produtoId,
            String nomeProduto,
            Integer quantidade,
            BigDecimal preco
    ) {
        public ItemVendaResponseDTO(ItemTransacao item) {
            this(
                    item.getProduto().getId(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getPreco()
            );
        }
    }
}
