package dat3.p2backend.api;

import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.service.SleepingBagService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sleeping-bags")
@CrossOrigin(origins = "https://lemon-beach-0aa157e03.3.azurestaticapps.net/")

public class SleepingBagController {

    SleepingBagService sleepingBagService;

    public SleepingBagController(SleepingBagService sleepingBagService) {
        this.sleepingBagService = sleepingBagService;
    }

    @PostMapping
    public List<SleepingBagResponse> getSleepingBags(@RequestBody SleepingBagRequest sleepingBagRequest){
        return sleepingBagService.getSleepingBags(sleepingBagRequest);
    }

}
