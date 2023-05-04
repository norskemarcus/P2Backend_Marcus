package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
    }


    public List<SleepingBagResponse> getSleepingBags(SleepingBagRequest sleepingBagRequest){
        List<SleepingBag> sleepingBags = sleepingBagRepository.findAll();

        List<SleepingBag> sleepingBagsFiltered = new ArrayList<>();
        for (SleepingBag sleepingBag : sleepingBags) {
            if (sleepingBagRequest.getIsColdSensitive()) {
                if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getComfortTemp()) {
                    sleepingBagsFiltered.add(sleepingBag);
                }
            }
            else {
                if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getLowerLimitTemp()) {
                    sleepingBagsFiltered.add(sleepingBag);
                }
            }
        }

        List<SleepingBagResponse> sleepingBagResponses = sleepingBagsFiltered.stream().map(s -> new SleepingBagResponse(s)).toList();
        return sleepingBagResponses;
    }

}
