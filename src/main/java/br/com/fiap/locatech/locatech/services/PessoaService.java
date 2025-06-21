package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.PessoaRequestDto;
import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoas(int page, int size) {
        int offset = (page - 1) * size;
        return pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findPessoaById(Long id) {
        return Optional.ofNullable(pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada")));
    }

    public void savePessoa(PessoaRequestDto pessoa) {
        var pessoaEntity = convertToEntity(pessoa);
        var save = pessoaRepository.save(pessoaEntity);
        Assert.state(save == 1, "Erro ao salvar pessoa " + pessoa.nome());
    }

    public void updatePessoa(Pessoa pessoa, Long id) {
        var update = pessoaRepository.update(pessoa, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    public void deletePessoa(Long id) {
        var delete = pessoaRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    private Pessoa convertToEntity(PessoaRequestDto pessoaRequestDto) {
        return new Pessoa(pessoaRequestDto);
    }
}
