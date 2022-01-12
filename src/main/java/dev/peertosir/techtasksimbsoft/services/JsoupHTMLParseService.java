package dev.peertosir.techtasksimbsoft.services;

import dev.peertosir.techtasksimbsoft.domain.History;
import dev.peertosir.techtasksimbsoft.domain.Word;
import dev.peertosir.techtasksimbsoft.repositories.HistoryRepository;
import dev.peertosir.techtasksimbsoft.repositories.WordRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsoupHTMLParseService implements HTMLParseService {

    private final HistoryRepository historyRepository;
    private final WordRepository wordRepository;
    private final Logger logger = LoggerFactory.getLogger(JsoupHTMLParseService.class);

    public JsoupHTMLParseService(HistoryRepository historyRepository, WordRepository wordRepository) {
        this.historyRepository = historyRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public String[] getWordsFromHtml(String url) throws IOException {
        logger.info("Checking if URL: " + url + " is valid");
        HTMLParseService.checkUrl(url);
        logger.info("URL is valid! Processing HTML page...");

        Element pageBody = Jsoup.connect(url).get().body();
        String parsedText = traverse(pageBody);
        String[] processedStrings = splitByDelimiters(parsedText);
        logger.info("HTML page text parsed");
        return processedStrings;
    }

    @Override
    public Map<String, Long> getWordsFrequencyFromHtml(String url) throws IOException {
        String[] data = getWordsFromHtml(url);
        Map<String, Long> buffer = new HashMap<>();

        for (String word :
                data) {
            if (word.isEmpty()) {
                continue;
            }

            if (word.chars().anyMatch(ch -> !Character.isAlphabetic(ch))) {
                continue;
            }

            String normalizedString = word.toUpperCase();

            if (!buffer.containsKey(normalizedString)) {
                buffer.put(normalizedString, 1L);
            } else {
                buffer.put(normalizedString, buffer.get(normalizedString) + 1L);
            }
        }

        submitResultsToDb(buffer, url);

        return buffer;
    }

    private void submitResultsToDb(Map<String, Long> results, String url) {
        logger.info("Submitting results to DB...");
        History history = new History(url, Instant.now());
        history = historyRepository.save(history);

        for (Map.Entry<String, Long> entry:
             results.entrySet()) {
            wordRepository.save(new Word(entry.getKey(), entry.getValue(), history));
        }
        logger.info("Results submitted at: " + Instant.now());
    }

    private String traverse(Element element) {
        StringBuilder text = new StringBuilder();
        Elements tags = element.select("*");
        for (Element tag :
                tags) {
            for (TextNode tn :
                    tag.textNodes()) {
                String tagText = tn.text().trim();
                if (tagText.length() > 0) {
                    text.append(tagText).append(" ");
                }
            }
        }
        return text.toString();
    }

    private String[] splitByDelimiters(String input) {
        return input.split("\\s|,|\\.|!|\\?|\"|;|:|\\[|\\]|\\(|\\)");
    }
}
