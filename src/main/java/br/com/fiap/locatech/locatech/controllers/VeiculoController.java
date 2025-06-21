package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.VeiculoRequestDto;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "CRUD para gerenciar veículos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            summary = "Listar veículos",
            description = "Endpoint para listar todos os veículos com paginação",
            responses = {
                    @ApiResponse(description = "Lista de veículos retornada com sucesso", responseCode = "200"),
            }
    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        logger.info("Foi acessado o endpoint de veículos /veiculos");
        var veiculos = veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculoById(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de veículos /veiculos/{}", id);
        var veiculo = veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping(produces = "application/vnd.locatech.v1+json")
    public ResponseEntity<Void> saveVeiculo(
          @Valid @RequestBody VeiculoRequestDto veiculo) {
        logger.info("Foi acessado o endpoint de veículos /veiculos para salvar um veículo");
        veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @PostMapping(produces = "application/vnd.locatech.v2+json")
    public ResponseEntity<Void> saveVeiculoV2(
            @Valid @RequestBody VeiculoRequestDto veiculo) {
        logger.info("Foi acessado o endpoint de veículos /veiculos para salvar um veículo");
        veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @PathVariable("id") Long id,
            @RequestBody Veiculo veiculo) {
        logger.info("Foi acessado o endpoint de veículos /{} para atualizar um veículo", id);
        veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de veículos /{} para deletar um veículo", id);
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
