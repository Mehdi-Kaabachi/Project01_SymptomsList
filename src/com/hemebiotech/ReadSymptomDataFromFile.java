package com.hemebiotech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ReadSymptomDataFromFile {

    public static void main(String[] args) throws Exception {

        //a raw listing of all Symptoms obtained from a data source

        BufferedReader reader = new BufferedReader(new FileReader("src/resources/symptoms.txt"));
        String line = reader.readLine();
        List<String> readSymptoms = new ArrayList<>();

        while (line != null) {

            readSymptoms.add(line);
            line = reader.readLine();
        }

        //remove duplicates and counts the number of occurrences of the list readSymptoms

        Map<String, Integer> countSymptoms = new TreeMap<>();
        for (String lines : readSymptoms) {

            if (countSymptoms.containsKey(lines)) {
                countSymptoms.put(lines, countSymptoms.get(lines) + 1);
            } else {
                countSymptoms.put(lines, 1);
            }
        }

        //transforms the map into a list, which makes it possible to write it in a file

        List<String> result = countSymptoms.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());

        //write the list in a file

        FileWriter writer = new FileWriter("result.out");
        for (String str : result) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
}
