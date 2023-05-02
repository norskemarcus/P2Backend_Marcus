package dat3.p2backend.config;

import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.service.SleepingBagService;
import dat3.p2backend.service.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DeveloperData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Test test = new Test();
        test.getData();
    }
}
