package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.PessoaRequestDto;
import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.services.PessoaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        logger.info("Foi acessado o endpoint de pessoas /pessoas");
        var pessoas = pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoaById(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de pessoas /pessoas/{}", id);
        var pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(
           @Valid @RequestBody PessoaRequestDto pessoa) {
        logger.info("Foi acessado o endpoint de pessoas /pessoas para salvar uma pessoa");
        pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @PathVariable("id") Long id,
            @RequestBody Pessoa pessoa) {
        logger.info("Foi acessado o endpoint de pessoas /{} para atualizar uma pessoa", id);
        pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
            @PathVariable("id") Long id) {
        logger.info("Foi acessado o endpoint de pessoas /{} para deletar uma pessoa", id);
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }

}
