package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.PessoaRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Pessoa(PessoaRequestDto pessoaRequestDto) {
        this.nome = pessoaRequestDto.nome();
        this.cpf = pessoaRequestDto.cpf();
        this.telefone = pessoaRequestDto.telefone();
        this.email = pessoaRequestDto.email();
    }
}
