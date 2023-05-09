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
import java.util.function.Predicate;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
    }


    public List<SleepingBagResponse> getSleepingBags(SleepingBagRequest sleepingBagRequest){
        List<SleepingBag> sleepingBags = sleepingBagRepository.findAll();

        //TODO: Mangler køn-filtrering

      List<SleepingBagResponse> sleepingBagResponses;

      sleepingBagResponses = sleepingBags.stream()
          .filter(sleepingBag -> {
            if (sleepingBagRequest.getIsColdSensitive() == null || sleepingBagRequest.getIsColdSensitive()) {
              return sleepingBag.getComfortTemp() == null || sleepingBag.getComfortTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
            } else {
              return sleepingBag.getLowerLimitTemp() == null || sleepingBag.getLowerLimitTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
            }
          })
          .filter(sleepingBag -> {
            if (!sleepingBagRequest.getIsFemale()) {
              return !sleepingBag.getIsFemale();
            } else {
              return true;
            }
          })
          .filter(sleepingBag -> sleepingBagRequest.getMaxCost() == null || sleepingBag.getCost() <= sleepingBagRequest.getMaxCost())
          .filter(sleepingBag -> sleepingBagRequest.getInnerMaterial() == null || sleepingBag.getInnerMaterial().equals(sleepingBagRequest.getInnerMaterial()))
          .filter(sleepingBag -> sleepingBagRequest.getPersonHeight() == null || sleepingBag.getPersonHeight() >= sleepingBagRequest.getPersonHeight())
          .map(SleepingBagResponse::new).toList();

      return sleepingBagResponses;
    }

    public SleepingBagResponse getSleepingBagBySku(Integer sku) {
        SleepingBag sleepingBag = sleepingBagRepository.findById(sku).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleeping bag not found"));
        return new SleepingBagResponse(sleepingBag);
    }

}
