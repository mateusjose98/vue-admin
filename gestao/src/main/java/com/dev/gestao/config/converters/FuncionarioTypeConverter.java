package com.dev.gestao.config.converters;

import com.dev.gestao.domain.professor.FuncionarioType;
import org.hibernate.annotations.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioTypeConverter implements Converter<String, FuncionarioType> {
    @Override
    public FuncionarioType convert(String value) {

        return FuncionarioType.fromString(value);
    }
}
