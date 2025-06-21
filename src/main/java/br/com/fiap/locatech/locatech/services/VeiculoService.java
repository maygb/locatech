package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.VeiculoRequestDto;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> findAllVeiculos(int page, int size) {
        int offset = (page - 1) * size;
        return veiculoRepository.findAll(size, offset);
    }

    public Optional<Veiculo> findVeiculoById(Long id) {
        return Optional.ofNullable(veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado")));
    }

    public void saveVeiculo(VeiculoRequestDto veiculo) {
        var veiculoEntity = new Veiculo(veiculo);
        var save = veiculoRepository.save(veiculoEntity);
        Assert.state(save == 1, "Erro ao salvar veículo " + veiculoEntity.getId());
    }

    public void updateVeiculo(Veiculo veiculo, Long id) {
        var update = veiculoRepository.update(veiculo, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Veiculo não encontrado");
        }
    }

    public void deleteVeiculo(Long id) {
        var delete = veiculoRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Veiculo não encontrado");
        }
    }
}
