package testrobotchallenge.commons.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class ExtractScore {
    private static final Logger logger = LoggerFactory.getLogger(ExtractScore.class);

    public static int[][] fromEvosuite(String content) {
        int[][] values = new int[8][8];
        String line;
        String delimiter = ",";

        if (content == null) {
            return values;
        }

        try (BufferedReader br = new BufferedReader(new StringReader(content))) {
            br.readLine(); // salto la prima riga, che contiene i nomi delle colonne

            for (int i = 0; i < 8; i++) {
                line = br.readLine();
                logger.info("line " + i + ": " + line);

                String[] columns = line.split(delimiter);

                // Verifico che esistano almeno 5 colonne, la percentuale di coverage si trova sulla terza
                if (columns.length >= 5) {
                    try {
                        values[i] = new int[]{Integer.parseInt(columns[4]), Integer.parseInt(columns[3]) - Integer.parseInt(columns[4])};
                        //double value = Double.parseDouble(columns[2].trim()) * 100;
                    } catch (NumberFormatException e) {
                        logger.error("[extractScore] Errore durante la conversione dei valori di copertura percentuale: {}", e.getMessage());
                        throw new RuntimeException("[extractScore] Errore durante la conversione dei valori di copertura percentuale: " + e.getMessage(), e);

                    }
                }
            }
        } catch (IOException e) {
            logger.error("[extractScore] Errore durante l'estrazione della colonna della copertura percentuale: {}", e.getMessage());
            throw new RuntimeException("[extractScore] Errore durante l'estrazione della colonna della copertura percentuale: " + e.getMessage(), e);
        }

        // Converto la lista in array di interi
        return values;
    }

    public static int[][] fromJacoco(String xmlContent) {
        final String[] coverageTypes = new String[]{"LINE", "BRANCH", "INSTRUCTION"};
        int[][] values = new int[coverageTypes.length][coverageTypes.length];

        if (xmlContent == null) {
            logger.error("[ExtractScore.fromJacoco] xmlContent is null ");
            return values;
        }

        Document doc = Jsoup.parse(xmlContent, "", Parser.xmlParser());
        for (int i = 0; i < coverageTypes.length; i++) {
            String coverageType = coverageTypes[i];
            Element counter = doc.selectFirst("report > counter[type=" + coverageType + "]");
            if (counter == null) {
                logger.error("[ExtractScore.fromJacoco] Counter extracted for {} is null", coverageType);
                values[i] = new int[]{0, 0};
                continue;
            }
            int covered = Integer.parseInt(counter.attr("covered"));
            int missed = Integer.parseInt(counter.attr("missed"));
            values[i] = new int[]{covered, missed};
            logger.info("[ExtractScore.fromJacoco] Score for {} is {}", coverageType, values[i]);
        }

        return values;
    }
}
