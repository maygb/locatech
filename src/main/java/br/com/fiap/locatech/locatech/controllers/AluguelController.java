package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/alugueis")
public class AluguelController {

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);
    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        logger.info("Foi acessado o endpoint de alugueis /alugueis");
        var alugueis = aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguelById(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de alugueis /alugueis/{}", id);
        var aluguel = aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(
           @Valid @RequestBody AluguelRequestDto aluguel) {
        logger.info("Foi acessado o endpoint de alugueis /alugueis para salvar um aluguel");
        aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @PathVariable("id") Long id,
            @RequestBody Aluguel aluguel) {
        logger.info("Foi acessado o endpoint de alugueis /{} para atualizar um aluguel", id);
        aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de alugueis /{} para deletar um aluguel", id);
        aluguelService.deleteAluguel(id);
        return ResponseEntity.noContent().build();
    }

}
