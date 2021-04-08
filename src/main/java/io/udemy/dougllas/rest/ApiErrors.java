package io.udemy.dougllas.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> eita_erros;

    /* Transformar o objeto/String Exception em uma array list */
    public ApiErrors(String mensagemErro) {
        this.eita_erros = Arrays.asList(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.eita_erros = errors;
    }
}
