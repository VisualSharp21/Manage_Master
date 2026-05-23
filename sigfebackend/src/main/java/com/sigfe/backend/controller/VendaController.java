package com.sigfe.backend.controller;

import com.sigfe.backend.dto.venda.VendaCreateDTO;
import com.sigfe.backend.dto.venda.VendaResponseDTO;
import com.sigfe.backend.model.Venda;
import com.sigfe.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@RequestBody @Valid VendaCreateDTO dto) {
        Venda venda = vendaService.salvar(dto);
        return ResponseEntity.ok(new VendaResponseDTO(venda));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(new VendaResponseDTO(vendaService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(
                vendaService.listarTodas().stream()
                        .map(VendaResponseDTO::new)
                        .toList()
        );
    }
}
