package com.dev.gestao.domain.professor;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FuncionarioType {
    PROFESSOR("Professor"), SECRETARIO("Secretario");
    private String desc;
    @JsonValue
    public String getDesc() {
        return desc;
    }

    FuncionarioType(String desc) {
        this.desc = desc;
    }

    public static FuncionarioType fromString(String text) {
        for (FuncionarioType b : FuncionarioType.values()) {
            if (b.desc.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
