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
                if (sleepingBag.getComfortTemp() != null && sleepingBag.getLowerLimitTemp() != null) {
                    if (sleepingBagRequest.getIsColdSensitive() != null) {
                        if (sleepingBagRequest.getIsColdSensitive()) {
                            if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getComfortTemp()) {
                                sleepingBagsTempFiltered.add(sleepingBag);
                            }
                        }
                        else {
                            if (sleepingBagRequest.getEnvironmentTemperatureMin() >= sleepingBag.getLowerLimitTemp()) {
                                sleepingBagsTempFiltered.add(sleepingBag);
                            }
                        }
                    }
                }
                // todo
                // what about sleeping bags with no only recommendedTEMP?
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

        List<SleepingBag> sleepingBagsInnerMaterialFiltered = new ArrayList<>();
        if (sleepingBagRequest.getInnerMaterial() != null) {
            for (SleepingBag sleepingBag : sleepingBagsCostFiltered) {
                if (sleepingBag.getInnerMaterial() != null) {
                    if (sleepingBagRequest.getInnerMaterial().equals( sleepingBag.getInnerMaterial())) {
                        sleepingBagsInnerMaterialFiltered.add(sleepingBag);
                    }
                }
            }
        }
        else {
            sleepingBagsInnerMaterialFiltered = sleepingBagsCostFiltered;
        }

        List<SleepingBag> sleepingBagsFiltered = sleepingBagsInnerMaterialFiltered;

        List<SleepingBagResponse> sleepingBagResponses = sleepingBagsFiltered.stream().map(s -> new SleepingBagResponse(s)).toList();
        return sleepingBagResponses;
    }

    public SleepingBagResponse getSleepingBagBySku(Integer sku) {
        SleepingBag sleepingBag = sleepingBagRepository.findById(sku).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleeping bag not found"));
        return new SleepingBagResponse(sleepingBag);
    }

}
