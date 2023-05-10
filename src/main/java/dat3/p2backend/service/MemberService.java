package dat3.p2backend.service;

import dat3.p2backend.dto.MemberRequest;
import dat3.p2backend.entity.Member;
import dat3.p2backend.repository.MemberRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MemberService {

MemberRepository memberRepository;
private PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    this.memberRepository = memberRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void addUserWithRoles(MemberRequest request, Role user) {

        if(memberRepository.existsById(request.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This email is used by another user");
    }
    String pw = passwordEncoder.encode(request.getPassword());

    Member member = new Member(pw, request.getEmail(), request.getPersonHeight(), request.getIsFemale(), request.getIsColdSensitive());
    member.addRole(user);
    memberRepository.save(member);
  }
}
