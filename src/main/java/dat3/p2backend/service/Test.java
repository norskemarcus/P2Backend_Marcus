package dat3.p2backend.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {

  public void getData() {

    String path = "sovepose-data.csv";
    try (
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        )  {
      List<CSVRecord> csvRecords = csvParser.getRecords();
      System.out.println(csvRecords);
      for (CSVRecord csvRecord : csvParser) {
        String s = csvRecord.get(0);
        System.out.println("test2");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
