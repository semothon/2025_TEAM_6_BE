package org.semothon.survey.user.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.semothon.survey.user.domain.enumerate.UserRole;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", length = 50)
    private String userId; // 학번

    private String userName;
    private String userAffiliation;
    private String userNumber;
    private String userEmail;
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}