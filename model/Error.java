package br.rr.gov.digigov.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
public class Error {
    private final OffsetDateTime timestamp;
    private final String title;
    private final String detail;
    private final Integer status;
    private final String error;

    public Error(OffsetDateTime timestamp, Type type, String detail, HttpStatus status) {
        this.timestamp = timestamp;
        this.status = status != null ? status.value() : null;
        this.error = status != null ? status.getReasonPhrase(): null;
        this.title = type.getTitle();
        this.detail = detail;
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        RECURSO_NAO_ENCONTRADO("Recurso não encontrado."),
        RECURSO_EM_CONFLITO("Recurso em conflito."),

        VALORES_IMCOMPREENSIVEIS("Atributos incompreensíveis."),
        VALORES_INVALIDOS("Valores inválidos."),

        ATRIBUTOS_INCOMPREENSIVEIS("Atributos incompreensíveis."),
        ATRIBUTOS_INVALIDOS("Atributos inválidos."),

        ATRIBUTO_INVALIDO("Atributo inválido."),
        ERRO_INTERNO("Erro interno.");
        private String title;
    }
}
