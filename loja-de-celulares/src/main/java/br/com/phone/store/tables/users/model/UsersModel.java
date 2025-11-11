package br.com.phone.store.tables.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a user in the phone store system.
 *
 * <p>This class maps to the {@code users} table and stores essential information
 * about each user, including their name, identification number, email, password, and active status.</p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {

    /**
     * Unique identifier for the user.
     * Auto-generated using a sequence named {@code users_seq}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;

    /**
     * Full name of the user.
     * Cannot be null.
     */
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * User's identification number (e.g., CPF).
     * Cannot be null.
     */
    @Column(name = "user_ssn", nullable = false)
    private String userSsn;

    /**
     * User's email address.
     * Cannot be null.
     */
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    /**
     * User's password.
     * Stored as plain text (consider encrypting for production).
     */
    private String userPassword;

    /**
     * Indicates whether the user is currently active.
     */
    private Boolean active;
}
