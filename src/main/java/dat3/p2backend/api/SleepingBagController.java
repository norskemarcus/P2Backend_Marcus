package dat3.p2backend.api;

import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.service.SleepingBagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sleeping-bags")
@CrossOrigin
public class SleepingBagController {

    SleepingBagService sleepingBagService;

    public SleepingBagController(SleepingBagService sleepingBagService) {
        this.sleepingBagService = sleepingBagService;
    }


    @GetMapping()
    public List<SleepingBagResponse> getAllSleepingBags() {
        return sleepingBagService.getAllSleepingBags();
    }

}
