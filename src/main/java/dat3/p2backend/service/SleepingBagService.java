package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public SleepingBagResponse getSleepingBagBySku(Integer sku) {
        SleepingBag sleepingBag = sleepingBagRepository.findById(sku).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleepingbag not found"));
        return new SleepingBagResponse(sleepingBag);
    }
}
