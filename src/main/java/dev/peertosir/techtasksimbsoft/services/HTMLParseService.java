package dev.peertosir.techtasksimbsoft.services;

import dev.peertosir.techtasksimbsoft.exceptions.UrlNotValidException;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.IOException;
import java.util.Map;

public interface HTMLParseService {

    String[] getWordsFromHtml(String url) throws IOException;

    Map<String, Long> getWordsFrequencyFromHtml(String url) throws IOException;

    static void checkUrl(String url) throws UrlNotValidException {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        boolean isValid = urlValidator.isValid(url);
        if (!isValid) {
            throw new UrlNotValidException("Provided URL is not valid");
        }
    }
}
