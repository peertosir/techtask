package dev.peertosir.techtasksimbsoft.services;

import dev.peertosir.techtasksimbsoft.exceptions.UrlNotValidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HTMLParseServiceTest {

    @Test
    void check_url_throws_custom_exception_when_protocol_not_valid() {
        assertThrows(UrlNotValidException.class, () -> {
            HTMLParseService.checkUrl("ftp://localhost");
        });
    }

    @Test
    void check_url_throws_custom_exception_when_URL_not_valid() {
        assertThrows(UrlNotValidException.class, () -> {
            HTMLParseService.checkUrl("http://localhost.losl");
        });
    }


}