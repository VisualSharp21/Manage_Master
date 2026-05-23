package com.sigfe.backend.service;

import com.sigfe.backend.dto.Item.ItemVendaCreateDTO;
import com.sigfe.backend.dto.venda.VendaCreateDTO;
import com.sigfe.backend.exception.BusinessException;
import com.sigfe.backend.model.Fornecedor;
import com.sigfe.backend.model.ItemTransacao;
import com.sigfe.backend.model.Produto;
import com.sigfe.backend.model.Venda;
import com.sigfe.backend.model.enums.StatusVenda;
import com.sigfe.backend.repository.FornecedorRepository;
import com.sigfe.backend.repository.ProdutoRepository;
import com.sigfe.backend.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final EstoqueService estoqueService;
    private final FinanceiroService financeiroService;

    public VendaService(
            VendaRepository vendaRepository,
            ProdutoRepository produtoRepository,
            FornecedorRepository fornecedorRepository,
            EstoqueService estoqueService,
            FinanceiroService financeiroService
    ) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.estoqueService = estoqueService;
        this.financeiroService = financeiroService;
    }

    @Transactional
    public Venda salvar(VendaCreateDTO dto) {
        if (dto.itens() == null || dto.itens().isEmpty()) {
            throw new BusinessException("A venda deve ter pelo menos um item");
        }

        Venda venda = new Venda();
        venda.setStatus(StatusVenda.PAGA);

        if (dto.fornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                    .orElseThrow(() -> new BusinessException("Fornecedor nao encontrado"));
            venda.setFornecedor(fornecedor);
        }

        venda.setFormaPagamento(dto.formaPagamento());
        venda.setNumeroDocumento(dto.numeroDocumento());
        venda.setUsuario("Vendedor Master");

        List<ItemTransacao> itens = new ArrayList<>();
        for (ItemVendaCreateDTO itemDto : dto.itens()) {
            if (itemDto.quantidade() == null || itemDto.quantidade() <= 0) {
                throw new BusinessException("Quantidade invalida para item da venda");
            }
            if (itemDto.preco() == null || itemDto.preco().signum() <= 0) {
                throw new BusinessException("Preco invalido para item da venda");
            }

            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new BusinessException("Produto nao encontrado: ID " + itemDto.produtoId()));

            estoqueService.baixarEstoque(itemDto.produtoId(), itemDto.quantidade());

            ItemTransacao item = new ItemTransacao(produto, itemDto.quantidade(), itemDto.preco());
            item.setTransacao(venda);
            itens.add(item);
        }

        venda.setItens(itens);
        Venda vendaSalva = vendaRepository.save(venda);
        financeiroService.registrarVenda(vendaSalva);
        return vendaSalva;
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.buscarPorIdComItens(id)
                .orElseThrow(() -> new BusinessException("Venda nao encontrada"));
    }

    public List<Venda> listarTodas() {
        return vendaRepository.listarTodasComItens();
    }
}
