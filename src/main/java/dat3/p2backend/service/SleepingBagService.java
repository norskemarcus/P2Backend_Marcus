package dat3.p2backend.service;

import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.stereotype.Service;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
    }
}
