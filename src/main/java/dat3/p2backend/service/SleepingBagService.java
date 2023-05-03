package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
    }


    public List<SleepingBagResponse> getAllSleepingBags(){
        List<SleepingBag> sleepingBags = sleepingBagRepository.findAll();
        return sleepingBags.stream().map(SleepingBagResponse::new).toList();
    }


}
