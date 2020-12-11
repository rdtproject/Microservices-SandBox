package com.tpolm.microsandbox.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpolm.microsandbox.domain.Difficulty;
import com.tpolm.microsandbox.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Service
public class JSonDataImporter {

    @Value("${ec.importfile}")
    private String importFile;

    @Autowired
    private TourService tourService;

    public void importDataFromJson() throws IOException {
        createTours(importFile);
    }

    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
                tourService.createTour(
                        importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }

    private static class TourFromFile {

        private String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;

        protected TourFromFile() {
        }

        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {
                    });
        }

        String getPackageType() {
            return packageType;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }

        String getBlurb() {
            return blurb;
        }

        Integer getPrice() {
            return Integer.parseInt(price);
        }

        String getLength() {
            return length;
        }

        String getBullets() {
            return bullets;
        }

        String getKeywords() {
            return keywords;
        }

        Difficulty getDifficulty() {
            return Difficulty.valueOf(difficulty);
        }

        Region getRegion() {
            return Region.fromLabel(region);
        }
    }
}
