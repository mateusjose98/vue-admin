package com.dev.gestao.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErroCampo {

    private String field;
    private String errorCode;
    private String description;

}
