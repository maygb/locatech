package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDto(
        @Schema(description = "ID da pessoa que está alugando o veículo", example = "1")
        @NotNull(message = "Id da pessoa não pode ser nulo")
        Long pessoaId,
        @NotNull(message = "Id do veículo não pode ser nulo")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
