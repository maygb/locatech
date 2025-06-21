package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Aluguel {
    private Long id;
    private Long pessoaId;
    private Long veiculoId;
    private String veiculoModelo;
    private String veiculoPlaca;
    private String pessoaCpf;
    private String pessoaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDto aluguelDto, BigDecimal valorTotal) {
        this.pessoaId =  aluguelDto.pessoaId();
        this.veiculoId = aluguelDto.veiculoId();
        this.dataInicio = aluguelDto.dataInicio();
        this.dataFim =  aluguelDto.dataFim();
        this.valorTotal = valorTotal;
    }

}
