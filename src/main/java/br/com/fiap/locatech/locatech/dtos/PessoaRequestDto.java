package br.com.fiap.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PessoaRequestDto(
        @NotNull(message = "Nome não pode ser nulo")
        String nome,
        @NotNull(message = "CPF não pode ser nulo")
        @Pattern(regexp = "^\\d{11}$", message = "CPF não é válido")
        String cpf,
        @NotNull(message = "Telefone não pode ser nulo")
        String telefone,
        String email
) {
}
