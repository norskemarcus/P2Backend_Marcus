package dat3.p2backend.service;

import dat3.p2backend.dto.ImageLinkResponse;
import dat3.p2backend.entity.ImageLink;
import dat3.p2backend.repository.ImageLinkRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class ImageLinkService {

  private ImageLinkRepository imageLinkRepository;

  public ImageLinkService(ImageLinkRepository imageLinkRepository){
    this.imageLinkRepository = imageLinkRepository;
  }


  public ImageLinkResponse getImageLinkBySku(Integer sku){
    ImageLink imageLink = imageLinkRepository.findById(sku).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Image link not found"));
    return new ImageLinkResponse(imageLink);
  }



}
