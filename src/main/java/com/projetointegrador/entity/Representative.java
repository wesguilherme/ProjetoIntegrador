package com.projetointegrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long representativeId;

    @NotNull @NotBlank
    private String cpf;

    @NotNull @NotBlank
    private String name;

    @Embedded
    private Address address;

}
