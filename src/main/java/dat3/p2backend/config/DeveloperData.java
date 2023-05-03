package dat3.p2backend.config;

import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.entity.SleepingBagExternal;
import dat3.p2backend.repository.SleepingBagExternalRepository;
import dat3.p2backend.repository.SleepingBagRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class DeveloperData implements CommandLineRunner {

    SleepingBagRepository sleepingBagRepository;

    SleepingBagExternalRepository sleepingBagExternalRepository;

    public DeveloperData(SleepingBagRepository sleepingBagRepository, SleepingBagExternalRepository sleepingBagExternalRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
        this.sleepingBagExternalRepository = sleepingBagExternalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        importData();
    }

    public void importData() {
        String path = "sovepose-data.csv";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"ISO-8859-1"));

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setDelimiter(';')
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            boolean skippedFirstRow = false;
            for (CSVRecord record : records) {
                if (skippedFirstRow == false) {
                    skippedFirstRow = true;
                    continue;
                }

                SleepingBagExternal sleepingBagExternal = new SleepingBagExternal(
                        record.get(0),
                        record.get(1),
                        record.get(2),
                        record.get(3),
                        record.get(4),
                        record.get(5),
                        record.get(6),
                        record.get(7),
                        record.get(8),
                        record.get(9),
                        record.get(10),
                        record.get(11),
                        record.get(12)
                );

                SleepingBag sleepingBag = new SleepingBag(sleepingBagExternal);

                sleepingBagRepository.save(sleepingBag);

                sleepingBagExternalRepository.save(sleepingBagExternal);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
