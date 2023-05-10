package dat3.p2backend.service;

import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.repository.SleepingBagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class SleepingBagService {
    SleepingBagRepository sleepingBagRepository;

    public SleepingBagService(SleepingBagRepository sleepingBagRepository) {
        this.sleepingBagRepository = sleepingBagRepository;
    }


    public List<SleepingBagResponse> getSleepingBags(SleepingBagRequest sleepingBagRequest){
        List<SleepingBag> sleepingBags = sleepingBagRepository.findAll();


      List<SleepingBagResponse> sleepingBagResponses;

      sleepingBagResponses = sleepingBags.stream()
          .filter(sleepingBag -> {
            if (sleepingBagRequest.getIsColdSensitive() == null || sleepingBagRequest.getIsColdSensitive()) {
              return sleepingBagRequest.getEnvironmentTemperatureMin() == null || sleepingBag.getComfortTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
            } else {
              return sleepingBagRequest.getEnvironmentTemperatureMin() == null || sleepingBag.getLowerLimitTemp() <= sleepingBagRequest.getEnvironmentTemperatureMin();
            }
          })
          .filter(sleepingBag -> sleepingBagRequest.getMaxCost() == null || sleepingBag.getCost() <= sleepingBagRequest.getMaxCost())
          .filter(sleepingBag -> sleepingBagRequest.getInnerMaterial() == null || sleepingBag.getInnerMaterial().equals(sleepingBagRequest.getInnerMaterial()))
          .filter(sleepingBag -> sleepingBagRequest.getPersonHeight() == null ||
              (
                  //Skal måske laves om til cm i stedet for %?
                  sleepingBag.getPersonHeight() >= sleepingBagRequest.getPersonHeight()) &&
                  //(sleepingBag.getPersonHeight() - sleepingBagRequest.getPersonHeight()) / sleepingBagRequest.getPersonHeight() * 100 <= 20
                  (sleepingBag.getPersonHeight() - sleepingBagRequest.getPersonHeight() <= 15)
          )
          .map(SleepingBagResponse::new)
          .toList();

      //Fjern soveposer, der er for lange:
      //Hvis højde ikke er null sorteres soveposerne efter modelnavn og derefter længde,
      // så den eventuelt for lange sovepose filtreres fra
      if(sleepingBagRequest.getPersonHeight() != null) {
        sleepingBagResponses = sleepingBags.stream()
          .sorted(Comparator.comparing(SleepingBag::getModel).thenComparing(SleepingBag::getPersonHeight))
            .filter(distinctByKey(SleepingBag::getModel))
            .map(SleepingBagResponse::new)
            .toList();
      }

      if(sleepingBagRequest.getIsFemale() != null) {
        sleepingBagResponses = sleepingBags.stream()
            .filter(sleepingBag -> {
              if (sleepingBagRequest.getIsFemale() == null || !sleepingBagRequest.getIsFemale()) {
                return sleepingBag.getIsFemale() == null || !sleepingBag.getIsFemale();
              } else {
                return true;
              }
            })
            .map(SleepingBagResponse::new)
            .toList();
      }

      return sleepingBagResponses;
    }

    public SleepingBagResponse getSleepingBagBySku(Integer sku) {
        SleepingBag sleepingBag = sleepingBagRepository.findById(sku).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sleeping bag not found"));
        return new SleepingBagResponse(sleepingBag);
    }

    // Taget fra Stack overflow
    // https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

}
