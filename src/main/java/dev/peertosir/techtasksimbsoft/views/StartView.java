package dev.peertosir.techtasksimbsoft.views;

import dev.peertosir.techtasksimbsoft.services.HTMLParseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Profile("console")
public class StartView implements CommandLineRunner {

    private final HTMLParseService HTMLParseService;
    private final Logger logger = LoggerFactory.getLogger(StartView.class);

    public StartView(HTMLParseService HTMLParseService) {
        this.HTMLParseService = HTMLParseService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length < 1) {
            logger.error("No url for parsing. Aborting...");
            throw new RuntimeException("No url for parsing was provided");
        }

        String url = args[0];

        try {
            logger.info("Starting parsing for url: " + url);
            Map<String, Long> results = this.HTMLParseService.getWordsFrequencyFromHtml(url);
            logger.info("Parsing for " + url + " completed");
            printFrequencyResults(results, url);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printFrequencyResults(Map<String, Long> data, String url) {
        System.out.println("-".repeat(30));
        System.out.printf("PARSE RESULTS FOR URL: %s\n", url);
        System.out.println("-".repeat(30));
        for (Map.Entry<String, Long> entry:
             data.entrySet()) {
            System.out.printf("%-40s | %-10d\n", entry.getKey(), entry.getValue());
        }
        System.out.println("-".repeat(30));
    }
}
