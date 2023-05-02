package dat3.p2backend.config;

import dat3.p2backend.entity.SleepingBagExternal;
import dat3.p2backend.repository.SleepingBagExternalRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class DeveloperData implements CommandLineRunner {

    SleepingBagExternalRepository sleepingBagExternalRepository;

    public DeveloperData(SleepingBagExternalRepository sleepingBagExternalRepository) {
        this.sleepingBagExternalRepository = sleepingBagExternalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        importData();
    }

    public void importData() {
        String path = "sovepose-data.csv";

        try {
            Reader in = new FileReader(path);

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
                sleepingBagExternalRepository.save(sleepingBagExternal);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
