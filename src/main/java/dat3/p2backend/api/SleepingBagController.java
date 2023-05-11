package dat3.p2backend.api;

import dat3.p2backend.dto.ImageLinkResponse;
import dat3.p2backend.dto.SleepingBagRequest;
import dat3.p2backend.dto.SleepingBagResponse;
import dat3.p2backend.service.ImageLinkService;
import dat3.p2backend.service.SleepingBagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sleeping-bags")
@CrossOrigin
public class SleepingBagController {

    SleepingBagService sleepingBagService;
    ImageLinkService imageLinkService;

    public SleepingBagController(SleepingBagService sleepingBagService, ImageLinkService imageLinkService) {
        this.sleepingBagService = sleepingBagService;
        this.imageLinkService = imageLinkService;
    }

    @PostMapping
    public List<SleepingBagResponse> getSleepingBags(@RequestBody SleepingBagRequest sleepingBagRequest){
        return sleepingBagService.getSleepingBags(sleepingBagRequest);
    }

    @GetMapping("/{sku}")
    public SleepingBagResponse getSleepingBagBySku(@PathVariable Integer sku) {
        return sleepingBagService.getSleepingBagBySku(sku);
    }

    @GetMapping("/image/{sku}")
    public ImageLinkResponse getImageLinkBySku(@PathVariable Integer sku){
        return imageLinkService.getImageLinkBySku(sku);
    }

}
