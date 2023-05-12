package dat3.p2backend.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {

  Double personHeight;
  Boolean isFemale;
  Boolean isColdSensitive;
  @OneToOne
  Result result;


  public Member(String password, String email, Double personHeight, Boolean isFemale, Boolean isColdSensitive) {
    super(email, password, email);
    this.personHeight = personHeight;
    this.isFemale = isFemale;
    this.isColdSensitive = isColdSensitive;
  }
}
