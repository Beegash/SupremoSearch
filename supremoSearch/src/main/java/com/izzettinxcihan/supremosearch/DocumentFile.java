package com.izzettinxcihan.supremosearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocumentFile {
    private String filePath;

    public DocumentFile(String filePath) {
        this.filePath = filePath;
    }

    public void processTextDocument(BST bst, Set<String> ignoreWords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith("<")) {
                    for (String word : line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+")) {
                        if (!word.isEmpty() && !ignoreWords.contains(word)) {
                            bst.insert(word, this.filePath);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing document: " + e.getMessage());
        }
    }

    public void processHtmlDocument(BST bst, Set<String> ignoreWords) {
        try {
            java.io.File input = new java.io.File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");
            Elements elements = doc.body().select("*");

            for (Element element : elements) {
                for (String word : element.ownText().toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+")) {
                    if (!word.isEmpty() && !ignoreWords.contains(word)) {
                        bst.insert(word, this.filePath);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing document: " + e.getMessage());
        }
    }
}
