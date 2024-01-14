package com.dev.gestao.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtils {

    public static Instant instantAfter(Integer hours) {
        return LocalDateTime.now().plusMinutes(hours).toInstant(ZoneOffset.of("-03:00"));
    }
}
