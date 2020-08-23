package br.rr.gov.digigov.common.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@JsonInclude(Include.NON_NULL)
public class ResponseError extends Error {

    private final List<Error> subErros;

    public ResponseError(Type type, HttpStatus status) {
        super(OffsetDateTime.now(), type, null, status);
        this.subErros = new ArrayList<>();
    }

    public ResponseError(Type type, String detail, HttpStatus status) {
        super(OffsetDateTime.now(), type, detail, status);
        this.subErros = new ArrayList<>();
    }
}
