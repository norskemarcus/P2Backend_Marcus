package dat3.p2backend.service;

import dat3.p2backend.config.DeveloperData;
import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.entity.SleepingBag;
import dat3.p2backend.entity.SleepingBagExternal;
import dat3.p2backend.repository.SleepingBagExternalRepository;
import dat3.p2backend.repository.SleepingBagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SleepingBagServiceTest {


  @Autowired
  SleepingBagRepository sleepingBagRepository;

  @Autowired
  SleepingBagExternalRepository sleepingBagExternalRepository;

  SleepingBagService sleepingBagService;


  DeveloperData developerData;



  @Test
  void getSleepingBags() {
    developerData = new DeveloperData(sleepingBagRepository, sleepingBagExternalRepository);
    developerData.importData();

    SleepingBagRequest sleepingBagRequest = new SleepingBagRequest();
    sleepingBagRequest.setPersonHeight(175.0);
    sleepingBagRequest.setEnvironmentTemperatureMin(1.0);
    sleepingBagRequest.setIsFemale(false);
    sleepingBagRequest.setIsColdSensitive(true);
    sleepingBagRequest.setMaxCost(3500.0);
    sleepingBagRequest.setInnerMaterial("Dun");

    sleepingBagService = new SleepingBagService(sleepingBagRepository);

    List<SleepingBagResponse> sleepingBagResponses = sleepingBagService.getSleepingBags(sleepingBagRequest);

    assertNotEquals(0, sleepingBagResponses.size());

  }


}