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
        List<SleepingBag> sleepingBagsAll = sleepingBagRepository.findAll();

        List<SleepingBag> sleepingBagsTempFiltered = new ArrayList<>();
        if (sleepingBagRequest.getEnvironmentTemperatureMin() != null) {
            for (SleepingBag sleepingBag : sleepingBagsAll) {
                if (sleepingBagRequest.getIsColdSensitive() != null && sleepingBagRequest.getIsColdSensitive()) {
                    if (sleepingBag.getComfortTemp() != null) {
                        if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getComfortTemp()) {
                            sleepingBagsTempFiltered.add(sleepingBag);
                        }
                    } else {
                        // todo
                        // what about sleeping bags with neither comfortTemp or lowerLimit?
                        if (sleepingBag.getLowerLimitTemp() != null) {
                            if (sleepingBagRequest.getEnvironmentTemperatureMin() >= (sleepingBag.getLowerLimitTemp()) + 5.0) {
                                sleepingBagsTempFiltered.add(sleepingBag);
                            }
                        }
                    }
                } else {
                    // what about sleeping bags with neither comfortTemp or lowerLimit?
                    if (sleepingBag.getLowerLimitTemp() != null) {
                        if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getLowerLimitTemp()) {
                            sleepingBagsTempFiltered.add(sleepingBag);
                        }
                    }
                }
            }
        }
        else {
            sleepingBagsTempFiltered = sleepingBagsAll;
        }

        List<SleepingBag> sleepingBagsHeightFiltered = new ArrayList<>();
        if (sleepingBagRequest.getPersonHeight() != null) {
            for (SleepingBag sleepingBag : sleepingBagsTempFiltered) {
                if (sleepingBag.getPersonHeight() != null) {
                    if (sleepingBagRequest.getPersonHeight() <= sleepingBag.getPersonHeight()) {
                        sleepingBagsHeightFiltered.add(sleepingBag);
                    }
                }
            }
        }
        else {
            sleepingBagsHeightFiltered = sleepingBagsTempFiltered;
        }

        List<SleepingBag> sleepingBagsFemaleFiltered = new ArrayList<>();
        if (sleepingBagRequest.getIsFemale() != null) {
            for (SleepingBag sleepingBag : sleepingBagsHeightFiltered) {
                if (sleepingBag.getIsFemale() != null) {
                    if (sleepingBagRequest.getIsFemale() == sleepingBag.getIsFemale()) {
                        sleepingBagsFemaleFiltered.add(sleepingBag);
                    }
                }
            }
        }
        else {
            sleepingBagsFemaleFiltered = sleepingBagsHeightFiltered;
        }

        List<SleepingBag> sleepingBagsCostFiltered = new ArrayList<>();
        if (sleepingBagRequest.getMaxCost() != null) {
            for (SleepingBag sleepingBag : sleepingBagsFemaleFiltered) {
                if (sleepingBag.getCost() != null) {
                    if (sleepingBagRequest.getMaxCost() >= sleepingBag.getCost()) {
                        sleepingBagsCostFiltered.add(sleepingBag);
                    }
                }
            }
        }
        else {
            sleepingBagsCostFiltered = sleepingBagsFemaleFiltered;
        }

        List<SleepingBag> sleepingBagsFiltered = sleepingBagsCostFiltered;

        List<SleepingBagResponse> sleepingBagResponses = sleepingBagsFiltered.stream().map(s -> new SleepingBagResponse(s)).toList();
        return sleepingBagResponses;
    }

}
