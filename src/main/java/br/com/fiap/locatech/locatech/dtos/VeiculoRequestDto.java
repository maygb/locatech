package br.com.fiap.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record VeiculoRequestDto(
        @NotNull(message = "Marca é obrigatório")
        String marca,
        @NotNull(message = "Modelo é obrigatório")
        String modelo,
        @NotNull(message = "Placa é obrigatório")
        String placa,
        int ano,
        String cor,
        @NotNull(message = "Valor diário é obrigatório")
        BigDecimal valorDiaria
) {
}
