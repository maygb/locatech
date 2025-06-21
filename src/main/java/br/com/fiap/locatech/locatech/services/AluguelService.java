package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id) {
        return Optional.ofNullable(aluguelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")
        ));
    }

    public void saveAluguel(AluguelRequestDto aluguel) {
        var aluguelEntity = calculaAluguel(aluguel);
        var save = aluguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguel.pessoaId());
    }

    public void updateAluguel(Aluguel aluguel, Long id) {
        var update = aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }
    }

    public void deleteAluguel(Long id) {
        var delete = aluguelRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }
    }

    private Aluguel calculaAluguel(AluguelRequestDto aluguelDto) {
        var veiculo = veiculoRepository.findById(aluguelDto.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));

        var quantidadeDias = BigDecimal.valueOf(aluguelDto.dataFim().getDayOfYear() - aluguelDto.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDias);
        return new Aluguel(aluguelDto, valor);
    }
}
