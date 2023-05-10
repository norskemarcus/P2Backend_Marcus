package dat3.p2backend.api;


import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.service.MemberService;
import dat3.security.entity.Role;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/member")
public class MemberController {

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  MemberService memberService;

  @PostMapping
  public void addUserWithRoles(@RequestBody MemberRequest request) {
    memberService.addUserWithRoles (request, Role.USER);
  }


}
