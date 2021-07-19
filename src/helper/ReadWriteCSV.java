package helper;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteCSV {

    public static ArrayList<String> readFile(String path) throws IOException {
        String currentLine = "";
        String[] results;
        ArrayList<String> finalResult = new ArrayList<String>();
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        while ((currentLine = br.readLine()) != null) {
            results = currentLine.split(",");
            for (int i = 0; i < results.length; i++) {
                finalResult.add(results[i]);
            }
        }
        br.close();
        return finalResult;
    }

    public static void writeFile(String path, String line) throws IOException {
        FileWriter csvFile = new FileWriter(path, true);
        PrintWriter out = new PrintWriter(csvFile);
        out.append(line);
        out.close();
    }

    public static void clearFile(String path) throws IOException {
        FileWriter csvFile = new FileWriter(path);
        PrintWriter out = new PrintWriter(csvFile);
        out.println("");
        out.close();
    }


}
