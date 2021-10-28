package com.projetointegrador.advisor;

import lombok.Data;

@Data
public class ErrorDto {

    private String field;
    private String error;

    public ErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
