package de.htwberlin.usermanagementservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Schema(
        name ="User",
        description = "Entity class representing a User in the system.The class implements {@link UserDetails} for integrating with Spring Security.It maps to the 'user' table in the database and stores user details for authentication and authorization."
)
@Entity
@Data
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;


    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;


    @Column(name = "country")
    private String country;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "gender")
    private String gender;

    /**
     * The role of the user, used for authorization (e.g., "USER", "ADMIN").
     */
    @Column(name="role")
    private String role;


    /**
     * Retrieves the authorities granted to the user. The authorities are derived from the user's role.
     *
     * @return A collection of {@link GrantedAuthority} representing the user's role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }


    /**
     * Retrieves the username of the user, which is the user's email.
     *
     * @return The email address of the user.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     * In this implementation, accounts are never considered expired.
     *
     * @return {@code true} if the account is not expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * In this implementation, accounts are never considered locked.
     *
     * @return {@code true} if the account is not locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials have expired.
     * In this implementation, credentials are never considered expired.
     *
     * @return {@code true} if the credentials are not expired, {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
