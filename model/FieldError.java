package br.rr.gov.digigov.common.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@JsonInclude(Include.NON_NULL)
public class FieldError extends Error {
    private final String campo;

    public FieldError(String campo, String detail) {
        super(null, Type.ATRIBUTO_INVALIDO, detail, null);
        this.campo = campo;
    }
}
