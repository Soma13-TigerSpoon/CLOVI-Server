package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.base.BaseEntity;
import Soma.CLOVI.domain.Gender;
import Soma.CLOVI.domain.Role;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(indexes = {
    @Index(name = "i_user_id", columnList = "userId"),
    @Index(name = "i_user_pw", columnList = "userPassword"),
    @Index(name = "i_name", columnList = "name"),
    @Index(name = "i_phone_num", columnList = "phoneNum")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Creator extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;

  private String userPassword;

  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String email;

  private String phoneNum;

  private String profileImgUrl;

  @Enumerated(EnumType.STRING)
  private Role role;

  private LocalDateTime lastLoginDate;

  private String status;

}
