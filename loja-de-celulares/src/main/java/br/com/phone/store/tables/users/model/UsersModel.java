package br.com.phone.store.tables.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name= "user_name", nullable = false)
    private String userName;

    @Column(name= "user_ssn", nullable = false)
    private String userSsn;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    private String userPassword;

    private Boolean active;

}
