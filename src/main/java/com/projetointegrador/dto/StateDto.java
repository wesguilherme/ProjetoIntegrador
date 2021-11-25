package com.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class StateDto {

    private String cep;
    private String nome;
    private double valorFrete;
}
